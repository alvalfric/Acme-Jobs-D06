<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="administrator.nonCommercialBanner.form.label.targetURL" path="targetURL" />
	<acme:form-textbox code="administrator.nonCommercialBanner.form.label.slogan" path="slogan" />
	<acme:form-textarea code="administrator.nonCommercialBanner.form.label.picture" path="picture" />
	<acme:form-double code="administrator.nonCommercialBanner.form.label.jingle" path="jingle" />
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.nonCommercialBanner.form.button.update"
		action="/administrator/non-commercial-banner/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.nonCommercialBanner.form.button.delete"
		action="/administrator/non-commercial-banner/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.nonCommercialBanner.form.button.create"
		action="/administrator/non-commercial-banner/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.nonCommercialBanner.form.button.update"
		action="/administrator/non-commercial-banner/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.nonCommercialBanner.form.button.delete"
		action="/administrator/non-commercial-banner/delete"/>
	<acme:form-return code="administrator.nonCommercialBanner.form.button.return"/>
</acme:form>