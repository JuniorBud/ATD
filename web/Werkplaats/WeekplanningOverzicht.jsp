<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Weekplanning</title>


<link href="http://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet" type="text/css">


<%@ page import="Domain.Werkplaats"%>
<%@ page import="Domain.Klus"%>
<%@ page import="Domain.Monteur"%>
<%@ page import="Domain.Weekplanning"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
<script type="text/javascript">
	function week() {
		updateWeek();
		draw();
	}
</script>
</head>
<body onload="draw()">
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<div id="weekplanning">
			<%
				Object msgs = request.getAttribute("error");
				if (msgs != null) {
					out.println("<div class='error'>" + msgs + "</div>");
				}
				msgs = request.getAttribute("success");
				if (msgs != null) {
					out.println("<div class='success'>" + msgs + "</div>");
				}
			%>
			<select id="jaar" onchange="week()">
				<%
					Werkplaats werkplaats = (Werkplaats) application.getAttribute("werkplaats");
					boolean first = true;
					int jaar = 0;
					for (Object o : werkplaats.getAlleJaren()) {
						if (first) {
							jaar = Integer.parseInt(o.toString());
							first = false;
						}
						out.println("<option>" + o.toString() + "</option>");
					}
				%>
			</select> <select id="week" onchange="draw()">
				<%
					for (Object o : werkplaats.getAlleWeken(jaar)) {
						out.println("<option>" + o.toString() + "</option>");
					}
				%>
			</select> <select id="monteur" onchange="draw()">
				<%
					for (Monteur m : werkplaats.getAlleMonteurs()) {
						String naam = m.getAchternaam() + ", " + m.getNaam();
						if (!m.getTussenvoegsel().equals("")) {
							naam += " " + m.getTussenvoegsel();
						}
						out.println("<option value='" + m.getNaam() + "-" + m.getTussenvoegsel() + "-" + m.getAchternaam() + "'>" + naam + "</option>");
					}
				%>
			</select>
			<table id="table">

			</table>
		</div>
	</div>
</body>
</html>
