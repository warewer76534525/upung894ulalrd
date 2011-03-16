<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

	<link rel="stylesheet" href="<% out.print(request.getContextPath()); %>/images/EliteCircle.css" type="text/css" />
	
	<title>OCBC McD Monitoring - <decorator:title default="OCBC McD Monitoring" /></title>
	<decorator:head />
</head>

<body>
	
	<!-- header starts here -->
	<div id="header-wrap"><div id="header-content">	
				
		<h1 id="logo"><a href="<c:url value="/"></c:url>" title="">OCBC McD <span class="orange">File Sender Monitoring</span></a></h1>	
		<h2 id="slogan">Put your site slogan here...</h2>		
		
		<div id="header-links">
			<p>
				<security:authorize ifAllGranted="ROLE_PRS_LOG">
					<security:authentication property="name" /> 
					| <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
				</security:authorize>
				<security:authorize ifAllGranted="ROLE_ANONYMOUS"> 
					<a href="<c:url value="/login.jsp" />">Login</a>
				</security:authorize>	
			</p>		
		</div>
		
		<!-- Menu Tabs -->
		<ul>
			<li><a href="index.html" id="current">Home</a></li>
			<li><a href="index.html">Log Monitor</a></li>
		</ul>					
	
	</div></div>
				
	<!-- content-wrap starts here -->
	<div id="content-wrap"><div id="content">	 
	
		<div id="main">		
			<decorator:body />
		</div>		
	
		
		<div id="sidebar">	
		
				<h1>Search Box</h1>	
				<form action="#" class="searchform">
					<p>
					<input name="search_query" class="textbox" type="text" />
  					<input name="search" class="button" value="Search" type="submit" />
					</p>			
				</form>			
			
				<h1>Sidebar Menu</h1>
				<ul class="sidemenu">
					<li><a href="<c:url value="/" />">Home</a></li>
					<li><a href="<c:url value="/log" />">Logs</a></li>
					<li><a href="<c:url value="/user" />">User</a></li>
				</ul>		
		</div>			
	
	<!-- content-wrap ends here -->		
	</div></div>

	<!-- footer starts here -->	
	<div id="footer-wrap"><div id="footer-content">
	
		<div class="col float-left space-sep">
			<p>
			&copy; copyright 2010 <strong>OCBC NISP</strong><br />
			<a href="http://www.bluewebtemplates.com/" title="Website Templates">website templates</a> by <a href="http://www.styleshout.com/">styleshout</a> <br />

			Valid <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> |
		   	      <a href="http://validator.w3.org/check/referer">XHTML</a>
			</p>
		</div>
		
		<div class="col float-left">
		</div>		
	
		<div class="col2 float-right">
            
		</div>
		
		<br class="clear" />
	
	</div></div>
	<!-- footer ends here -->

</body>
</html>
