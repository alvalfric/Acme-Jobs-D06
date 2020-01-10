<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list readonly="true">
	<acme:list-column code="anonymous.rosado-bulletin.list.label.name" path="name" width="20%" />
	<acme:list-column code="anonymous.rosado-bulletin.list.label.image" path="image" width="20%" />
	<acme:list-column code="anonymous.rosado-bulletin.list.label.video" path="video" width="60%" />
</acme:list>