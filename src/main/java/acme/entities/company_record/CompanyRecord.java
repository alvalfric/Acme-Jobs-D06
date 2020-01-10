
package acme.entities.company_record;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "starScore")
})
public class CompanyRecord extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				companyName;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				CEOName;

	@NotBlank
	private String				activitiesDescription;

	@NotBlank
	@URL
	private String				website;

	@NotBlank
	@Pattern(regexp = "([+]([1-9]|[1-9][0-9]|[1-9][0-9][0-9])[\\s][(]([0-9]|[0-9][0-9]|[0-9][0-9][0-9]|[0-9][0-9][0-9][0-9])[)][\\s]\\d{6,10})$")
	private String				contactPhone;

	@NotBlank
	@Email
	private String				contactEmail;

	private Boolean				incorporated;

	@Range(min = 0, max = 5)
	private Double				starScore;
}
