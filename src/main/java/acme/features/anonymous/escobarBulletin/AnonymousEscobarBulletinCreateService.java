
package acme.features.anonymous.escobarBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.escobarBulletin.EscobarBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousEscobarBulletinCreateService implements AbstractCreateService<Anonymous, EscobarBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousEscobarBulletinRepository repository;


	@Override
	public boolean authorise(final Request<EscobarBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public EscobarBulletin instantiate(final Request<EscobarBulletin> request) {
		assert request != null;

		EscobarBulletin result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		result = new EscobarBulletin();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setBirthdate(moment);

		return result;
	}

	@Override
	public void unbind(final Request<EscobarBulletin> request, final EscobarBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "birthdate", "level", "text");

	}

	@Override
	public void bind(final Request<EscobarBulletin> request, final EscobarBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<EscobarBulletin> request, final EscobarBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<EscobarBulletin> request, final EscobarBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}
}
