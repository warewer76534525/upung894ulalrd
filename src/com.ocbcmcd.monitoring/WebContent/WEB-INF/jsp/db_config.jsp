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
<h1>Database Configuration</h1>

<c:if test="${param.message == 1}">
	<h4><spring:message code="message.conf.updated" ></spring:message></h4>
</c:if>

<c:url value="/dbConfig" var="url" ></c:url>
<form:form method="post" modelAttribute="command" action="${url}" >
	<p class="">
		<label>URL</label>
		<form:input  size="60" path="url" />
		<form:errors path="url" cssClass="error" />
	</p>
	<p class="">
		<label>User Name</label>
		<form:input  size="60" path="userName" />
		<form:errors path="userName" cssClass="error" />
	</p>
	<p class="">
		<label>Password</label>
		<form:password size="60" path="password" />
		<form:errors path="password" cssClass="error" />
	</p>
	<p class=""><input type="submit" value="Submit" /></p>
</form:form>
</body>
</html>