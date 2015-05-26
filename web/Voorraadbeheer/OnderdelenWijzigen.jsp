<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Onderdeel wijzigen</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css">
<%@ page import="Domain.Onderdeel"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<%
			Object msgs = request.getAttribute("error");
			if (msgs != null) {
				out.println("<div class='error'>" + msgs + "</div>");
			}
		%>
		<form action='VoorraadBeheer.do' method='post'>
			<div>
				<table>
					<tr>
						<th>Nummer</th>
						<th>Omschrijving</th>
						<th>Aantal in voorraad</th>
						<th>Minimale Voorraad</th>
					</tr>
					<%
						ArrayList<Onderdeel> onderdelen = (ArrayList<Onderdeel>) request.getSession().getAttribute("onderdelen");
						if (onderdelen == null) {
							RequestDispatcher rd = request.getRequestDispatcher("OnderdelenOverzicht.jsp");
							rd.forward(request, response);
						} else {
							for (Onderdeel o : onderdelen) {
					%>
					<tr>
						<td><%=o.getOnderdeelNummer()%></td>
						<td><%=o.getOnderdeelOmschrijving()%></td>
						<td><input type="text" name=<%=o.getOnderdeelNummer() + "0"%>
							value=<%=o.getOnderdeelVoorraad()%>></td>
						<td><input type="text" name=<%=o.getOnderdeelNummer() + "1"%>
							value=<%=o.getOnderdeelMinimaleVoorraad()%>></td>
					</tr>
					<%
						}
						}
					%>
				</table>

				<input type="hidden" name="soort" value="onderdeel" />
				<div class="button">
					<input type="submit" name="gewijzigd" value="Wijzig onderdeel">
				</div>
			</div>
		</form>
	</div>
</body>
</html>