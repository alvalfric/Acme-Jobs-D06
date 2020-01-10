
package acme.features.authenticated.thread;

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
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedThreadRepository repository;


	// AbstractShowService<Authenticated, Announcement> interface -------------

	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		Integer id = request.getModel().getInteger("id");
		Thread result = this.repository.findOneById(id);
		Collection<Authenticated> users = result.getUsers();
		Collection<Integer> userAccounts = new ArrayList<Integer>();
		for (Authenticated user : users) {
			userAccounts.add(user.getUserAccount().getId());
		}

		return principal.getAccountId() == result.getCreator().getUserAccount().getId() || userAccounts.contains(principal.getAccountId());
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "creator.userAccount.username");
		Collection<Message> messages = entity.getMessages();
		model.setAttribute("messages", messages);
		Collection<Authenticated> users = entity.getUsers();
		model.setAttribute("users", users);
		Integer creatorId = entity.getCreator().getId();
		model.setAttribute("creatorId", creatorId);
		Collection<Authenticated> otherUsers = this.repository.findAllAuthenticated();
		otherUsers.removeAll(users);
		model.setAttribute("otherUsers", otherUsers);

	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;

		Thread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		result.getMessages();

		return result;
	}

}
