<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="anonymous.alferez-bulletin.form.label.author" path="author" />
	<acme:form-select code="anonymous.alferez-bulletin.form.label.type" path="type">
		<acme:form-option code="Music" value="Music"/>
		<acme:form-option code="Video" value="Video"/>
		<acme:form-option code="Others" value="Others"/>
	</acme:form-select>
	<acme:form-textarea code="anonymous.alferez-bulletin.form.label.url" path="url" />
	
	<acme:form-submit code="anonymous.alferez-bulletin.form.button.create" action="/anonymous/alferez-bulletin/create/" />
	<acme:form-return code="anonymous.alferez-bulletin.form.button.return"/>
</acme:form>