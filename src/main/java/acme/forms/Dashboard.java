
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier --------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------

	Integer						totalNumberOfAnnouncements;
	Integer						totalNumberOfCompanyRecords;
	Integer						totalNumberOfInvestorRecords;
	Double						mininumRewardOfActiveRequests;
	Double						maximumRewardOfActiveRequests;
	Double						averageRewardOfActiveRequests;
	Double						standardDeviationOfActiveRequests;
	Double						mininumRewardOfActiveOffers;
	Double						maximumRewardOfActiveOffers;
	Double						averageRewardOfActiveOffers;
	Double						standardDeviationOfActiveOffers;
	String[][]					totalNumberOfCompanyRecordsGroupedBySector;
	String[][]					totalNumberOfInvestorRecordsGroupedBySector;
	List<List<String>>			chartCompanyInvestor;
}
