<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User</title>
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Update User</h1>

<c:if test="${param.message == 1}">
	<h4><spring:message code="message.user.updated" ></spring:message></h4>
</c:if>
 
<c:url var="url" value="/adminUser/edit/${command.id}" />
<form:form method="post" modelAttribute="command" action="${url}">
	<form:hidden path="id"/>
	<form:hidden path="userName"/>
	<p class="">
		<label>Your Username</label>
		<form:input path="userName" disabled="true" />
		<form:errors path="userName" cssClass="error" />
	</p>
	<p class="">
		<label>Your Password</label>
		<form:password path="password" />
		<form:errors path="password" cssClass="error" />
	</p>
	<p class="">
		<label>Your Confirm Password</label>
		<form:password path="confirmPassword" />
		<form:errors path="confirmPassword" cssClass="error" />
	</p>
	<p class=""><input type="submit" value="Submit" /></p>
</form:form>
</body>
</html>