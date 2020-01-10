
package acme.features.administrator.customisationParameter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/customisation-parameter/")
public class AdministratorCustomisationParameterController extends AbstractController<Administrator, CustomisationParameter> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	private AdministratorCustomisationParameterListService		listService;

	@Autowired
	private AdministratorCustomisationParameterShowService		showService;

	@Autowired
	private AdministratorCustomisationParameterUpdateService	updateService;


	// Constructors ----------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
