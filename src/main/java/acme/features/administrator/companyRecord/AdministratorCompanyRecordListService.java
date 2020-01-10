
package acme.features.administrator.companyRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.company_record.CompanyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorCompanyRecordListService implements AbstractListService<Administrator, CompanyRecord> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	AdministratorCompanyRecordRepository repository;


	// AbstractListService<Authenticated, Offer> interface -------------------------------------

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
	public Collection<CompanyRecord> findMany(final Request<CompanyRecord> request) {
		assert request != null;

		Collection<CompanyRecord> result;

		result = this.repository.findManyCompanyRecords();

		for (CompanyRecord cr : result) {
			if (cr.getIncorporated()) {
				cr.setCompanyName(cr.getCompanyName() + ", Inc");
			} else {
				cr.setCompanyName(cr.getCompanyName() + ", LLC");
			}
		}

		return result;
	}

}
