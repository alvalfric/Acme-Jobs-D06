
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "reward", "goal", "deadline");

	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;
		Challenge result;

		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneChallengeById(id);

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Calendar calendar;
		Date mes;
		calendar = new GregorianCalendar();
		mes = calendar.getTime();

		if (!errors.hasErrors("description")) {
			errors.state(request, !entity.getDescription().isEmpty(), "description", "administrator.challenge.error.notBlank");
		}

		if (!errors.hasErrors("title")) {
			errors.state(request, !entity.getTitle().isEmpty(), "title", "administrator.challenge.error.notBlank");
		}
		if (this.repository.findOneByTitle(entity.getTitle()) != null) {
			errors.state(request, entity.getId() == this.repository.findOneByTitle(entity.getTitle()).getId(), "title", "administrator.challenge.error.duplicated-title");
		}
		if (entity.getDeadline() != null) {
			errors.state(request, entity.getDeadline().after(mes), "deadline", "administrator.challenge.error.future-dead");

		} else {
			errors.state(request, entity.getDeadline() != null, "deadline", "administrator.challenge.error.notBlank");
		}

	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
