
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.roles.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$")
	private String				creditCardNumber;

	@NotBlank
	@Pattern(regexp = "^\\d{1,2}\\/\\d{1,2}$")
	private String				ccExpirationDate;

	@NotBlank
	@Pattern(regexp = "^\\d\\d\\d$")
	private String				ccValidationNumber;

	// Relationships -------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;

}
