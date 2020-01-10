
package acme.features.authenticated.message;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.threads.Message;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message/")
public class AuthenticatedMessageController extends AbstractController<Authenticated, Message> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	private AuthenticatedMessageShowService			showService;
	@Autowired
	private AuthenticatedMessageListRelatedService	listRelatedService;
	@Autowired
	private AuthenticatedMessageCreateService		createService;


	// Constructors ----------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_RELATED, BasicCommand.LIST, this.listRelatedService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
