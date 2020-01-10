
package acme.entities.threads;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Thread extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	// Relationships---------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Authenticated>	users;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		creator;

	@OneToMany(mappedBy = "thread", fetch = FetchType.EAGER)
	private Set<@Valid Message>	messages;
}
