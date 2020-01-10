
package acme.features.anonymous.cardenasBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.cardenasBulletin.CardenasBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/cardenas-bulletin/")
public class AnonymousCardenasBulletinController extends AbstractController<Anonymous, CardenasBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousCardenasBulletinListService	listService;
	@Autowired
	private AnonymousCardenasBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
