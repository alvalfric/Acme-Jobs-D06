
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
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedRequestAuditorCreateService implements AbstractCreateService<Authenticated, RequestAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedRequestAuditorRepository repository;


	// AbstractCreateService<Authenticated, Provider> interface ---------------

	@Override
	public boolean authorise(final Request<RequestAuditor> request) {
		assert request != null;
		boolean result = true;

		Principal principal = request.getPrincipal();

		boolean hasRequest = this.repository.countAuditorRequestByUserAccountId(principal.getAccountId()) >= 1;
		boolean isAuditor = this.repository.findOneAuditorByUserAccountId(request.getPrincipal().getAccountId()) != null;

		if (isAuditor) {
			result = false;
		} else if (hasRequest) {
			result = false;
		}

		System.out.println("hasRequest " + hasRequest + " isAuditor " + isAuditor);
		return result;
	}

	@Override
	public void bind(final Request<RequestAuditor> request, final RequestAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		entity.setUserAccount(this.repository.findOneUserAccountById(request.getPrincipal().getAccountId()));
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
		Principal principal;
		//		int userAccountId;
		//		UserAccount userAccount;

		principal = request.getPrincipal();

		result = new RequestAuditor();
		result.setUserAccount(this.repository.findOneUserAccountById(request.getPrincipal().getAccountId()));

		//		userAccountId = principal.getAccountId();
		//		userAccount = this.repository.findOneUserAccountById(userAccountId);
		//		result.setUserAccount(userAccount);

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
