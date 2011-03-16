<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users Detail</title>
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Users Detail</h1>
	<c:if test="${message!=null}">
		<h4><spring:message code="${message}" /></h4>
	</c:if>
	<table>
		<tr>
			<td>User Name</td>
			<td>${user.userName}</td>
		</tr>
		<tr>
			<td>Created</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${user.createdDate}" /></td>
		</tr>
		<tr>
			<td>Status</td>
			<td>${user.enabled}</td>
		</tr>
	</table>
	
	<a href="<c:url value="/user/edit/${user.id}" />">edit</a> | 
	<c:if test="${user.enabled == 1}">
	<a href="<c:url value="/user/disable/${user.id}" />">disable</a>
	</c:if>
	<c:if test="${user.enabled == 0}">
	<a href="<c:url value="/user/enable/${user.id}" />">enable</a>
	</c:if>  
</body>
</html>