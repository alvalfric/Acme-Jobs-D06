
package acme.entities.jobs;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	// Serialisation identifier -------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes ---------------------------------------------------------

	@NotBlank
	private String description;

	// Relationships ------------------------------------------------------

	@OneToMany(mappedBy = "descriptor", fetch = FetchType.EAGER)
	private Set<@Valid Duty> duties;
}
