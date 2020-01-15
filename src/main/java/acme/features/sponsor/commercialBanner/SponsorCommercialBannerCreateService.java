
package acme.features.sponsor.commercialBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		boolean result;

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findOneSponsorBUserAccountyId(principal.getAccountId());
		Authenticated authenticated = this.repository.findOneAuthenticatedUserByAccountId(principal.getAccountId());
		result = sponsor.getUserAccount().getId() == authenticated.getUserAccount().getId();
		return result;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "targetURL", "creditCardNumber", "picture", "ccExpirationDate", "ccValidationNumber");

	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result = new CommercialBanner();

		Principal principal = request.getPrincipal();
		Integer id = principal.getAccountId();
		Sponsor sponsor = this.repository.findOneSponsorBUserAccountyId(id);
		result.setSponsor(sponsor);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String regexpurl = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
		String regexpccn = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
		String regexpcced = "^\\d{1,2}\\/\\d{1,2}$";
		String regexpccvn = "^\\d\\d\\d$";

		CustomisationParameter s = this.repository.findOneCustomisationParameterById();

		String[] spamEn = s.getSpamWordsEn().toString().split(", ");
		String[] spamEs = s.getSpamWordsEs().toString().split(",");

		List<String> spamWord = new ArrayList<String>();
		spamWord.addAll(Arrays.asList(spamEn));
		spamWord.addAll(Arrays.asList(spamEs));

		if (!errors.hasErrors("slogan")) {
			errors.state(request, !entity.getSlogan().isEmpty(), "slogan", "javax.validation.constraints.NotBlank.message");
		}
		if (!errors.hasErrors("targetURL")) {
			errors.state(request, !entity.getTargetURL().isEmpty(), "targetURL", "javax.validation.constraints.NotBlank.message");
			errors.state(request, entity.getTargetURL().matches(regexpurl), "targetURL", "acme.validation.pattern", regexpurl);
		}
		if (!errors.hasErrors("picture")) {
			errors.state(request, !entity.getPicture().isEmpty(), "picture", "javax.validation.constraints.NotBlank.message");
			errors.state(request, entity.getPicture().matches(regexpurl), "picture", "acme.validation.pattern", regexpurl);
		}
		if (!errors.hasErrors("creditCardNumber")) {
			errors.state(request, !entity.getCreditCardNumber().isEmpty(), "creditCardNumber", "javax.validation.constraints.NotBlank.message");
			errors.state(request, entity.getCreditCardNumber().matches(regexpccn), "creditCardNumber", "acme.validation.pattern", regexpccn);
		}
		if (!errors.hasErrors("ccExpirationDate")) {
			errors.state(request, !entity.getCcExpirationDate().isEmpty(), "ccExpirationDate", "javax.validation.constraints.NotBlank.message");
			errors.state(request, entity.getCcExpirationDate().matches(regexpcced), "ccExpirationDate", "acme.validation.pattern", regexpcced);
		}
		if (!errors.hasErrors("ccValidationNumber")) {
			errors.state(request, !entity.getCcValidationNumber().isEmpty(), "ccValidationNumber", "javax.validation.constraints.NotBlank.message");
			errors.state(request, entity.getCcValidationNumber().matches(regexpccvn), "ccValidationNumber", "acme.validation.pattern", regexpccvn);
		}

		if (entity.getPicture() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getPicture()), "picture", "sponsor.commercialBannerr.error.spamWords");
		}
		if (entity.getSlogan() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getSlogan()), "slogan", "sponsor.commercialBannerr.error.spamWords");
		}
		if (entity.getTargetURL() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getTargetURL()), "targetURL", "sponsor.commercialBannerr.error.spamWords");
		}

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	public Boolean existeWordSpam(final List<String> lista, final String text) {
		int spamconter = 0;
		boolean res = false;
		for (String word : lista) {
			if (text.contains(word)) {
				spamconter += 1;
			}
		}
		if (spamconter > 0) {

			res = true;
		}
		return res;
	}

}
