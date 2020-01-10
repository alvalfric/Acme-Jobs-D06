
package acme.features.provider.requests;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requests.Requests;
import acme.entities.roles.Provider;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/provider/requests/")
public class ProviderRequestsController extends AbstractController<Provider, Requests> {

	// Internal state ---------------------------------------------------

	@Autowired
	private ProviderRequestsCreateService createService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
