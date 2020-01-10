
package acme.entities.roles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import acme.entities.banners.CommercialBanner;
import acme.entities.banners.NonCommercialBanner;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsor extends UserRole {

	// Serialisation identifier ---------------------------------------
	private static final long						serialVersionUID	= 1L;

	// Attributes -----------------------------------------------------

	@NotBlank
	private String									company;

	@Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$")
	private String									creditCardNumber;

	@Pattern(regexp = "^\\d{1,2}\\/\\d{1,2}$")
	private String									ccExpirationDate;

	@Pattern(regexp = "^\\d\\d\\d$")
	private String									ccValidationNumber;

	@OneToMany(mappedBy = "sponsor")
	private Collection<@Valid CommercialBanner>		commercialBanners;

	@OneToMany(mappedBy = "sponsor")
	private Collection<@Valid NonCommercialBanner>	nonCommercialBanners;

}
