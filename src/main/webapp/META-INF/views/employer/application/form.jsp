<%--
- form.jsp
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

<acme:form>
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" readonly="true"/>
	<acme:form-moment  code="employer.application.form.label.moment" path="moment" readonly="true"/>
	<acme:form-moment  code="employer.application.form.label.lastUpdate" path="lastUpdate" readonly="true"/>	
	<jstl:if test="${status != 'PENDING' && command == 'show'}">
		<acme:form-textbox  code="employer.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status == 'PENDING' || command == 'update'}">
		<acme:form-select  code="employer.application.form.label.status" path="status" readonly="false">
			<acme:form-option code="employer.application.form.label.status.accepted" value="ACCEPTED"/>
			<acme:form-option code="employer.application.form.label.status.rejected" value="REJECTED"/>
		</acme:form-select>
	</jstl:if>
	<acme:form-textbox  code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textbox  code="employer.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textbox  code="employer.application.form.label.qualifications" path="qualifications" readonly="true"/>
	
	<jstl:if test="${status == 'REJECTED' && command == 'show'}">
		<acme:form-textarea  code="employer.application.form.label.rejectReason" path="rejectReason" readonly="true" placeholder="Reject Reason"/>
	</jstl:if>
	
	<jstl:if test="${status == 'PENDING' || command == 'update'}">
		<acme:form-textarea  code="employer.application.form.label.rejectReason" path="rejectReason" readonly="false" placeholder="Reject Reason"/>
	</jstl:if>
	
	<acme:form-submit test="${status == 'PENDING' || command == 'update'}" 
		code="employer.application.form.button.update"
		action="/employer/application/update"/>
	
	<acme:form-return code="employer.application.form.button.return"/>
</acme:form>
