
package acme.features.anonymous.chellikBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chellikBulletin.ChellikBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousChellikBulletinCreateService implements AbstractCreateService<Anonymous, ChellikBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousChellikBulletinRepository repository;


	@Override
	public boolean authorise(final Request<ChellikBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public ChellikBulletin instantiate(final Request<ChellikBulletin> request) {
		assert request != null;

		ChellikBulletin result;
		Date fecha;
		fecha = new Date(System.currentTimeMillis() - 1);

		result = new ChellikBulletin();
		result.setNombre("Abdel");
		result.setApellido("Chellik");
		result.setRole("developer");
		result.setFecha(fecha);

		return result;
	}

	@Override
	public void unbind(final Request<ChellikBulletin> request, final ChellikBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "nombre", "apellido", "role", "fecha");

	}

	@Override
	public void bind(final Request<ChellikBulletin> request, final ChellikBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<ChellikBulletin> request, final ChellikBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<ChellikBulletin> request, final ChellikBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
