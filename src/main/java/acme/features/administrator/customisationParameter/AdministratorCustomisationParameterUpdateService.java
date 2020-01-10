
package acme.features.administrator.customisationParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomisationParameterUpdateService implements AbstractUpdateService<Administrator, CustomisationParameter> {

	@Autowired
	AdministratorCustomisationParameterRepository repository;


	@Override
	public boolean authorise(final Request<CustomisationParameter> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWordsEn", "spamWordsEs", "spamThreshold");

	}

	@Override
	public CustomisationParameter findOne(final Request<CustomisationParameter> request) {
		assert request != null;
		CustomisationParameter result;

		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneCustomisationParameterById(id);

		return result;
	}

	@Override
	public void validate(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("spamThreshold")) {
			errors.state(request, !(entity.getSpamThreshold() == null), "spamThreshold", "administrator.customisation-parameter.error.NotBlank");
		}
		if (!errors.hasErrors("spamWordsEn")) {
			errors.state(request, !entity.getSpamWordsEn().isEmpty(), "spamWordsEn", "administrator.customisation-parameter.error.NotBlank");
		}
		if (!errors.hasErrors("spamWordsEs")) {
			errors.state(request, !entity.getSpamWordsEs().isEmpty(), "spamWordsEs", "administrator.customisation-parameter.error.NotBlank");
		}

		if (entity.getSpamWordsEn() != null && !errors.hasErrors("spamWordsEn")) {
			errors.state(request, this.isValidStringList(entity.getSpamWordsEn()), "spamWordsEn", "administrator.customisation-parameter.error.spamWords");
		}
		if (entity.getSpamWordsEs() != null && !errors.hasErrors("spamWordsEs")) {
			errors.state(request, this.isValidStringList(entity.getSpamWordsEs()), "spamWordsEs", "administrator.customisation-parameter.error.spamWords");
		}
		if (entity.getSpamThreshold() != null && !errors.hasErrors("spamThreshold")) {
			Double limit = entity.getSpamThreshold();
			errors.state(request, limit >= 0.0 && limit <= 100.0, "spamThreshold", "administrator.customisation-parameter.error.spam-threshold", 0.0, 100.0);
		}

	}

	public Boolean isValidStringList(final String s) {
		Boolean result = true;
		if (s.charAt(s.length() - 1) == ',' || s.charAt(s.length() - 1) == ' ' || s.charAt(0) == ',' || s.charAt(0) == ' ') {
			result = false;
		} else {
			for (int i = 0; i < s.length() - 2; i++) {
				if (s.charAt(i) == ',') {
					if (s.charAt(i - 1) == ',' || s.charAt(i - 1) == ' ') {
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}

	@Override
	public void update(final Request<CustomisationParameter> request, final CustomisationParameter entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
