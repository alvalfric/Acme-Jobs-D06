
package acme.features.anonymous.alferezBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.alferezBulletin.AlferezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousAlferezBulletinService implements AbstractCreateService<Anonymous, AlferezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousAlferezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<AlferezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public AlferezBulletin instantiate(final Request<AlferezBulletin> request) {
		assert request != null;

		AlferezBulletin result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		result = new AlferezBulletin();
		result.setAuthor("John Doe");
		result.setType("Others");
		result.setUrl("http://www.example.com/");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<AlferezBulletin> request, final AlferezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "type", "url");

	}

	@Override
	public void bind(final Request<AlferezBulletin> request, final AlferezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<AlferezBulletin> request, final AlferezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AlferezBulletin> request, final AlferezBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}
}
