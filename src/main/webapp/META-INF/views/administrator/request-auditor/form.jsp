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

<acme:form>
	<acme:form-textbox code="administrator.form.request-auditor.label.username" path="username" readonly="true"/>
	<acme:form-textarea code="administrator.form.request-auditor.label.firm" path="firm" readonly="true"/>
	<acme:form-textarea code="administrator.form.request-auditor.label.statement" path="responsabilityStat" readonly="true"/>
	
	<acme:form-submit
		code="administrator.form.request-auditor.label.accept"
		action="/administrator/request-auditor/accept"/>
	<acme:form-submit
		code="administrator.form.request-auditor.label.deny"
		action="/administrator/request-auditor/deny"/>
	
  	<acme:form-return code="administrator.form.request-auditor.label.return"/>
</acme:form>
