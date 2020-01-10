
package acme.entities.jobs;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.applications.Application;
import acme.entities.auditrecord.Auditrecord;
import acme.entities.roles.Employer;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job extends DomainEntity {

	// Serialisation identifier -------------------------------------------

	private static final long				serialVersionUID	= 1L;

	// Attributes ---------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Length(min = 3, max = 15)
	@Pattern(regexp = "^([A-Z0-9]{1,7}-[A-Z0-9]{1,7})$")
	private String							reference;

	@NotBlank
	private String							title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date							deadline;

	@NotNull
	@Valid
	private Money							salary;

	@URL
	private String							moreInfo;

	private boolean							finalMode;

	// Relationships ------------------------------------------------------

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Descriptor						descriptor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Employer						employer;

	@OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
	private Set<@Valid Application>			applications;

	@OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
	private Collection<@Valid Auditrecord>	auditrecords;

}
