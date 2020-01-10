
package acme.features.anonymous.escobarBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.escobarBulletin.EscobarBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousEscobarBulletinListService implements AbstractListService<Anonymous, EscobarBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousEscobarBulletinRepository repository;

	// AbstractListService<Administrator,EscobarBulletin> Interface ---------------------------------------------------------


	@Override
	public boolean authorise(final Request<EscobarBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<EscobarBulletin> findMany(final Request<EscobarBulletin> request) {
		assert request != null;
		Collection<EscobarBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<EscobarBulletin> request, final EscobarBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "birthdate", "level", "moment");

	}
}
