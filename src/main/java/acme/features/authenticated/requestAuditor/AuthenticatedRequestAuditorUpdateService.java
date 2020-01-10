
package acme.features.authenticated.requestAuditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestAuditors.RequestAuditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedRequestAuditorUpdateService implements AbstractUpdateService<Authenticated, RequestAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedRequestAuditorRepository repository;


	// AbstractUpdateService<Authenticated, Provider> interface ---------------

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

		entity.setUserAccount(this.repository.findOneUserAccountById(request.getPrincipal().getAccountId()));
		request.bind(entity, errors, "userAccountId");
	}

	@Override
	public void unbind(final Request<RequestAuditor> request, final RequestAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStat");
	}

	@Override
	public RequestAuditor findOne(final Request<RequestAuditor> request) {
		assert request != null;

		RequestAuditor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneRequestAuditorByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<RequestAuditor> request, final RequestAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<RequestAuditor> request, final RequestAuditor entity) {
		assert request != null;
		assert entity != null;

		entity.setUserAccount(this.repository.findOneUserAccountById(request.getPrincipal().getAccountId()));
		this.repository.save(entity);
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
