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
	<acme:form-textbox code="authenticated.company-record.form.label.companyName" path="companyName"/>
	<acme:form-checkbox code="authenticated.company-record.form.label.incorporated" path="incorporated"/>
	<acme:form-textbox code="authenticated.company-record.form.label.sector" path="sector"/>
	<acme:form-textbox code="authenticated.company-record.form.label.CEOName" path="CEOName"/>
	<acme:form-textarea code="authenticated.company-record.form.label.activitiesDescription" path="activitiesDescription"/>		
	<acme:form-url code="authenticated.company-record.form.label.website" path="website"/>		
	<acme:form-textbox code="authenticated.company-record.form.label.contactPhone" path="contactPhone" placeholder="+123 (1234) 123456"/>		
	<acme:form-textbox code="authenticated.company-record.form.label.contactEmail" path="contactEmail"/>
	<acme:form-double code="authenticated.company-record.form.label.starScore" path="starScore" placeholder="5.0"/>				

	<acme:form-submit test="${command == 'show'}"
		code="administrator.company-record.form.button.update"
		action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.company-record.form.button.delete"
		action="/administrator/company-record/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.company-record.form.button.create"
		action="/administrator/company-record/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.company-record.form.button.update"
		action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.company-record.form.button.delete"
		action="/administrator/company-record/delete"/>
	<acme:form-return code="authenticated.company-record.form.button.return"/>
</acme:form>
