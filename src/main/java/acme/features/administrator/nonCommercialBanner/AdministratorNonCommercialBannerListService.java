
package acme.features.administrator.nonCommercialBanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorNonCommercialBannerListService implements AbstractListService<Administrator, NonCommercialBanner> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	AdministratorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "targetURL");
	}

	@Override
	public Collection<NonCommercialBanner> findMany(final Request<NonCommercialBanner> request) {
		assert request != null;

		Collection<NonCommercialBanner> result;

		result = this.repository.findManyAll();

		return result;
	}

}
