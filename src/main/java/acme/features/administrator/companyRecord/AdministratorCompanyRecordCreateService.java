
package acme.features.administrator.companyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.company_record.CompanyRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCompanyRecordCreateService implements AbstractCreateService<Administrator, CompanyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorCompanyRecordRepository repository;


	// AbstractShowService<Authenticated, Announcement> interface -------------

	@Override
	public boolean authorise(final Request<CompanyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CompanyRecord> request, final CompanyRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CompanyRecord> request, final CompanyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "companyName", "sector", "CEOName", "activitiesDescription", "website", "contactPhone", "contactEmail", "incorporated", "starScore");
	}

	@Override
	public CompanyRecord instantiate(final Request<CompanyRecord> request) {
		CompanyRecord result;

		result = new CompanyRecord();

		return result;
	}

	@Override
	public void validate(final Request<CompanyRecord> request, final CompanyRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isDuplicated, websiteOK, contactPhoneOK, conctatEmailOK;
		String regexpUrl = "^(https?:\\/\\/)?(www\\.)?([a-zA-Z0-9]+(-?[a-zA-Z0-9])*\\.)+[\\w]{2,}(\\/\\S*)?$";
		String regexPhone = "^([+]([1-9]|[1-9][0-9]|[1-9][0-9][0-9])[\\s][(]([0-9]|[0-9][0-9]|[0-9][0-9][0-9]|[0-9][0-9][0-9][0-9])[)][\\s]\\d{6,10})$";
		String regexEmail = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

		isDuplicated = this.repository.findOneCompanyRecordByCompanyName(entity.getCompanyName()) != null;
		errors.state(request, !isDuplicated, "companyName", "administrator.company-record.error.duplicated");

		websiteOK = entity.getWebsite().matches(regexpUrl);
		errors.state(request, websiteOK, "website", "administrator.company-record.error.website", regexpUrl);

		contactPhoneOK = entity.getContactPhone().matches(regexPhone);
		errors.state(request, contactPhoneOK, "contactPhone", "administrator.company-record.error.contact-phone", regexPhone);

		conctatEmailOK = entity.getContactEmail().matches(regexEmail);
		errors.state(request, conctatEmailOK, "contactEmail", "administrator.company-record.error.contact-email", regexEmail);

		if (!errors.hasErrors("companyName")) {
			errors.state(request, !entity.getCompanyName().isEmpty(), "companyName", "administrator.company-record.error.NotBlank");
		}
		if (!errors.hasErrors("sector")) {
			errors.state(request, !entity.getSector().isEmpty(), "sector", "administrator.company-record.error.NotBlank");
		}
		if (!errors.hasErrors("CEOName")) {
			errors.state(request, !entity.getCEOName().isEmpty(), "CEOName", "administrator.company-record.error.NotBlank");
		}
		if (!errors.hasErrors("activitiesDescription")) {
			errors.state(request, !entity.getActivitiesDescription().isEmpty(), "activitiesDescription", "administrator.company-record.error.NotBlank");
		}
		if (!errors.hasErrors("website")) {
			errors.state(request, !entity.getWebsite().isEmpty(), "website", "administrator.company-record.error.NotBlank");
		}
		if (!errors.hasErrors("contactPhone")) {
			errors.state(request, !entity.getContactPhone().isEmpty(), "contactPhone", "administrator.company-record.error.NotBlank");
		}
		if (!errors.hasErrors("contactEmail")) {
			errors.state(request, !entity.getContactEmail().isEmpty(), "contactEmail", "administrator.company-record.error.NotBlank");
		}
		if (entity.getStarScore() != null && !errors.hasErrors("starScore")) {
			Double stars = entity.getStarScore();
			errors.state(request, stars >= 0.0 && stars <= 5.0, "starScore", "administrator.company-record.error.star-score", 0.0, 5.0);
		}
	}

	@Override
	public void create(final Request<CompanyRecord> request, final CompanyRecord entity) {
		assert request != null;
		assert entity != null;

		if (entity.getIncorporated() == null) {
			entity.setIncorporated(false);
		}

		this.repository.save(entity);
	}

}
