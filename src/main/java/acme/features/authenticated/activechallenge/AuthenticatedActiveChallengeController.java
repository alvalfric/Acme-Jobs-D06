
package acme.features.authenticated.activechallenge;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.challenges.Challenge;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticatedactivechallenge/activechallenge/")
public class AuthenticatedActiveChallengeController extends AbstractController<Authenticated, Challenge> {

	@Autowired
	private AuthenticatedActiveChallengeListService	listService;
	@Autowired
	private AuthenticatedActiveChallengeShowService	showService;


	@PostConstruct
	private void initimize() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
