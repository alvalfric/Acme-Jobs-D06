
package acme.features.anonymous.chellikBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.chellikBulletin.ChellikBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/chellik-bulletin/")
public class AnonymousChellikBulletinController extends AbstractController<Anonymous, ChellikBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousChellikBulletinListService		listService;
	@Autowired
	private AnonymousChellikBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
