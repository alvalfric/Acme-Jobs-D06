
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Length(min = 5, max = 23)
	@Pattern(regexp = "^([A-Z0-9]{1,7}-[A-Z0-9]{1,7}:[A-Z0-9]{1,7})$")
	private String				reference;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				lastUpdate;

	@NotBlank
	private String				status;

	@NotBlank
	private String				statement;

	@NotBlank
	private String				skills;

	@NotBlank
	private String				qualifications;

	private String				rejectReason;

	//Relationships ------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

}
