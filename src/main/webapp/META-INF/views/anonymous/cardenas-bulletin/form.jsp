<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="anonymous.cardenas-bulletin.form.label.book" path="book" />
	<acme:form-textarea code="anonymous.cardenas-bulletin.form.label.author" path="author" />
	<acme:form-textarea code="anonymous.cardenas-bulletin.form.label.genre" path="genre" />
	<acme:form-textarea code="anonymous.cardenas-bulletin.form.label.isbn" path="isbn" />
	
	<acme:form-submit code="anonymous.cardenas-bulletin.form.button.create" action="/anonymous/cardenas-bulletin/create/" />
	<acme:form-return code="anonymous.cardenas-bulletin.form.button.return"/>
</acme:form>