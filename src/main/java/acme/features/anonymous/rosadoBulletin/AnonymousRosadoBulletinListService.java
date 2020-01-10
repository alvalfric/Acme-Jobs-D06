
package acme.features.anonymous.rosadoBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.rosadoBulletin.RosadoBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousRosadoBulletinListService implements AbstractListService<Anonymous, RosadoBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousRosadoBulletinRepository repository;

	// AbstractListService<Administrator,Shout> Interface ---------------------------------------------------------


	@Override
	public boolean authorise(final Request<RosadoBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<RosadoBulletin> findMany(final Request<RosadoBulletin> request) {
		assert request != null;
		Collection<RosadoBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<RosadoBulletin> request, final RosadoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "image", "video");

	}
}
