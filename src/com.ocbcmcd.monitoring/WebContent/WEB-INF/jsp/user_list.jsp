<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usersx</title>
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Usersx</h1>

	<c:if test="${param.message == 0}">
		<h4><spring:message code="message.user.notfound" ></spring:message></h4>
	</c:if>
	<c:if test="${param.message == 1}">
		<h4><spring:message code="message.user.created" ></spring:message></h4>
	</c:if>

<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />

<c:url value="/userList/?${requestString}" var="pagedLink">
	<c:param name="p" value="~" />
</c:url>

<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />
<form:form method="get" modelAttribute="command">
	<p class="">
	user name
	<form:input path="userName"/>
	<input type="submit" value="Submit" />
	</p>
</form:form>

<table>
	<thead>
		<tr>
			<th>User Name</th>
			<th>Created Data</th>
			<th>Status</th>
		</tr>
	</thead>
	<c:forEach items="${pagedListHolder.pageList}" var="user">
		<tr>
			<td><a href="<c:url value="/userDetail/${user.id}" />"><c:out value="${user.userName}"></c:out></a></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${user.createdDate}" /></td>
			<td><c:out value="${user.enabled}"></c:out></td>
		</tr>
	</c:forEach>
</table>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />

<security:authorize ifAllGranted="ROLE_PRS_CREATE_USER">
<p><a href="<c:url value="/register" />">Register User</a></p>
</security:authorize>
</body>
</html>