
package acme.features.anonymous.company_record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.company_record.CompanyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousCompanyRecordShowService implements AbstractShowService<Anonymous, CompanyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousCompanyRecordRepository repository;


	// AbstractShowService<Authenticated, Announcement> interface -------------

	@Override
	public boolean authorise(final Request<CompanyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CompanyRecord> request, final CompanyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "companyName", "sector", "CEOName", "activitiesDescription", "website", "contactPhone", "contactEmail", "incorporated", "starScore");
	}

	@Override
	public CompanyRecord findOne(final Request<CompanyRecord> request) {
		assert request != null;

		CompanyRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		if (result.getIncorporated()) {
			result.setCompanyName(result.getCompanyName() + ", Inc");
		} else {
			result.setCompanyName(result.getCompanyName() + ", LLC");
		}

		return result;
	}

}
