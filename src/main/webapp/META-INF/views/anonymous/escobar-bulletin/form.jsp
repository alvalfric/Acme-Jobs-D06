<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="anonymous.escobar-bulletin.form.label.author" path="author" />
	<acme:form-moment code="anonymous.escobar-bulletin.form.label.birthdate" path="birthdate" />
	<acme:form-select code="anonymous.escobar-bulletin.form.label.level" path="level">
		<acme:form-option code="anonymous.escobar-bulletin.form.label.level.middle-school" value="Middle School (Secundaria)" />
		<acme:form-option code="anonymous.escobar-bulletin.form.label.level.high-school" value="High School (Bachillerato)" />
		<acme:form-option code="anonymous.escobar-bulletin.form.label.level.vocational-training" value="Vocational Training (FP)" />
		<acme:form-option code="anonymous.escobar-bulletin.form.label.level.university-degree" value="University Degree (Grado Univ.)" />
		<acme:form-option code="anonymous.escobar-bulletin.form.label.level.university-master" value="University Master (Máster Univ.)" />
	</acme:form-select>
	<acme:form-textarea code="anonymous.escobar-bulletin.form.label.text" path="text" />
	
	<acme:form-submit code="anonymous.escobar-bulletin.form.button.create" action="/anonymous/escobar-bulletin/create/" />
	<acme:form-return code="anonymous.escobar-bulletin.form.button.return"/>
</acme:form>