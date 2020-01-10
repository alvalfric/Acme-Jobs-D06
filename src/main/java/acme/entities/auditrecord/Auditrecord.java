
package acme.entities.auditrecord;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditrecord extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	private String				reference;

	@NotBlank
	private String				title;

	private boolean				status;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@NotBlank
	private String				body;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

	@NotNull
	@Valid
	@ManyToOne(optional = false)  // no es opcional porque audit record siempre tiene un auditor
	private Auditor				auditor;

}
