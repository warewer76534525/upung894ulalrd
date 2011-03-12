<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log</title>
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Log Monitoring</h1>


<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />

<c:url value="/log" var="pagedLink">
	<c:param name="p" value="~" />
</c:url>

<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />

<table>
	<thead>
		<tr>
			<th>File Name</th>
			<th>Status</th>
			<th>Date</th>
		</tr>
	</thead>
	<c:forEach items="${pagedListHolder.pageList}" var="log">
		<tr>
			<td><c:out value="${log.fileName}"></c:out></td>
			<td><c:out value="${log.type}"></c:out></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${log.time}" /></td>
		</tr>
	</c:forEach>
</table>

<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />
</body>
</html>