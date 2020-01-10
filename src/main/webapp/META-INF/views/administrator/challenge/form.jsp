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
	<acme:form-textbox code="administrator.challenge.form.label.title" path="title"/>
	<acme:form-moment  code="administrator.challenge.form.label.deadline" path="deadline" />
	<acme:form-select code="administrator.challenge.form.label.reward" path="reward">
		<acme:form-option code="administrator.challenge.form.label.reward.oro" value="Gold" />
		<acme:form-option code="administrator.challenge.form.label.reward.plata" value="Silver" />
		<acme:form-option code="administrator.challenge.form.label.reward.bronce" value="Bronze " />
	</acme:form-select>
	<acme:form-select code="administrator.challenge.form.label.goal" path="goal">
		<acme:form-option code="administrator.challenge.form.label.goal.primero" value="First" />
		<acme:form-option code="administrator.challenge.form.label.goal.segundo" value="Second" />
		<acme:form-option code="administrator.challenge.form.label.goal.tercero" value="Third " />
	</acme:form-select>

	<acme:form-textarea  code="administrator.challenge.form.label.description" path="description"/>
	
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.challenge.form.button.update"
		action="/administrator/challenge/update"/>
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.challenge.form.button.delete"
		action="/administrator/challenge/delete"/>
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.challenge.form.button.create"
		action="/administrator/challenge/create"/>
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.challenge.form.button.update"
		action="/administrator/challenge/update"/>
	<acme:form-submit test="${command == 'delete'}" 
		code="administrator.challenge.form.button.delete"
		action="/administrator/challenge/delete"/>
	
	
	<acme:form-return code="administrator.challenge.form.button.return"/>
</acme:form>
