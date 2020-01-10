
package acme.entities.jobs;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Duty extends DomainEntity {
	//Serialisation identifier ----------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@NotNull
	@Range(min = (long) 0.0, max = (long) 100.0)
	private Double				percentage;

	// Relationships ------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Descriptor			descriptor;
}
