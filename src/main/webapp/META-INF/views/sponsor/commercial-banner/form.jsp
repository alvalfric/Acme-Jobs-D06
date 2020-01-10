<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="sponsor.commercialBanner.form.label.targetURL" path="targetURL" />
	<acme:form-textbox code="sponsor.commercialBanner.form.label.slogan" path="slogan" />
	<acme:form-textarea code="sponsor.commercialBanner.form.label.picture" path="picture" />
	<acme:form-double code="sponsor.commercialBanner.form.label.creditCardNumber" path="creditCardNumber" />
	<acme:form-double code="sponsor.commercialBanner.form.label.ccValidationNumber" path="ccValidationNumber" />
	<acme:form-double code="sponsor.commercialBanner.form.label.ccExpirationDate" path="ccExpirationDate" />
	
	<acme:form-submit test="${command == 'show'}"
		code="sponsor.commercialBanner.form.button.update"
		action="/sponsor/commercial-banner/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="sponsor.commercialBanner.form.button.delete"
		action="/sponsor/commercial-banner/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="sponsor.commercialBanner.form.button.create"
		action="/sponsor/commercial-banner/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="sponsor.commercialBanner.form.button.update"
		action="/sponsor/commercial-banner/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="sponsor.commercialBanner.form.button.delete"
		action="/sponsor/commercial-banner/delete"/>
	<acme:form-return code="sponsor.commercialBanner.form.button.return"/>
</acme:form>