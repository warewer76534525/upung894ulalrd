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
<h1>FTP Configuration</h1>

<c:if test="${param.message == 1}">
	<h4><spring:message code="message.conf.updated" ></spring:message></h4>
</c:if>

<c:url value="/ftpConfig" var="url" ></c:url>
<form:form method="post" modelAttribute="command" action="${url}" >
	<p class="">
		<label>Host</label>
		<form:input path="host" />
		<form:errors path="host" cssClass="error" />
	</p>
	<p class="">
		<label>Port</label>
		<form:input path="port" />
		<form:errors path="port" cssClass="error" />
	</p>
	<p class="">
		<label>User Name</label>
		<form:input path="userName" />
		<form:errors path="userName" cssClass="error" />
	</p>
	<p class="">
		<label>Password</label>
		<form:password path="password" />
		<form:errors path="password" cssClass="error" />
	</p>
	<p class="">
		<label>Remote Directory</label>
		<form:input path="remoteDir" />
		<form:errors path="remoteDir" cssClass="error" />
	</p>
	<p class="">
		<label>Chk Directory</label>
		<form:input path="chkDir" />
		<form:errors path="chkDir" cssClass="error" />
	</p>
	<p class="">
		<label>Report Directory</label>
		<form:input path="reportDir" />
		<form:errors path="reportDir" cssClass="error" />
	</p>
	<p class="">
		<label>FTP Send max retry</label>
		<form:input path="maxRetry" />
		<form:errors path="maxRetry" cssClass="error" />
	</p>
	<p class="">
		<label>FTP Retry Send Interval</label>
		<form:input path="retryInterval" />
		<form:errors path="retryInterval" cssClass="error" />
	</p>
	<p class="">
		<label>CHK Watcher max retry</label>
		<form:input path="watcherRetry" />
		<form:errors path="watcherRetry" cssClass="error" />
	</p>
	<p class="">
		<label>CHK Watcher retry interval</label>
		<form:input path="watcherInterval" />
		<form:errors path="watcherInterval" cssClass="error" />
	</p>
	
	<p class=""><input type="submit" value="Submit" /></p>
</form:form>
</body>
</html>