
package acme.features.auditor.auditrecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditrecordListMineService implements AbstractListService<Auditor, Auditrecord> {

	@Autowired
	AuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "moment");

	}

	@Override
	public Collection<Auditrecord> findMany(final Request<Auditrecord> request) {
		assert request != null;

		Collection<Auditrecord> result;
		Principal principal;

		principal = request.getPrincipal();
		if (request.getModel().hasAttribute("jobId")) {
			result = this.repository.findManyByJobIdAndAuditorId(principal.getActiveRoleId(), request.getModel().getInteger("jobId"));
		} else {
			result = this.repository.findManyByAuditrecordId(principal.getActiveRoleId());
		}

		return result;
	}

}
