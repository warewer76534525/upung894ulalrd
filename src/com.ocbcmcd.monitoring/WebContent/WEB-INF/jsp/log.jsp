<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log</title>
</head>
<body>
			<a name="TemplateInfo"></a>				
			<h1>Log Monitoring</h1>
			
            <table>
            <thead>
            	<tr>
            		<td>File Name<td>
            		<td>Status<td>
            		<td>Date<td>
            	</tr>
            </thead>
            <tbody>
            	<c:forEach items="${logEventList}" var="log">
				<tr>
            		<td><c:out value="${log.fileName}"></c:out><td>
            		<td><c:out value="${log.type}"></c:out><td>
            		<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${log.time}" /><td>
            	</tr>
				</c:forEach>
            </tbody>
            </table>
</body>
</html>