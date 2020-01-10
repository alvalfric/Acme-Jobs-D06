
package acme.features.authenticated.requests;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requests.Requests;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("authenticated/requests/")
public class AuthenticatedRequestsController extends AbstractController<Authenticated, Requests> {

	// Internal state ---------------------------------------------------

	@Autowired
	private AuthenticatedRequestsListService	listService;

	@Autowired
	private AuthenticatedRequestsShowService	showService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
