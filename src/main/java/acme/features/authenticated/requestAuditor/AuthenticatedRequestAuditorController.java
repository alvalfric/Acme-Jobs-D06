
package acme.features.authenticated.requestAuditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requestAuditors.RequestAuditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/request-auditor/")
public class AuthenticatedRequestAuditorController extends AbstractController<Authenticated, RequestAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedRequestAuditorCreateService	createService;

	@Autowired
	private AuthenticatedRequestAuditorUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
