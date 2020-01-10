<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list readonly="true">
	<acme:list-column code="anonymous.chellik-bulletin.list.label.nombre" path="nombre" width="20%" />
	<acme:list-column code="anonymous.chellik-bulletin.list.label.apellido" path="apellido" width="20%" />
	<acme:list-column code="anonymous.chellik-bulletin.list.label.role" path="role" width="20%" />
	<acme:list-column code="anonymous.chellik-bulletin.list.label.fecha" path="fecha" width="20%" />

</acme:list>