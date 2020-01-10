/*
 * AdministratorUserAccountListService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.requestAuditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestAuditors.RequestAuditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorRequestAuditorListService implements AbstractListService<Administrator, RequestAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorRequestAuditorRepository repository;


	// AbstractListService<Administrator, UserAccount> interface --------------

	@Override
	public boolean authorise(final Request<RequestAuditor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<RequestAuditor> request, final RequestAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		UserAccount ua = entity.getUserAccount();
		model.setAttribute("username", ua.getUsername());

		request.unbind(entity, model, "firm", "responsabilityStat");

	}

	@Override
	public Collection<RequestAuditor> findMany(final Request<RequestAuditor> request) {
		assert request != null;

		Collection<RequestAuditor> result;

		result = this.repository.findAllRequestAuditor();

		return result;
	}

}
