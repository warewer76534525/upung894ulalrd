<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configuration Setting</title>
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Configuration Setting</h1>

<c:if test="${param.message == 1}">
	<h4><spring:message code="message.conf.updated" ></spring:message></h4>
</c:if>

<c:url value="/config" var="url" ></c:url>
<form:form method="post" modelAttribute="command" action="${url}" >
	<p class="">
		<a href="<c:url value="/mailConfig" />"> Email Config </a> <br/>
		<a href="<c:url value="/ftpConfig" />"> FTP Config </a> <br/>
		<a href="<c:url value="/dirConfig" />"> Directory Config </a> <br/>
		<a href="<c:url value="/dbConfig" />"> Database Config </a> <br/>
	</p>
</form:form>
</body>
</html>