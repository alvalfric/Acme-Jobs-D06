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
	<acme:form-textbox code="title" path="title" placeholder="Example Title"/>
	<acme:form-textbox code="description" path="description" placeholder="Example Description"/>
	<acme:form-textbox code="percentage" path="percentage" placeholder="30"/>
	
	<acme:form-submit test="${command == 'create' && finalMode != true}" 
	code="authenticated.employer.form.button.create" action="/employer/duty/create?jobId=${jobId}"/>
	<acme:form-submit test="${command == 'show' && finalMode != true}" 
	code="authenticated.employer.form.button.update" action="/employer/duty/update?dutyId=${dutyId}"/>
	<acme:form-submit test="${command == 'show' && finalMode != true}" 
	code="authenticated.employer.form.button.delete" action="/employer/duty/delete"/>
	<acme:form-submit test="${command == 'update' && finalMode != true}" 
	code="authenticated.employer.form.button.update" action="/employer/duty/update?dutyId=${dutyId}"/>
	<acme:form-submit test="${command == 'update'&& finalMode != true}" 
	code="authenticated.employer.form.button.delete" action="/employer/duty/delete"/>
	
	<acme:form-return code="authenticated.employer.form.button.return"/>
</acme:form>
