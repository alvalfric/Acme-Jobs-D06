
package acme.features.authenticated.thread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.threads.Thread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/thread/")
public class AuthenticatedThreadController extends AbstractController<Authenticated, Thread> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	private AuthenticatedThreadListMineService		listMineService;

	@Autowired
	private AuthenticatedThreadListCreatedService	listCreatedService;

	@Autowired
	private AuthenticatedThreadShowService			showService;

	@Autowired
	private AuthenticatedThreadCreateService		createService;

	@Autowired
	private AuthenticatedThreadRemoveUserService	removeUserService;

	@Autowired
	private AuthenticatedThreadAddUserService		addUserService;


	// Constructors ----------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_CREATED, BasicCommand.LIST, this.listCreatedService);
		super.addCustomCommand(CustomCommand.REMOVE_USER, BasicCommand.UPDATE, this.removeUserService);
		super.addCustomCommand(CustomCommand.ADD_USER, BasicCommand.UPDATE, this.addUserService);
	}
}
