
package acme.entities.threads;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class Message extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotBlank
	private String				body;

	private String				tags;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	// Relationships---------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		user;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Thread				thread;
}
