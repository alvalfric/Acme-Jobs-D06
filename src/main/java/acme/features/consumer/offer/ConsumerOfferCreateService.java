
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	// Internal state ----------------------------------------------------------

	@Autowired
	ConsumerOfferRepository repository;


	// AbstractCreateService<Consumer, Offer> interface ---------------------

	@Override
	public boolean authorise(final Request<Offer> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "text", "rewardMin", "rewardMax", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		// TODO Auto-generated method stub
		Offer result;

		result = new Offer();

		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		Offer existing;
		Calendar calendar;
		Date minimumDeadline;
		boolean isConfirmed;

		String regexTicker = "^O([A-Z]{4})-([0-9]{5})$";
		Boolean tickerOK = entity.getTicker().matches(regexTicker);
		errors.state(request, tickerOK, "ticker", "consumer.offer.error.ticker-doesnt-match-regexp", regexTicker);
		errors.state(request, !entity.getTicker().isEmpty(), "ticker", "consumer.offer.error.ticker-null");
		if (!errors.hasErrors("ticker")) {
			existing = this.repository.findOneByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "consumer.offer.error.duplicated-ticker");
		}

		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "consumer.offer.error.must-confirm");

		calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		minimumDeadline = calendar.getTime();
		errors.state(request, entity.getDeadline() != null && entity.getDeadline().after(minimumDeadline), "deadline", "consumer.offer.error.wrong-deadline");

		if (!errors.hasErrors("rewardMin") && !errors.hasErrors("rewardMax")) {
			errors.state(request, !entity.getRewardMin().equals(null), "rewardMin", "consumer.offer.error.rewardMin-null");
			errors.state(request, entity.getRewardMin().getCurrency().equals("€"), "rewardMin", "consumer.offer.error.rewardMin-not-euro");
			errors.state(request, !entity.getRewardMax().equals(null), "rewardMax", "consumer.offer.error.rewardMax-null");
			errors.state(request, entity.getRewardMax().getCurrency().equals("€"), "rewardMax", "consumer.offer.error.rewardMax-not-euro");
			errors.state(request, entity.getRewardMin().getAmount() <= entity.getRewardMax().getAmount(), "rewardMin", "consumer.offer.error.rewardMin-too-big");
		}

		errors.state(request, !entity.getTitle().isEmpty(), "title", "consumer.offer.error.title-null");
		errors.state(request, !entity.getText().isEmpty(), "text", "consumer.offer.error.text-null");

	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		Date creation;

		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);

	}

}
