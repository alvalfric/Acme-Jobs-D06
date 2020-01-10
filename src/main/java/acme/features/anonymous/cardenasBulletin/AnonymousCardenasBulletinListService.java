
package acme.features.anonymous.cardenasBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cardenasBulletin.CardenasBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCardenasBulletinListService implements AbstractListService<Anonymous, CardenasBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousCardenasBulletinRepository repository;

	// AbstractListService<Administrator,CardenasBulletin> Interface ---------------------------------------------------------


	@Override
	public boolean authorise(final Request<CardenasBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<CardenasBulletin> findMany(final Request<CardenasBulletin> request) {
		assert request != null;
		Collection<CardenasBulletin> result;
		result = this.repository.findMany();
		return result;
	}

	@Override
	public void unbind(final Request<CardenasBulletin> request, final CardenasBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "book", "author", "genre", "isbn");

	}
}
