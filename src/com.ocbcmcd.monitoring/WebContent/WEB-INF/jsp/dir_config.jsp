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

<c:url value="/dirConfig" var="url" ></c:url>
<form:form method="post" modelAttribute="command" action="${url}" >
	<p class="">
		<label>Incoming Directory</label>
		<form:input  size="60" path="incomingDir" />
		<form:errors path="incomingDir" cssClass="error" />
	</p>
	<p class="">
		<label>Encrypted Directory</label>
		<form:input  size="60" path="encryptedDir" />
		<form:errors path="encryptedDir" cssClass="error" />
	</p>
	<p class="">
		<label>Processing Directory</label>
		<form:input  size="60" path="processingDir" />
		<form:errors path="processingDir" cssClass="error" />
	</p>
	<p class="">
		<label>Outgoing Directory</label>
		<form:input  size="60" path="outgoingDir" />
		<form:errors path="outgoingDir" cssClass="error" />
	</p>
	<p class="">
		<label>Daily Report Directory</label>
		<form:input  size="60" path="dailyReportDir" />
		<form:errors path="dailyReportDir" cssClass="error" />
	</p>
	<p class="">
		<label>Failed Directory</label>
		<form:input  size="60" path="failedDir" />
		<form:errors path="failedDir" cssClass="error" />
	</p>
	<p class="">
		<label>End Of Month Cron Tab</label>
		<form:input  size="60" path="eomScheduler" />
		<form:errors path="eomScheduler" cssClass="error" />
	</p>
	<p class="">
		<label>End Of Month Status</label>
		<form:input  size="60" path="eomSchedulerStatus" />
		<form:errors path="eomSchedulerStatus" cssClass="error" />
	</p>
	<p class="">
		<label>End Of Day Cron Tab</label>
		<form:input  size="60" path="eodScheduler" />
		<form:errors path="eodScheduler" cssClass="error" />
	</p>
	<p class=""><input type="submit" value="Submit" /></p>
</form:form>
</body>
</html>