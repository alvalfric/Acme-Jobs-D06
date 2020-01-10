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

<style>
table {
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #e9ecef;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #e9ecef;
}
</style>

<acme:form >
	
	<jstl:if test="${(command == 'show') || (command == 'create') }">
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title"/>
	</jstl:if>
	<jstl:if test= "${command == 'show'}">
	<acme:form-textbox  code="authenticated.thread.form.label.moment" path="moment"/>
	<acme:form-textbox  code="authenticated.thread.form.label.creator" path="creator.userAccount.username"/>
	<b><acme:message code="authenticated.thread.form.label.messages"/></b>
	<table>
		<tr>
			<th><acme:message code="authenticated.thread.form.label.message.title"/></th>
			<th><acme:message code="authenticated.thread.form.label.message.moment"/></th>
			<th><acme:message code="authenticated.thread.form.label.message.user"/></th>
		</tr>
		<jstl:forEach var="message" items="${messages}">
		<tr>
			<td>${message.title}</td>
			<td>${message.moment}</td>
			<td>${message.user.userAccount.username}</td>
			<td><a href = "authenticated/message/show?id=${message.id}"><acme:message code="authenticated.thread.form.label.message.show"/></a></td>
		</tr>
		</jstl:forEach>
	</table>
	<br>
	<b><acme:message code="authenticated.thread.form.label.users"/></b>
	<table>
		<tr>
			<th><acme:message code="authenticated.thread.form.label.user.name"/></th>
		</tr>
		<jstl:forEach var="user" items="${users}">
		<tr>
			<td>${user.userAccount.username}</td>
			<jstl:if test="${principal.getActiveRoleId() == (creatorId) }">
			<td><a href = "authenticated/thread/remove-user?id=${id}&userId=${user.userAccount.id}"><acme:message code="authenticated.thread.form.remove"/></a></td>
			</jstl:if>
		</tr>
		</jstl:forEach>
	</table>
	<br>
	<jstl:if test="${principal.getActiveRoleId() == (creatorId) }">
	<acme:form-submit method="get" 
		code="authenticated.thread.form.label.user.add"
		action="/authenticated/thread/add-user?id=${id}"/>
	</jstl:if>
	</jstl:if>
	
	<jstl:if test="${command == 'add-user' }">
	<acme:form-select code="authenticated.thread.form.label.user.add" path="userId">
		<jstl:forEach var = "user" items="${otherUsers}" >
		<acme:form-option code="${user.userAccount.username}" value="${user.userAccount.id}"/>
		</jstl:forEach>
	</acme:form-select>
	<acme:form-submit test="true" 
		code="authenticated.thread.form.add"
		action="/authenticated/thread/add-user"/>
	</jstl:if>
	
	
	<acme:form-submit method="get" test="${command == 'show'}" code="authenticated.thread.form.button.add-message" action="/authenticated/message/create?threadid=${id}"/>
		
	<jstl:if test="${command == 'remove-user'}" >
		<b><acme:message code="authenticated.thread.form.remove.user"/> <jstl:out value="${removeUsername}" /></b>
		<br>
		<br>
	</jstl:if>
	<acme:form-submit test="${command == 'remove-user'}" 
		code="authenticated.thread.form.remove"
		action="/authenticated/thread/remove-user?id=${id}&userId=${userId}"/>
	
	<acme:form-submit test="${command == 'create'}" 
		code="authenticated.thread.form.button.create"
		action="/authenticated/thread/create"/>
	
	<acme:form-return code="authenticated.thread.form.button.return"/>
</acme:form>
