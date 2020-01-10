<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="consumer.offer.form.label.title" path="title" placeholder="Example Title"/>
	<acme:form-moment code="consumer.offer.form.label.deadline" path="deadline" placeholder="Example deadline"/>
	<acme:form-textarea code="consumer.offer.form.label.text" path="text" placeholder="Example Text"/>
	<acme:form-money code="consumer.offer.form.label.rewardMin" path="rewardMin" placeholder=20/>
	<acme:form-money code="consumer.offer.form.label.rewardMax" path="rewardMax"placeholder=70/>
	<acme:form-textbox code="consumer.offer.form.label.ticker" path="ticker" placeholder="OAAAA-12345"/>
	<acme:form-checkbox code="consumer.offer.form.label.confirm" path="confirm"/>
	
	<acme:form-submit test="${command == 'create'}" 
		code="consumer.offer.form.button.create"
		action="/consumer/offer/create"/>
	
	<acme:form-return code="consumer.offer.form.button.return"/>
</acme:form>