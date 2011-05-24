<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"  %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users Detail</title>
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Log Detail</h1>
	<table>
		<tr>
			<td>File Name</td>
			<td>${log.fileName}</td>
		</tr>
		<tr>
			<td>Log Status</td>
			<td>${log.type}</td>
		</tr>
		<tr>
			<td>Time</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${log.time}" /></td>
		</tr>
		<tr>
			<td>Description</td>
			<td>${log.description}</td>
		</tr>
	</table>
	
</body>
</html>