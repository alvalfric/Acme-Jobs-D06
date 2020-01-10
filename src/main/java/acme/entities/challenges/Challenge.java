
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline"), @Index(columnList = "title, deadline")
})
public class Challenge extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Future
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotBlank
	private String				goal;

	@NotBlank
	private String				reward;


	@Transient
	public String getRewardGoal() {
		StringBuilder result;
		result = new StringBuilder();
		result.append(this.reward);
		result.append(" / ");
		result.append(this.goal);

		return result.toString();

	}

}
