<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>
<c:if test="${not empty param.error}">
	<font color="red"> Invalid username or password </font>
</c:if>

<form action="<c:url value="/j_spring_security_check" />" name="signinform" method="post">
	<p class=""><label>Your Username</label><input type="text" name="j_username" value="" /></p>
	<p class=""><label>Your Password</label><input type="password" name="j_password" value="" /></p>
	<p class="agree"><input type="checkbox" id="remember_me" name="_spring_security_remember_me" /> Remember me</p>
	<p class=""><input type="submit" value="Login" /> <input type="reset" value="Reset" /> </p>
</form>

</body>
</html>