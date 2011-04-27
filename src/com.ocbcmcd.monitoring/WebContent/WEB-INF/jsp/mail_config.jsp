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
<h1>Mail Configuration</h1>

<c:if test="${param.message == 1}">
	<h4><spring:message code="message.conf.updated" ></spring:message></h4>
</c:if>

<c:url value="/mailConfig" var="url" ></c:url>
<form:form method="post" modelAttribute="command" action="${url}" >
	<p class="">
		<label>Host</label>
		<form:input  size="60" path="host" />
		<form:errors path="host" cssClass="error" />
	</p>
	<p class="">
		<label>Port</label>
		<form:input  size="60" path="port" />
		<form:errors path="port" cssClass="error" />
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
	<p class="">
		<label>Smtp Protocol</label>
		<form:input  size="60" path="smtpProtocol" />
		<form:errors path="smtpProtocol" cssClass="error" />
	</p>
	<p class="">
		<label>SMTP Authentication</label>
		<form:input  size="60" path="smtpAuth" />
		<form:errors path="smtpAuth" cssClass="error" />
	</p>
	<p class="">
		<label>Email From</label>
		<form:input  size="60" path="from" />
		<form:errors path="from" cssClass="error" />
	</p>
	<p class="">
		<label>To Eod Email</label>
		<form:input  size="60" path="toEod" />
		<form:errors path="toEod" cssClass="error" />
	</p>
	<p class="">
		<label>EOD Email Subject</label>
		<form:input  size="60" path="eodSubject" />
		<form:errors path="eodSubject" cssClass="error" />
	</p>
	<p class="">
		<label>To Processed Failed</label>
		<form:input  size="60" path="toProcessedFailed" />
		<form:errors path="toProcessedFailed" cssClass="error" />
	</p>
	<p class="">
		<label>Processed Failed Subject</label>
		<form:input  size="60" path="processedFailedSubject" />
		<form:errors path="processedFailedSubject" cssClass="error" />
	</p>
	<p class="">
		<label>To Sent Failed</label>
		<form:input  size="60" path="toSentFailed" />
		<form:errors path="toSentFailed" cssClass="error" />
	</p>
	<p class="">
		<label>Sent Failed Subject</label>
		<form:input  size="60" path="sentFailedSubject" />
		<form:errors path="sentFailedSubject" cssClass="error" />
	</p>
	<p class=""><input type="submit" value="Submit" /></p>
</form:form>
</body>
</html>