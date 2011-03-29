<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration New User</title>
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Registration User</h1>
<form:form method="post" modelAttribute="command">
	<p class="">
		<label>Your Username</label>
		<form:input path="userName" />
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
	<p class="">
		<label>User Type</label>
		<form:select path="userType" items="${userTypes}"
			itemValue="name" itemLabel="description" />
		<form:errors path="userType" cssClass="error" />
	</p>
	
	<p class=""><input type="submit" value="Submit" /></p>
</form:form>
</body>
</html>