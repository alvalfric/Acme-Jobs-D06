package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "finalMode", "descriptor.description");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		Job result;
		result = new Job();
		result.setEmployer(this.repository.findOneEmployerByUserAccountId(request.getPrincipal().getAccountId()));

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.isFinalMode()) {
			errors.state(request, !entity.isFinalMode(), "finalMode", "employer.job.error.noFinalMode");
			request.getModel().setAttribute("finalMode", false);
		}

		boolean isDuplicated, referenceOK, websiteOk;
		String regexpReference = "^([A-Z0-9]{1,7})-([A-Z0-9]{1,7})$";
		String regexpUrl = "^(https?:\\/\\/)?(www\\.)?([a-zA-Z0-9]+(-?[a-zA-Z0-9])*\\.)+[\\w]{2,}(\\/\\S*)?$";

		isDuplicated = this.repository.findOneJobByReference(entity.getReference()) != null;
		errors.state(request, !isDuplicated, "reference", "employer.job.error.duplicated");

		referenceOK = entity.getReference().matches(regexpReference);
		errors.state(request, referenceOK, "reference", "employer.job.error.pattern", regexpReference);

		if (!errors.hasErrors("reference")) {
			errors.state(request, !entity.getReference().isEmpty(), "reference", "employer.job.error.NotBlank");
		}
		if (!errors.hasErrors("title")) {
			errors.state(request, !entity.getTitle().isEmpty(), "title", "employer.job.error.NotBlank");
		}
		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline() != null, "deadline", "employer.job.error.NotBlank");
		}
		if (!errors.hasErrors("salary")) {
			errors.state(request, entity.getSalary() != null, "salary", "employer.job.error.NotBlank");
		}
		if (!errors.hasErrors("salary")) {
			if (entity.getSalary().getAmount() < 0) {
				errors.state(request, false, "salary", "employer.job.error.salary.negative");
			}
		}
		if (!entity.getMoreInfo().isEmpty()) {
			websiteOk = entity.getMoreInfo().matches(regexpUrl);
			errors.state(request, websiteOk, "website", "eemployer.job.error.pattern", regexpUrl);
		}
		if (!errors.hasErrors("descriptor.description")) {
			errors.state(request, !entity.getDescriptor().getDescription().isEmpty(), "descriptor.description", "employer.job.error.NotBlank");
		}

		if (!errors.hasErrors("title")) {
			errors.state(request, !this.isSpam(request, entity), "title", "employer.job.error.spam");
		}
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity.getDescriptor());
		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Job> request, final Response<Job> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

	private boolean isSpam(final Request<Job> request, final Job entity) {
		CustomisationParameter cp = this.repository.customisationParameters();
		Double spamThreshold = cp.getSpamThreshold();
		List<String> spamWords = new ArrayList<String>();
		spamWords.addAll(Arrays.asList(cp.getSpamWordsEn().toString().split(", ")));
		spamWords.addAll(Arrays.asList(cp.getSpamWordsEs().toString().split(", ")));

		int spamCounter = 0;
		boolean isSpam = false;

		for (String word : spamWords) {
			if (entity.getTitle().contains(word)) {
				spamCounter += 1;
			}
		}

		if (spamCounter > 0) {
			double spamPercentage = (double) spamCounter / entity.getTitle().split(" ").length * 100;
			isSpam = spamPercentage >= spamThreshold;

		}
		return isSpam;
	}
}
