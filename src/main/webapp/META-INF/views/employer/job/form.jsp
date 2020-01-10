<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<style>
table {
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #e9ecef;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #e9ecef;
}
</style>

<acme:form>
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EMP1234-JOB1234"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title" placeholder="Example Title"/>
	<jstl:if test= "${finalMode != true}">
	<acme:form-select code="employer.job.form.label.status" path="finalMode">
		<acme:form-option code="employer.job.form.label.publish-draft" value="false"/>
		<acme:form-option code="employer.job.form.label.publish-published" value="true"/>
	</acme:form-select>
	</jstl:if>
	<jstl:if test= "${finalMode == true}">
		<b><acme:message code="employer.job.form.label.status"/></b><b>: </b><acme:message code="employer.job.form.label.publish-published"/>
		<br>
		<br>
	</jstl:if>
	<acme:form-textbox code="employer.job.form.label.deadline" path="deadline" placeholder="2020/10/10 10:10"/>
	<acme:form-textbox code="employer.job.form.label.salary" path="salary" placeholder="20"/>
	<acme:form-textbox code="employer.job.form.label.moreInfo" path="moreInfo" placeholder = "http//:example.com"/>
	<acme:form-textarea code="employer.job.form.label.description" path="descriptor.description" placeholder="Example Description"/>
	<jstl:if test= "${descriptor.id != null && !descriptor.getDuties().isEmpty()}">
		<b><acme:message code="employer.job.form.label.duty"/></b>
		<table>
			<tr>
				<th><acme:message code="employer.job.form.label.duty.title"/></th>
				<th><acme:message code="employer.job.form.label.duty.description"/></th>
				<th><acme:message code="employer.job.form.label.duty.percentage"/></th>
			</tr>
			<jstl:forEach var="duty" items="${descriptor.duties}">
			<tr>
				<td>${duty.title}</td>
				<td>${duty.description}</td>
				<td>${duty.percentage}</td>
				<jstl:if test= "${command == 'show' && descriptor.id != null && finalMode != true}">
				<td>
				<acme:form-submit method="get" test="${command == 'show' && descriptor.id != null && finalMode != true}" 
					code="employer.job.form.label.duty.go"
					action="/employer/duty/show?dutyId=${duty.id}"/>
				</td>
				</jstl:if>
			</tr>
			</jstl:forEach>
		</table>
		<acme:form-errors path="errorDuty"/>
		<br>

	</jstl:if>

	<acme:form-submit test="${command == 'show' && finalMode != true}" 
		code="administrator.announcement.form.button.update"
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.announcement.form.button.delete"
		action="/employer/job/delete"/>
	<acme:form-submit method="get" test="${command == 'show' && descriptor.id != null && finalMode != true}" 
		code="Create duty"
		action="/employer/duty/create?jobId=${id}"/>
	<acme:form-submit method="get" test="${command == 'show' && finalMode == true}" 
		code="employer.job.form.button.audit-record"
		action="/employer/auditrecord/list-mine?jobId=${id}"/>
	<acme:form-submit test="${command == 'create' && finalMode != true}" 
		code="administrator.announcement.form.button.create"
		action="/employer/job/create"/>
	<acme:form-submit test="${command == 'update' && finalMode != true}" 
		code="administrator.announcement.form.button.update"
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'delete' && applicationsEmpty == true}" 
		code="administrator.announcement.form.button.delete"
		action="/employer/job/delete"/>


	<%--
	
	<input type="button" class="btn btn-default" 
	onclick="location.href='/acme-jobs/employer/auditrecord/list-mine?jobId=${id}'"
	value="<acme:message code="employer.job.form.button.audit-record"/>">
	--%>
	<acme:form-return code="employer.job.form.button.return"/>	
</acme:form>