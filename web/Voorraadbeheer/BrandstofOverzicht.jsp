<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Brandstof Overzicht</title>
		<link href="http://fonts.googleapis.com/css?family=Roboto:300"
			rel="stylesheet" type="text/css">
			<%@ page import="Domain.Voorraad"%>
				<%@ page import="Domain.Brandstof"%>
				<%@ page import="java.util.ArrayList"%>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<%
			Object msgs = request.getAttribute("success");
			if (msgs != null) {
				out.println("<div class='success'>" + msgs + "</div>");
			}
		%>
		<form action="VoorraadBeheer.do" method="post">
			<div>
				<div id="buttons">
					<input type="hidden" name="soort" value="brandstof" />
					<div class="button" id="nieuw">
						<input type="submit" name="nieuw"
							value="Nieuw brandstof toevoegen" />
					</div>
					<div class="button" id="wijzigen">
						<input type="submit" name="wijzigen"
							value="Geselecteerde brandstoffen wijzigen" />
					</div>
					<div class="button" id="verwijderen">
						<input type="submit" name="verwijderen"
							value="Geselecteerde brandstoffen verwijderen" />
					</div>
					<div class="button" id="bestellen">
						<input type="submit" name="bestellen"
							value="Geselecteerde brandstoffen bestellen" />
					</div>
				</div>
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
						int i = 0;

						Voorraad voorraad = (Voorraad) application.getAttribute("voorraad");
						ArrayList<Brandstof> brandstoffen = (ArrayList<Brandstof>) voorraad.getAlleBrandstof();
						for (Brandstof b : brandstoffen) {
							String clas = (b.getAantalLiters() < b.getMinimaalAantalLiters()) ? "red" : "";
					%>
					<tr>
						<td><input type="checkbox" name=<%="" + i%>></td>
						<td><%=b.getBrandstofType()%></td>
						<td><%=b.getTankstationID()%></td>
						<td class=<%=clas%>><%=b.getAantalLiters()%></td>
						<td><%=b.getMinimaalAantalLiters()%></td>
						<td><%=b.getBrandstofPrijs()%></td>
					</tr>
					<%
						i++;
						}
					%>
				</table>
				<div id="btt">
					<a href=#>Terug naar Boven</a>
				</div>
			</div>

		</form>
	</div>
</body>
</html>



