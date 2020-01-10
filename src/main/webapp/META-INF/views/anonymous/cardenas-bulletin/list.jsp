<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list readonly="true">
	<acme:list-column code="anonymous.cardenas-bulletin.list.label.book" path="book" width="25%" />
	<acme:list-column code="anonymous.cardenas-bulletin.list.label.author" path="author" width="25%" />
	<acme:list-column code="anonymous.cardenas-bulletin.list.label.genre" path="genre" width="25%" />
	<acme:list-column code="anonymous.cardenas-bulletin.list.label.isbn" path="isbn" width="25%" />
</acme:list>