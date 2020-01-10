<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="sponsor.commercialBanner.list.label.slogan" path="slogan" />
	<acme:list-column code="sponsor.commercialBanner.list.label.targetURL" path="targetURL" />
</acme:list>
