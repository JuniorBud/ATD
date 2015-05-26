<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Brandstof bestellen</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css">
<%@ page import="Domain.Brandstof"%>
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
						<th>Type</th>
						<th>TID</th>
						<th>Minimale Liters</th>
						<th>Liters in voorraad</th>
						<th>Prijs/Liter</th>
						<th>Aantal bestellen</th>
					</tr>
					<%
						ArrayList<Brandstof> brandstoffen = (ArrayList<Brandstof>) request.getSession().getAttribute("brandstoffen");
						if (brandstoffen == null) {
							RequestDispatcher rd = request.getRequestDispatcher("BrandstofOverzicht.jsp");
							rd.forward(request, response);
						} else {
							for (Brandstof b : brandstoffen) {
					%>
					<tr>
						<td><%=b.getBrandstofType()%></td>
						<td><%=b.getTankstationID()%></td>
						<td><%=b.getMinimaalAantalLiters()%></td>
						<td><%=b.getAantalLiters()%></td>
						<td><%=b.getBrandstofPrijs()%></td>
						<td><input type="text"
							name=<%=b.getBrandstofType() + b.getTankstationID()%>
							value=<%=0%>></td>
					</tr>
					<%
						}
						}
					%>
				</table>

				<input type="hidden" name="soort" value="brandstof" />
				<div class="button">
					<input type="submit" name="besteld" value="Bestel brandstof">
				</div>
			</div>
		</form>
	</div>
</body>
</html>