
package acme.features.administrator.nonCommercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorNonCommercialBannerUpdateService implements AbstractUpdateService<Administrator, NonCommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		return true;
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
		String regexpurl = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";

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

	}

	@Override
	public void update(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
