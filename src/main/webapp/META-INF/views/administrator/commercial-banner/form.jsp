<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="administrator.commercialBanner.form.label.targetURL" path="targetURL" />
	<acme:form-textbox code="administrator.commercialBanner.form.label.slogan" path="slogan" />
	<acme:form-textarea code="administrator.commercialBanner.form.label.picture" path="picture" />
	<acme:form-double code="administrator.commercialBanner.form.label.creditCardNumber" path="creditCardNumber" />
	<acme:form-double code="administrator.commercialBanner.form.label.ccValidationNumber" path="ccValidationNumber" />
	<acme:form-double code="administrator.commercialBanner.form.label.ccExpirationDate" path="ccExpirationDate" />
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.commercialBanner.form.button.update"
		action="/administrator/commercial-banner/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.commercialBanner.form.button.delete"
		action="/administrator/commercial-banner/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.commercialBanner.form.button.create"
		action="/administrator/commercial-banner/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.commercialBanner.form.button.update"
		action="/administrator/commercial-banner/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.commercialBanner.form.button.delete"
		action="/administrator/commercial-banner/delete"/>
	<acme:form-return code="administrator.commercialBanner.form.button.return"/>
</acme:form>