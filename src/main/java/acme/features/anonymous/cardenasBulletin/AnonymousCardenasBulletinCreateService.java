
package acme.features.anonymous.cardenasBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cardenasBulletin.CardenasBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCardenasBulletinCreateService implements AbstractCreateService<Anonymous, CardenasBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousCardenasBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CardenasBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public CardenasBulletin instantiate(final Request<CardenasBulletin> request) {
		assert request != null;

		CardenasBulletin result;

		result = new CardenasBulletin();
		result.setBook("La Comunidad del Anillo");
		result.setAuthor("J.R.R Tolkien");
		result.setGenre("Fantasía");
		result.setIsbn("9788071457060");

		return result;
	}

	@Override
	public void unbind(final Request<CardenasBulletin> request, final CardenasBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "book", "author", "genre", "isbn");

	}

	@Override
	public void bind(final Request<CardenasBulletin> request, final CardenasBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<CardenasBulletin> request, final CardenasBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<CardenasBulletin> request, final CardenasBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
