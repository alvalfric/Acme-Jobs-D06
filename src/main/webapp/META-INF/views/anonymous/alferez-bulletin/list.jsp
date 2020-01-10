<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list readonly="true">
	<acme:list-column code="anonymous.alferez-bulletin.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.alferez-bulletin.list.label.author" path="author" width="15%" />
	<acme:list-column code="anonymous.alferez-bulletin.list.label.type" path="type" width="15%" />
	<acme:list-column code="anonymous.alferez-bulletin.list.label.url" path="url" width="60%" />
</acme:list>