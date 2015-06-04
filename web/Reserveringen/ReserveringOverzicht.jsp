<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Reservering Overzicht</title>


		<link href="http://fonts.googleapis.com/css?family=Roboto:300"
			rel="stylesheet" type="text/css">
			<%@ page import="Domain.Parkeergarage"%>
			<%@ page import="Domain.Reservering"%>
			<%@ page import="java.text.SimpleDateFormat"%>
			<%@ page import="java.text.ParseException"%>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">

		<div id="error">
			<%
				Object validation = request.getAttribute("validation");
				if (validation != null) {
					out.println(validation);
				}
			%>
		</div>
		<div id="reserveringaanmaken">
			<table>
				<tr>
					<th>Naam</th>
					<th>Kenteken</th>
					<th>Begin datum</th>
					<th>Begin tijd</th>
					<th>Eind datum</th>
					<th>Eind tijd</th>
					<th>Parkeerplaats</th>
				</tr>



				<%
					Parkeergarage parkeergarage = (Parkeergarage) application.getAttribute("parkeergarage");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					for (Reservering r : parkeergarage.getAlleReserveringen()) {
						String beginDatum = sdf.format(r.getBeginDatum());
						String eindDatum = sdf.format(r.getEindDatum());
				%>
				<tr>
					<td><%=r.getNaam()%></td>
					<td><%=r.getKenteken()%></td>
					<td><%=beginDatum%></td>
					<td><%=r.getTijd(r.getBeginTijd())%></td>
					<td><%=eindDatum%></td>
					<td><%=r.getTijd(r.getEindTijd())%></td>
					<td><%=r.getParkeerplaats()%></td>
				</tr>
				<%
					}
				%>

			</table>
		</div>
	</div>
</body>
</html>
