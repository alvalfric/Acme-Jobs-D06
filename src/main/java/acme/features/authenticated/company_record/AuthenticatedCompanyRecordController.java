
package acme.features.authenticated.company_record;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.company_record.CompanyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/company-record/")
public class AuthenticatedCompanyRecordController extends AbstractController<Authenticated, CompanyRecord> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	private AuthenticatedCompanyRecordListService	listService;

	@Autowired
	private AuthenticatedCompanyRecordShowService	showService;


	// Constructors ----------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
