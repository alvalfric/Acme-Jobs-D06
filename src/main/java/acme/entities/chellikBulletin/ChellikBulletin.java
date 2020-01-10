
package acme.entities.chellikBulletin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChellikBulletin extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				nombre;

	@NotBlank
	private String				apellido;

	@NotBlank
	private String				role;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				fecha;

}
