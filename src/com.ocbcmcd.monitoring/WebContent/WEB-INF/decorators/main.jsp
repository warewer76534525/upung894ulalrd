<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"  %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"  %>

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
		<h2 id="slogan">BANK OCBC NISP - Your Partner For Life</h2>		
		
		<div id="header-links">
			<p>
				<security:authorize ifAnyGranted="ROLE_PRS_ADM_UPDATE_USER,ROLE_PRS_UPDATE_USR">
					<security:authentication var="userName" property="name" />
					<c:url var="url" value="/userView/" />
					Welcome, 
					<a href="${url}${userName}">
						<security:authentication property="name" />
					</a>
					| <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
				</security:authorize>
				<security:authorize ifAllGranted="ROLE_ANONYMOUS"> 
					<a href="<c:url value="/login.jsp" />">Login</a>
				</security:authorize>	
			</p>		
		</div>
		
		<!-- Menu Tabs -->
		<ul>
			
		</ul>					
	
	</div></div>
				
	<!-- content-wrap starts here -->
	<div id="content-wrap">
		<div id="content">	 
			<div id="main">		
				<decorator:body />
			</div>		
		
			<div id="sidebar">	
					<h1>Sidebar Menu</h1>
					<ul class="sidemenu">
						<li><a href="<c:url value="/" />" title="">Home</a></li>
						<security:authorize ifAllGranted="ROLE_PRS_LOG">
						<li><a href="<c:url value="/logList" />">Logs</a></li>
						</security:authorize>
						<security:authorize ifAllGranted="ROLE_PRS_USER_LIST">
						<li><a href="<c:url value="/userList" />">User</a></li>
						</security:authorize>
						<security:authorize ifAllGranted="ROLE_PRS_CONFIG">
						<li><a href="<c:url value="/config" />">Config</a></li>
						</security:authorize>
						
					</ul>		
			</div>			
		</div>
	</div>
	<!-- content-wrap ends here -->		

	<!-- footer starts here -->	
	<div id="footer-wrap"><div id="footer-content">
	
		<div class="col float-left space-sep">
			<p>
			&copy; copyright 2010 <strong>OCBC NISP</strong><br />
			<a href="http://www.ocbcnisp.com/" title="BANK OCBC NISP - Your Partner For Life">BANK OCBC NISP - Your Partner For Life</a> <br />
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
