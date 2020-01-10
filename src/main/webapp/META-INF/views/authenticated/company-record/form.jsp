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

<acme:form readonly="true">
	
	<acme:form-textbox code="authenticated.company-record.form.label.companyName" path="companyName" />
	<acme:form-textbox code="authenticated.company-record.form.label.sector" path="sector" />
	<acme:form-textbox code="authenticated.company-record.form.label.CEOName" path="CEOName"/>
	<acme:form-textarea code="authenticated.company-record.form.label.activitiesDescription" path="activitiesDescription"/>		
	<acme:form-url code="authenticated.company-record.form.label.website" path="website"/>		
	<acme:form-textbox code="authenticated.company-record.form.label.contactPhone" path="contactPhone"/>		
	<acme:form-textbox code="authenticated.company-record.form.label.contactEmail" path="contactEmail"/>
	<acme:form-double code="authenticated.company-record.form.label.starScore" path="starScore"/>				

	<acme:form-return code="authenticated.company-record.form.button.return"/>
</acme:form>
