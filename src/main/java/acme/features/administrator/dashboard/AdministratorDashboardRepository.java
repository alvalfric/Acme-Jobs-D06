/*
 * AdministratorUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Announcement a")
	Integer countNumberOfAnnouncements();

	@Query("select count(cr) from CompanyRecord cr")
	Integer countNumberOfCompanyRecords();

	@Query("select count(ir) from InvestorRecord ir")
	Integer countNumberOfInvestorRecords();

	@Query("select min(r.reward.amount) from Requests r where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double mininumRewardOfActiveRequests();

	@Query("select max(r.reward.amount) from Requests r where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double maximumRewardOfActiveRequests();

	@Query("select avg(r.reward.amount) from Requests r where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double averageRewardOfActiveRequests();

	@Query("select min(o.rewardMin.amount) from Offer o where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double mininumRewardOfActiveOffers();

	@Query("select max(o.rewardMax.amount) from Offer o where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double maximumRewardOfActiveOffers();

	@Query("select avg((o.rewardMax.amount + o.rewardMin.amount)/2) from Offer o where TIMESTAMPDIFF(DAY, CURRENT_DATE(), deadline)>=0")
	Double averageRewardOfActiveOffers();

	@Query("select cr.sector, count(cr) from CompanyRecord cr group by cr.sector")
	String[][] countNumberOfCompanyRecordsGroupedBySector();

	@Query("select ir.sector, count(ir) from InvestorRecord ir group by ir.sector")
	String[][] countNumberOfInvestorRecordsGroupedBySector();
}
