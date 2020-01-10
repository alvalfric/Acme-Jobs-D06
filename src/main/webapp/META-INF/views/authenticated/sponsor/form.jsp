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
	<acme:form-textbox code="authenticated.sponsor.form.label.company" path="company" placeholder="Company S.L"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.creditCardNumber" path="creditCardNumber" placeholder="4929280904778"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.ccExpirationDate" path="ccExpirationDate" placeholder="22/2"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.ccValidationNumber" path="ccValidationNumber" placeholder= "900"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.form.button.create" action="/authenticated/sponsor/create"/>
	
	<acme:form-submit test="${command == 'update'}" code="authenticated.sponsor.form.button.update" action="/authenticated/sponsor/update"/>
	<acme:form-return code="authenticated.sponsor.form.button.return"/>
</acme:form>