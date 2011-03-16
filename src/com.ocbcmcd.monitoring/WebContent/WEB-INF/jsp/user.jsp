<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Users</h1>

	<c:if test="${param.message == 0}">
		<h4><spring:message code="message.user.notfound" ></spring:message></h4>
	</c:if>
	<c:if test="${param.message == 1}">
		<h4><spring:message code="message.user.created" ></spring:message></h4>
	</c:if>

<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />

<c:url value="/user" var="pagedLink">
	<c:param name="p" value="~" />
</c:url>

<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />

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
			<td><a href="<c:url value="/user/${user.id}" />"><c:out value="${user.userName}"></c:out></a></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${user.createdDate}" /></td>
			<td><c:out value="${user.enabled}"></c:out></td>
		</tr>
	</c:forEach>
</table>
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />

<p><a href="<c:url value="/register" />">Register User</a><p>
</body>
</html>