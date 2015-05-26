<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Onderdeel Overzicht</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css"></link>
<%@ page import="Domain.Voorraad"%>
<%@ page import="Domain.Onderdeel"%>
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
					<input type="hidden" name="soort" value="onderdeel" />
					<div class="button" id="nieuw">
						<input type="submit" name="nieuw"
							value="Nieuw onderdeel toevoegen" />
					</div>
					<div class="button" id="wijzigen">
						<input type="submit" name="wijzigen"
							value="Geselecteerde onderdelen wijzigen" />
					</div>
					<div class="button" id="verwijderen">
						<input type="submit" name="verwijderen"
							value="Geselecteerde onderdelen verwijderen" />

					</div>
					<div class="button" id="bestellen">
						<input type="submit" name="bestellen"
							value="Geselecteerde onderdelen bestellen" />
					</div>
				</div>
				<table>
					<tr>
						<th></th>
						<th>Nummer</th>
						<th>omschrijving</th>
						<th>aantal in vooraad</th>
						<th>minimale voorraad</th>
					</tr>
					<%
						int i = 0;
						Voorraad voorraad = (Voorraad) application.getAttribute("voorraad");
						for (Onderdeel o : voorraad.getAlleOnderdelen()) {
							String clas = (o.getOnderdeelVoorraad() < o.getOnderdeelMinimaleVoorraad()) ? "red" : "";
					%>
					<tr>
						<td><input type="checkbox" name=<%="" + i%>></td>
						<td><%=o.getOnderdeelNummer()%></td>
						<td><%=o.getOnderdeelOmschrijving()%></td>
						<td class=<%=clas%>><%=o.getOnderdeelVoorraad()%></td>
						<td><%=o.getOnderdeelMinimaleVoorraad()%></td>
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




