<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.requests.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.requests.form.label.creation" path="creation"/>
	<acme:form-moment code="authenticated.requests.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.requests.form.label.text" path="text"/>
	<acme:form-money code="authenticated.requests.form.label.reward" path="reward"/>
	<acme:form-textbox code="authenticated.requests.form.label.ticker" path="ticker"/>
	
	<acme:form-return code="authenticated.requests.form.button.return"/>
</acme:form>