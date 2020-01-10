<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="employer.application.list.label.reference" path="reference" width="40%"/>
	<acme:list-column code="employer.application.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="employer.application.list.label.status" path="status" width="20%"/>
	<acme:list-column code="employer.application.list.label.lastUpdate" path="lastUpdate" width="20%"/>
</acme:list>

<input type="button" class="btn btn-default" onclick="location.href='/acme-jobs/employer/application/list-grouped?indexStatus=${'ACCEPTED'}'" value="<acme:message code="employer.application.form.button.status.accepted"/>">
<input type="button" class="btn btn-default" onclick="location.href='/acme-jobs/employer/application/list-grouped?indexStatus=${'REJECTED'}'" value="<acme:message code="employer.application.form.button.status.rejected"/>">
<input type="button" class="btn btn-default" onclick="location.href='/acme-jobs/employer/application/list-grouped?indexStatus=${'PENDING'}'" value="<acme:message code="employer.application.form.button.status.pending"/>">
<input type="button" class="btn btn-default" onclick="location.href='/acme-jobs/employer/application/list-mine'" value="<acme:message code="employer.application.form.button.status.all"/>">


