
package acme.entities.roles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.auditrecord.Auditrecord;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditor extends UserRole {

	/**
	 *
	 */
	private static final long				serialVersionUID	= 1L;

	@NotBlank
	private String							firm;

	@NotBlank
	private String							responsabilityStat;

	private boolean							status;

	@OneToMany(mappedBy = "auditor", fetch = FetchType.EAGER)
	private Collection<@Valid Auditrecord>	auditrecords;

}
