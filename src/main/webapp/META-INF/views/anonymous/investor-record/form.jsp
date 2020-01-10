<%@page language="java"%>
<%@taglib prefix="jstl" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir ="/WEB-INF/tags"%>


<acme:form readonly="true">
	<acme:form-textbox code="anonymous.investorRecord.form.label.investorName" path="investorName" />
	<acme:form-textbox code="anonymous.investorRecord.form.label.sector" path="sector" />
	<acme:form-textarea code="anonymous.investorRecord.form.label.investingStatement" path="investingStatement" />
	<acme:form-double code="anonymous.investorRecord.form.label.stars" path="stars" />
	
	
	<acme:form-return code="anonymous.investorRecord.form.button.return"/>
</acme:form>