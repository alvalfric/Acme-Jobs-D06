<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="anonymous.chellik-bulletin.form.label.nombre" path="nombre" />
	<acme:form-textbox code="anonymous.chellik-bulletin.form.label.apellido" path="apellido" />
	<acme:form-select code="anonymous.chellik-bulletin.form.label.role" path="role">
		<acme:form-option code="anonymous.chellik-bulletin.form.label.role.developer" value="Developer" />
		<acme:form-option code="anonymous.chellik-bulletin.form.label.role.analist" value="Analist" />
		<acme:form-option code="anonymous.chellik-bulletin.form.label.role.manager" value="Manager" />
		<acme:form-option code="anonymous.chellik-bulletin.form.label.role.tester" value="Tester" />
		<acme:form-option code="anonymous.chellik-bulletin.form.label.role.operator" value="Operator" />
	</acme:form-select>
	<acme:form-moment code="anonymous.chellik-bulletin.form.label.fecha" path="fecha" />
	
	<acme:form-submit code="anonymous.chellik-bulletin.form.button.create" action="/anonymous/chellik-bulletin/create/" />
	<acme:form-return code="anonymous.chellik-bulletin.form.button.return"/>
</acme:form>