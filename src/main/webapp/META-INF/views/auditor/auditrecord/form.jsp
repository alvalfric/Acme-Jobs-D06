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
	<acme:form-textbox code="auditor.auditrecord.form.label.reference" path="reference" readonly="true"/>
	<acme:form-textbox code="auditor.auditrecord.form.label.title" path="title" placeholder="Titulo Auditoria"/>
	
	<jstl:if test= "${status == 'true'}">
	<acme:form-select code="auditor.auditrecord.form.label.status" path="status">
		<acme:form-option code="auditor.auditrecord.form.label.published" value="true"/>
		<acme:form-option code="auditor.auditrecord.form.label.draft" value="false"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test= "${status == 'false'}">
	<acme:form-select code="auditor.auditrecord.form.label.status" path="status">
		<acme:form-option code="auditor.auditrecord.form.label.draft" value="false"/>
		<acme:form-option code="auditor.auditrecord.form.label.published" value="true"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test= "${command != 'create'}">
			<acme:form-moment  code="auditor.auditrecord.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	
	<acme:form-textbox code="auditor.auditrecord.form.label.body" path="body" placeholder = "Cuerpo Auditoria"/>
	
	<acme:form-submit test= "${command != 'update'}" code= "auditor.auditrecord.form.button.create" action="/auditor/auditrecord/create?jobId=${job.id}"/> 
	<acme:form-submit test= "${command != 'create'}" code= "auditor.auditrecord.form.button.update" action="/auditor/auditrecord/update"/> 
	<acme:form-return code="auditor.auditrecord.form.button.return"/>
</acme:form>
