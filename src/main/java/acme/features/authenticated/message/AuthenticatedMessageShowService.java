
package acme.features.authenticated.message;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Message;
import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		Integer id = request.getModel().getInteger("id");
		Thread result = this.repository.findThreadByMessageId(id);
		Collection<Authenticated> users = result.getUsers();
		Collection<Integer> userAccounts = new ArrayList<Integer>();
		for (Authenticated user : users) {
			userAccounts.add(user.getUserAccount().getId());
		}

		assert userAccounts.contains(principal.getAccountId());

		return true;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body", "user.userAccount.username");

	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
