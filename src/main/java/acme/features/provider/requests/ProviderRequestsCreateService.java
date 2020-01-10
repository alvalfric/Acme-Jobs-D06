
package acme.features.provider.requests;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Requests;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestsCreateService implements AbstractCreateService<Provider, Requests> {

	// Internal state ----------------------------------------------------------

	@Autowired
	ProviderRequestsRepository repository;


	// AbstractCreateService<Provider, Requests> interface ---------------------

	@Override
	public boolean authorise(final Request<Requests> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Requests> request, final Requests entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Requests> request, final Requests entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "text", "reward", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}
	}

	@Override
	public Requests instantiate(final Request<Requests> request) {
		// TODO Auto-generated method stub
		Requests result;

		result = new Requests();

		return result;
	}

	@Override
	public void validate(final Request<Requests> request, final Requests entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		Requests existing;
		Calendar calendar;
		Date minimumDeadline;
		boolean isConfirmed;

		String regexTicker = "^R([A-Z]{4})-([0-9]{5})$";
		Boolean tickerOK = entity.getTicker().matches(regexTicker);
		errors.state(request, tickerOK, "ticker", "provider.requests.error.ticker-doesnt-match-regexp", regexTicker);
		errors.state(request, !entity.getTicker().isEmpty(), "ticker", "provider.requests.error.ticker-null");
		if (!errors.hasErrors("ticker")) {
			existing = this.repository.findOneByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "provider.requests.error.duplicated-ticker");
		}

		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "provider.requests.error.must-confirm");

		calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		minimumDeadline = calendar.getTime();
		errors.state(request, entity.getDeadline() != null && entity.getDeadline().after(minimumDeadline), "deadline", "provider.requests.error.wrong-deadline");

		if (!errors.hasErrors("reward")) {
			errors.state(request, !entity.getReward().equals(null), "reward", "provider.requests.error.reward-null");
			errors.state(request, entity.getReward().getCurrency().equals("€"), "reward", "provider.requests.error.reward-not-euro");
		}

		errors.state(request, !entity.getTitle().isEmpty(), "title", "provider.requests.error.title-null");
		errors.state(request, !entity.getText().isEmpty(), "text", "provider.requests.error.text-null");

	}

	@Override
	public void create(final Request<Requests> request, final Requests entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		Date creation;

		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);

	}

}
