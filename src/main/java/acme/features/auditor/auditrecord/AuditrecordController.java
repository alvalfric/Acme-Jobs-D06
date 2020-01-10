
package acme.features.auditor.auditrecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditrecord.Auditrecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/auditrecord/")
public class AuditrecordController extends AbstractController<Auditor, Auditrecord> {

	@Autowired
	private AuditrecordShowService		showService;

	@Autowired
	private AuditrecordListMineService	listMineService;

	@Autowired
	private AuditrecordCreateService	createService;

	@Autowired
	private AuditrecordUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);

	}

}
