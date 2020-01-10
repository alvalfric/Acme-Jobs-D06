
package acme.entities.customisationParameters;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomisationParameter extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				spamWordsEn;

	@NotBlank
	private String				spamWordsEs;

	@Range(min = 0, max = 100)
	private Double				spamThreshold;

}
