<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
      		<acme:menu-suboption code="master.menu.anonymous.announcement" action="/anonymous/announcement/list"/>
      		<acme:menu-suboption code="master.menu.anonymous.investorRecord.list" action="/anonymous/investor-record/list"/>
	      	<acme:menu-suboption code="master.menu.authenticated.company-records" action="/anonymous/company-record/list"/>
	      	<acme:menu-suboption code="master.menu.anonymous.investorRecord.list" action="/anonymous/investor-record/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
	       <acme:menu-suboption code="master.menu.authenticated.announcement" action="/authenticated/announcement/list"/>
	       <acme:menu-suboption code="master.menu.authenticated.investorRecord.list" action="/authenticated/investor-record/list"/>
	       <acme:menu-suboption code="master.menu.authenticated.company-records" action="/authenticated/company-record/list"/>
           <acme:menu-suboption code="master.menu.authenticated.requests" action="/authenticated/requests/list"/>
           <acme:menu-suboption code="master.menu.authenticated.challenge" action="/authenticated/challenge/list"/>
           <acme:menu-suboption code="master.menu.authenticated.offer" action="/authenticated/offer/list"/>
           <acme:menu-suboption code="master.menu.authenticated.job" action="/authenticated/job/list"/>
           <acme:menu-suboption code="master.menu.authenticated.thread" action="/authenticated/thread/list-mine"/>
           <acme:menu-separator/>
	       <acme:menu-suboption code="master.menu.authenticated.thread.list.created" action="/authenticated/thread/list-created"/>
	       <acme:menu-suboption code="master.menu.authenticated.thread.create" action="/authenticated/thread/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.announcement" action="/administrator/announcement/list"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.challenge" action="/administrator/challenge/list"/>
			
			<acme:menu-suboption code="master.menu.administrator.company-records" action="/administrator/company-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.customisation-parameter" action="/administrator/customisation-parameter/list"/>

			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"/>

			<acme:menu-suboption code="master.menu.administrator.investorRecords" action="/administrator/investor-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.requestAuditor" action="/administrator/request-auditor/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create"/>
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create"/>
			
			<acme:menu-suboption code="master.menu.administrator.commercialBanner.create" action="/administrator/commercial-banner/create"/>
			<acme:menu-suboption code="master.menu.administrator.nonCommercialBanner.create" action="/administrator/non-commercial-banner/create"/>
			
			<acme:menu-suboption code="master.menu.administrator.company-records.create" action="/administrator/company-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.investorRecord.create" action="/administrator/investor-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.job" action="/employer/job/list-mine"/>
			<acme:menu-suboption code="master.menu.employer.application" action="/employer/application/list-mine"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.application" action="/worker/application/list-mine"/>
			<acme:menu-suboption code="master.menu.worker.job" action="/worker/job/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
	       <acme:menu-suboption code="master.menu.auditor.auditrecord" action="/auditor/auditrecord/list-mine"/>
	       <acme:menu-suboption code="master.menu.auditor.job.pending" action="/auditor/job/list-pending"/>
	       <acme:menu-suboption code="master.menu.auditor.job.done" action="/auditor/job/list-done"/>
	</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<%--
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
			<acme:menu-separator/>
			--%>
			<acme:menu-suboption code="master.menu.consumer.offer.create" action="/consumer/offer/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.commercialBanner" action="/sponsor/commercial-banner/list-mine"/>
			<acme:menu-suboption code="master.menu.sponsor.nonCommercialBanner" action="/sponsor/non-commercial-banner/list-mine"/>
		
			<acme:menu-suboption code="master.menu.sponsor.commercialBanner.create" action="/sponsor/commercial-banner/create"/>
			<acme:menu-suboption code="master.menu.sponsor.nonCommercialBanner.create" action="/sponsor/non-commercial-banner/create"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create" access="!hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update" access="hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create" access="!hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update" access="hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create" access="!hasRole('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update" access="hasRole('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/request-auditor/create" access="!hasRole('RequestAuditor') && !hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/request-auditor/update" access="hasRole('RequestAuditor') && !hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>