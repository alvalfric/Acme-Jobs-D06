<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="anonymous.announcement.list.label.moment" path="moment" />
	<acme:list-column code="anonymous.announcement.list.label.title" path="title" />
</acme:list>