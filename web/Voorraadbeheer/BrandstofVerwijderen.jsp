<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Brandstof verwijderen</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css">
<%@ page import="Domain.Brandstof"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<form action='VoorraadBeheer.do' method='post'>
			<div>
				<table>
					<tr>
						<th></th>
						<th>BrandstofType</th>
						<th>Tankstation ID</th>
						<th>Aantal Liters aanwezig</th>
						<th>Minimum aantal Liters aanwezig</th>
						<th>Prijs per Liter</th>
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
						<td><input type="checkbox"
							name=<%=b.getBrandstofType() + b.getTankstationID()%> checked></td>
						<td><%=b.getBrandstofType()%></td>
						<td><%=b.getTankstationID()%></td>
						<td><%=b.getAantalLiters()%></td>
						<td><%=b.getMinimaalAantalLiters()%></td>
						<td><%=b.getBrandstofPrijs()%></td>
					</tr>
					<%
						}
						}
					%>
				</table>
				<div class="removeMessage">Weet u zeker dat u deze
					brandstoffen wilt verwijderen?</div>
				<input type="hidden" name="soort" value="brandstof" />
				<div class="button">
					<input type="submit" name="verwijderd"
						value="Verwijder brandstoffen">
				</div>
			</div>
		</form>
	</div>
</body>
</html>