
package acme.features.anonymous.alferezBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.alferezBulletin.AlferezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousAlferezBulletinListService implements AbstractListService<Anonymous, AlferezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousAlferezBulletinRepository repository;

	// AbstractListService<Administrator,Shout> Interface ---------------------------------------------------------


	@Override
	public boolean authorise(final Request<AlferezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<AlferezBulletin> findMany(final Request<AlferezBulletin> request) {
		assert request != null;
		Collection<AlferezBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<AlferezBulletin> request, final AlferezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "type", "url", "moment");

	}
}
