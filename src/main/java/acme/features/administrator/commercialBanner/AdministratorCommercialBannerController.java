
package acme.features.administrator.commercialBanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.CommercialBanner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/commercial-banner/")
public class AdministratorCommercialBannerController extends AbstractController<Administrator, CommercialBanner> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	private AdministratorCommercialBannerListService	listService;

	@Autowired
	private AdministratorCommercialBannerShowService	showService;
	@Autowired
	private AdministratorCommercialBannerUpdateService	updateService;
	@Autowired
	private AdministratorCommercialBannerCreateService	createService;
	@Autowired
	private AdministratorCommercialBannerDeleteService	deleteService;


	// Constructors ----------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
