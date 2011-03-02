<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log</title>
</head>
<body>
			<a name="TemplateInfo"></a>				
			<h1>Log Monitoring</h1>
			
            <p>
				<c:forEach items="${logList}" var="log">
					<c:out value="${log.description}"></c:out>
					<c:out value="${log.type}"></c:out>
				</c:forEach>
            </p>
</body>
</html>