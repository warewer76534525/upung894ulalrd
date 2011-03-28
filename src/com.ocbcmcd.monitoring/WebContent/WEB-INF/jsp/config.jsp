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
		<label>Config Name</label>
		 <select name="configName">
		 	<option value="">select</option>
		    <optgroup label="DATABASE">
		      <option value="jdbc.url">db url</option>
		      <option value="jdbc.username">db user</option>
		      <option value="jdbc.password">db password</option>
		    </optgroup>
		    <optgroup label="FTP">
		      <option value="ftp.host">ftp host</option>
		      <option value="ftp.username">ftp user</option>
		      <option value="ftp.password">ftp password</option>
		    </optgroup>
		    <optgroup label="SMTP">
		      <option value="mail.host">smtp host</option>
		      <option value="mail.port">smtp port</option>
		      <option value="mail.username">smtp user</option>
		      <option value="mail.password">smtp password</option>
		      <option value="mail.from">smtp from</option>
		      <option value="mail.to">smtp to</option>
		    </optgroup>
		  </select>
		<form:errors path="configName" cssClass="error" />
	</p>
	<p class="">
		<label>Config Value</label>
		<form:password path="configValue" />
		<form:errors path="configValue" cssClass="error" />
	</p>
	<p class=""><input type="submit" value="Submit" /></p>
</form:form>
</body>
</html>