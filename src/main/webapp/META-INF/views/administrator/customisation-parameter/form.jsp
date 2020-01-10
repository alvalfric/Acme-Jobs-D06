<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="false">
	<acme:form-textbox code="administrator.customisation-parameter.form.label.spamWordsEn" path="spamWordsEn"/>
	<acme:form-textbox code="administrator.customisation-parameter.form.label.spamWordsEs" path="spamWordsEs"/>
	<acme:form-double code="administrator.customisation-parameter.form.label.spamThreshold" path="spamThreshold"/>
	
	<acme:form-submit 
		code="administrator.customisation-parameter.form.button.update"
		action="/administrator/customisation-parameter/update"/>
	
	<acme:form-return code="administrator.customisation-parameter.form.button.return"/>
</acme:form>
