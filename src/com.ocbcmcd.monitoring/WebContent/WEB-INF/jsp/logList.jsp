<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log Event</title>

<link rel="stylesheet" href="<c:url value="/" />plugin/jqui/themes/base/jquery.ui.all.css">
<script src="<c:url value="/" />plugin/jqui/jquery-1.5.1.js"></script>
<script src="<c:url value="/" />plugin/jqui/ui/jquery.ui.core.js"></script>
<script src="<c:url value="/" />plugin/jqui/ui/jquery.ui.widget.js"></script>
<script src="<c:url value="/" />plugin/jqui/ui/jquery.ui.datepicker.js"></script>
<script>
$(function() {
	var dates = $( "#from, #to" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		onSelect: function( selectedDate ) {
			var option = this.id == "from" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
					instance.settings.dateFormat ||
					$.datepicker._defaults.dateFormat,
					selectedDate, instance.settings );
			dates.not( this ).datepicker( "option", option, date );
		}
	});
});
</script>
	
</head>
<body>
<a name="TemplateInfo"></a>
<h1>Log Event</h1>


<c:url value="/logList/?${requestString}" var="pagedLink">
	<c:param name="p" value="~" />
</c:url>

<form:form method="get" modelAttribute="command">
	<p class="">
	file
	<form:input path="file"/>
	from 
	<form:input path="from"/>
	to 
	<form:input path="to"/>
	</p>
	<p>
	<input type="submit" value="Submit" />
	</p>
</form:form>

<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />

<table>
	<thead>
		<tr>
			<th>File Name</th>
			<th>Status</th>
			<th>Date</th>
		</tr>
	</thead>
	<c:forEach items="${pagedListHolder.pageList}" var="log">
		<tr>
			<td><a href="<c:url value="/logDetail/${log.id}" />">${log.fileName}</a></td>
			<td>${log.type}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${log.time}" /></td>
		</tr>
	</c:forEach>
</table>

<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />

${coba}
</body>
</html>