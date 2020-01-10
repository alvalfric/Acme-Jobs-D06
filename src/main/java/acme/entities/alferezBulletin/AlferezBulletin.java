
package acme.entities.alferezBulletin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AlferezBulletin extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				author;

	@NotBlank
	private String				type;

	@NotBlank
	private String				url;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;
}
