
package acme.features.anonymous.chellikBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chellikBulletin.ChellikBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousChellikBulletinListService implements AbstractListService<Anonymous, ChellikBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousChellikBulletinRepository repository;

	// AbstractListService<Administrator,Shout> Interface ---------------------------------------------------------


	@Override
	public boolean authorise(final Request<ChellikBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<ChellikBulletin> findMany(final Request<ChellikBulletin> request) {
		assert request != null;
		Collection<ChellikBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<ChellikBulletin> request, final ChellikBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "nombre", "apellido", "role", "fecha");

	}
}
