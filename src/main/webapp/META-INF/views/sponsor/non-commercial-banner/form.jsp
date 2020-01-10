
<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="sponsor.nonCommercialBanner.form.label.targetURL" path="targetURL" />
	<acme:form-textbox code="sponsor.nonCommercialBanner.form.label.slogan" path="slogan" />
	<acme:form-textarea code="sponsor.nonCommercialBanner.form.label.picture" path="picture" />
	<acme:form-double code="sponsor.nonCommercialBanner.form.label.jingle" path="jingle" />
	
	<acme:form-submit test="${command == 'show'}"
		code="sponsor.nonCommercialBanner.form.button.update"
		action="/sponsor/non-commercial-banner/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="sponsor.nonCommercialBanner.form.button.delete"
		action="/sponsor/non-commercial-banner/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="sponsor.nonCommercialBanner.form.button.create"
		action="/sponsor/non-commercial-banner/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="sponsor.nonCommercialBanner.form.button.update"
		action="/sponsor/non-commercial-banner/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="sponsor.nonCommercialBanner.form.button.delete"
		action="/sponsor/non-commercial-banner/delete"/>
	<acme:form-return code="sponsor.nonCommercialBanner.form.button.return"/>
</acme:form>