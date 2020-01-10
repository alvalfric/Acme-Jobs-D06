
package acme.features.administrator.requestAuditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestAuditors.RequestAuditor;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorRequestAuditorAccept implements AbstractCreateService<Administrator, RequestAuditor> {

	@Autowired
	AdministratorRequestAuditorRepository repository;


	@Override
	public boolean authorise(final Request<RequestAuditor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<RequestAuditor> request, final RequestAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<RequestAuditor> request, final RequestAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStat");
	}

	@Override
	public RequestAuditor instantiate(final Request<RequestAuditor> request) {
		assert request != null;

		RequestAuditor result;
		UserAccount ua = this.repository.findOneUserAccountById(request.getModel().getInteger("id"));

		result = new RequestAuditor();
		result = this.repository.findOneRequestAuditorById(request.getModel().getInteger("id"));

		return result;
	}

	@Override
	public void validate(final Request<RequestAuditor> request, final RequestAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<RequestAuditor> request, final RequestAuditor entity) {
		assert request != null;
		assert entity != null;

		Auditor auditor = new Auditor();
		auditor.setFirm(entity.getFirm());
		auditor.setResponsabilityStat(entity.getResponsabilityStat());
		auditor.setUserAccount(entity.getUserAccount());

		this.repository.save(auditor);
		this.repository.delete(entity);
	}

	@Override
	public void onSuccess(final Request<RequestAuditor> request, final Response<RequestAuditor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}
