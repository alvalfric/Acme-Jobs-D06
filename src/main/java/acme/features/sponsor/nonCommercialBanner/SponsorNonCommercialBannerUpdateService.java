
package acme.features.sponsor.nonCommercialBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorNonCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, NonCommercialBanner> {

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		boolean result;

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findOneSponsorBUserAccountyId(principal.getAccountId());
		Authenticated authenticated = this.repository.findOneAuthenticatedUserByAccountId(principal.getAccountId());
		result = sponsor.getUserAccount().getId() == authenticated.getUserAccount().getId();
		return result;
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "targetURL", "jingle", "picture");

	}

	@Override
	public NonCommercialBanner findOne(final Request<NonCommercialBanner> request) {
		assert request != null;
		NonCommercialBanner result;

		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CustomisationParameter s = this.repository.findOneCustomisationParameterById();

		String[] spamEn = s.getSpamWordsEn().toString().split(", ");
		String[] spamEs = s.getSpamWordsEs().toString().split(",");

		String regexpurl = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";

		List<String> spamWord = new ArrayList<String>();
		spamWord.addAll(Arrays.asList(spamEn));
		spamWord.addAll(Arrays.asList(spamEs));

		if (!errors.hasErrors("slogan")) {
			errors.state(request, !entity.getSlogan().isEmpty(), "slogan", "javax.validation.constraints.NotBlank.message");
		}
		if (!errors.hasErrors("targetURL")) {
			errors.state(request, !entity.getTargetURL().isEmpty(), "targetURL", "javax.validation.constraints.NotBlank.message");
			errors.state(request, entity.getTargetURL().matches(regexpurl), "targetURL", "javax.validation.constraints.Pattern.message", regexpurl);
		}
		if (!errors.hasErrors("picture")) {
			errors.state(request, !entity.getPicture().isEmpty(), "picture", "javax.validation.constraints.NotBlank.message");
			errors.state(request, entity.getPicture().matches(regexpurl), "picture", "javax.validation.constraints.Pattern.message", regexpurl);
		}

		if (entity.getPicture() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getPicture()), "picture", "sponsor.nonCommercialBanner.error.spamWords");
		}
		if (entity.getSlogan() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getSlogan()), "slogan", "sponsor.nonCommercialBanner.error.spamWords");
		}
		if (entity.getTargetURL() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getTargetURL()), "targetURL", "sponsor.nonCommercialBanner.error.spamWords");
		}
		if (entity.getJingle() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getJingle()), "jingle", "sponsor.nonCommercialBanner.error.spamWords");
		}

	}

	@Override
	public void update(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
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
