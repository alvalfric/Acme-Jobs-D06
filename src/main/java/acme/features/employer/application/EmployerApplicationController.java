
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application/")
public class EmployerApplicationController extends AbstractController<Employer, Application> {

	@Autowired
	private EmployerApplicationListMineService		listService;
	@Autowired
	private EmployerApplicationShowService			showService;

	@Autowired
	private EmployerApplicationUpdateService		updateService;

	@Autowired
	private EmployerApplicationListByStatusService	listGroupedService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_GROUPED, BasicCommand.LIST, this.listGroupedService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
