
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Message;
import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedThreadAddUserService implements AbstractUpdateService<Authenticated, Thread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		Integer id = request.getModel().getInteger("id");
		Thread thread = this.repository.findOneById(id);

		return principal.getAccountId() == thread.getCreator().getUserAccount().getId();

	}

	@Override
	public void bind(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "creator");
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

	@Override
	public void validate(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer userId = request.getModel().getInteger("userId");
		Authenticated user = this.repository.findOneAuthenticatedBUserAccountyId(userId);
		assert entity.getUsers().contains(user) == false;

	}

	@Override
	public void update(final Request<Thread> request, final Thread entity) {
		assert request != null;
		assert entity != null;

		Integer userId = request.getModel().getInteger("userId");

		Authenticated user = this.repository.findOneAuthenticatedBUserAccountyId(userId);

		entity.getUsers().add(user);

		this.repository.save(entity);

	}

}
