
package acme.features.anonymous.alferezBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.alferezBulletin.AlferezBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/alferez-bulletin/")
public class AnonymousAlferezBulletinController extends AbstractController<Anonymous, AlferezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousAlferezBulletinListService	listService;
	@Autowired
	private AnonymousAlferezBulletinService		createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
