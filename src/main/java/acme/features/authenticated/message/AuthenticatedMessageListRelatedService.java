
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Message;
import acme.features.authenticated.thread.AuthenticatedThreadRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListRelatedService implements AbstractListService<Authenticated, Message> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	AuthenticatedMessageRepository	repository;
	@Autowired
	AuthenticatedThreadRepository	threadRepository;


	// AbstractListService<Authenticated, Offer> interface -------------------------------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;
		Integer id = request.getModel().getInteger("id");
		acme.entities.threads.Thread t = this.threadRepository.findOneById(id);
		result = t.getMessages();

		return result;
	}

}
