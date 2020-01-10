
<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.dashboard.form.label.announcements" path="totalNumberOfAnnouncements" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label.companyRecords" path="totalNumberOfCompanyRecords" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label.investorRecords" path="totalNumberOfInvestorRecords" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label.minActiveRequests" path="mininumRewardOfActiveRequests" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label.maxActiveRequests" path="maximumRewardOfActiveRequests" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label.avgActiveRequests" path="averageRewardOfActiveRequests" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label.minOffers" path="mininumRewardOfActiveOffers" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label.maxOffers" path="maximumRewardOfActiveOffers" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label.avgOffers" path="averageRewardOfActiveOffers" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label." path="totalNumberOfCompanyRecordsGroupedBySector" readonly="true" />
	<acme:list-column code="administrator.dashboard.form.label." path="totalNumberOfInvestorRecordsGroupedBySector" readonly="true" />
</acme:list>


