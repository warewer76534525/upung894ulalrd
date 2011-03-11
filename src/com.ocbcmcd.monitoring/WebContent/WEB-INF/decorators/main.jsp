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
				
		<h1 id="logo"><a href="index.html" title="">OCBC McD <span class="orange">File Sender Monitoring</span></a></h1>	
		<h2 id="slogan">Put your site slogan here...</h2>		
		
		<div id="header-links">
			<p>
				<a href="index.html">Home</a> | 
				<a href="index.html">Contact</a> | 
				<a href="index.html">Site Map</a>
				<security:authorize ifAllGranted="ROLE_PRS_LOG">
					<security:authentication property="name" /> 
					| <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
				</security:authorize>
				<security:authorize ifAllGranted="ROLE_ANONYMOUS"> 
					| <a href="<c:url value="/login.jsp" />">Login</a>
				</security:authorize>	
			</p>		
		</div>
		
		<!-- Menu Tabs -->
		<ul>
			<li><a href="index.html" id="current">Home</a></li>
			<li><a href="index.html">News</a></li>
			<li><a href="index.html">Downloads</a></li>
			<li><a href="index.html">Support</a></li>
			<li><a href="index.html">About</a></li>			
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
					<li><a href="<%=request.getContextPath()%>">Home</a></li>
					<li><a href="<%=request.getContextPath()%>/log">Logs</a></li>
					<li><a href="#SampleTags">Sample Tags</a></li>
					<li><a href="http://www.styleshout.com/">More Templates</a></li>
					<li><a href="http://www.dreamtemplate.com" title="Web Templates">Web Templates</a></li>
				</ul>		
		</div>			
	
	<!-- content-wrap ends here -->		
	</div></div>

	<!-- footer starts here -->	
	<div id="footer-wrap"><div id="footer-content">
	
		<div class="col float-left space-sep">
			<h2>Site Partners</h2>
			<ul class="columns">
                <li class="top"><a href="http://www.dreamtemplate.com" title="Website Templates">DreamTemplate</a></li>
                <li><a href="http://www.themelayouts.com" title="WordPress Themes">ThemeLayouts</a></li>
                <li><a href="http://www.imhosted.com" title="Website Hosting">ImHosted.com</a></li>
                <li><a href="http://www.dreamstock.com" title="Stock Photos">DreamStock</a></li>
                <li><a href="http://www.evrsoft.com" title="Website Builder">Evrsoft</a></li>
                <li><a href="http://www.webhostingwp.com" title="Web Hosting">Web Hosting</a></li>
			</ul>			
		</div>
		
		<div class="col float-left">
			<h2>Links</h2>
			<ul class="columns">				
				<li class="top"><a href="index.html">Link One</a></li>
				<li><a href="index.html">Link Two</a></li>
				<li><a href="index.html">Link Three</a></li>
				<li><a href="index.html">Link Four</a></li>
				<li><a href="index.html">Link Five</a></li>
                <li><a href="index.html">Link Six</a></li>
			</ul>
		</div>		
	
		<div class="col2 float-right">
            <h2>Site Links</h2>
			<ul class="columns">
				<li class="top"><a href="index.html">Home</a></li>
                <li><a href="index.html">About</a></li>
				<li><a href="index.html">Sitemap</a></li>
				<li><a href="index.html">RSS Feed</a></li>								
			</ul>

            <p>
			&copy; copyright 2010 <strong>Your Company Name</strong><br />
			<a href="http://www.bluewebtemplates.com/" title="Website Templates">website templates</a> by <a href="http://www.styleshout.com/">styleshout</a> <br />

			Valid <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> |
		   	      <a href="http://validator.w3.org/check/referer">XHTML</a>
			</p>
		</div>
		
		<br class="clear" />
	
	</div></div>
	<!-- footer ends here -->

</body>
</html>
