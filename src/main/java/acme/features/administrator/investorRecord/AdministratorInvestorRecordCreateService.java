
package acme.features.administrator.investorRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecords.InvestorRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInvestorRecordCreateService implements AbstractCreateService<Administrator, InvestorRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorInvestorRecordRepository repository;


	@Override
	public boolean authorise(final Request<InvestorRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestorRecord> request, final InvestorRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestorRecord> request, final InvestorRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investorName", "sector", "investingStatement", "stars");
	}

	@Override
	public InvestorRecord instantiate(final Request<InvestorRecord> request) {
		InvestorRecord result;

		result = new InvestorRecord();
		result.setInvestingStatement("Statement");
		result.setInvestorName("Name");
		result.setSector("Sector");

		return result;
	}

	@Override
	public void validate(final Request<InvestorRecord> request, final InvestorRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("investorName")) {
			errors.state(request, !entity.getInvestorName().isEmpty(), "investorName", "javax.validation.constraints.NotBlank.message");
		}
		if (!errors.hasErrors("sector")) {
			errors.state(request, !entity.getSector().isEmpty(), "sector", "javax.validation.constraints.NotBlank.message");
		}
		if (!errors.hasErrors("investingStatement")) {
			errors.state(request, !entity.getInvestingStatement().isEmpty(), "investingStatement", "javax.validation.constraints.NotBlank.message");
		}
		if (entity.getStars() != null && !errors.hasErrors("stars")) {
			Double stars = entity.getStars();
			errors.state(request, stars >= 0 && stars <= 5, "stars", "org.hibernate.validator.constraints.Range.message", '0', '5');
		}

	}

	@Override
	public void create(final Request<InvestorRecord> request, final InvestorRecord entity) {
		this.repository.save(entity);
	}

}
