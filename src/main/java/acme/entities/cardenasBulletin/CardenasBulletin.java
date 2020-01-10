
package acme.entities.cardenasBulletin;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CardenasBulletin extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				book;

	@NotBlank
	private String				author;

	@NotBlank
	private String				genre;

	@NotBlank
	private String				isbn;

}
