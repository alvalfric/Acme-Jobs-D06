
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.roles.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NonCommercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	private String				jingle;

	// Relationships -------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;

}
