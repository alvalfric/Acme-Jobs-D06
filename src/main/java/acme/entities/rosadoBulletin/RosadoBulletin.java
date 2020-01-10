
package acme.entities.rosadoBulletin;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RosadoBulletin extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@URL
	private String				image;

	@URL
	private String				video;

}
