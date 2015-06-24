<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Klanten Overzicht</title>
</head>
<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css">
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="Domain.Klantbinding"%>
	<%@ page import="Domain.Klant"%>
	<%@ page import="Domain.Template"%>
	<%@ page import="java.text.SimpleDateFormat"%>
	<body>
		<jsp:include page="../Menu.jsp" />
		<div id="content">
			<div>
				<%
					if (request.getAttribute("msgs") != null) {
				%>
				<div class='error'>${msgs}</div>
				<%
					}
				%>
			</div>
			<div>
				<form action="GenereerBrieven.do" method="post">
					<div>
						<div id="templateSelect">
							<b>Selecteer lul template: </b> <select name="template">
								<%
									Klantbinding klantbinding = (Klantbinding) application.getAttribute("klantbinding");
									for (Template t : klantbinding.getAlleTemplates()) {
								%>
								<option value=<%=t.getTemplateNaam()%>><%=t.getTemplateNaam()%></option>
								<%
									}
								%>
							</select>
						</div>
						<div class="button">
							<input type="submit" value="Genereer." />
						</div>
						<table>
							<tr>
								<th><th>Naam</th>
									<th>Tussenvoegsel</th>
									<th>Achternaam</th>
									<th>Leeftijd</th>
									<th>Woonplaats</th>
									<th>Onderhoud</th>
									<th>Bezoek</th>
									<th>Geblokkeerd</th>
							</tr>
							<%
								SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								int i = 0;

								for (Klant k : klantbinding.getAlleKlanten()) {
									String s = (k.isGeblokkeerd()) ? "Ja" : "Nee";
							%>
							<tr>
								<td><input type="checkbox" name=<%="" + i%>></td>
								<td><%=k.getNaam()%></td>
								<td><%=k.getTussenvoegsel()%></td>
								<td><%=k.getAchternaam()%></td>
								<td><%=k.getLeeftijd()%></td>
								<td><%=k.getAdres().getWoonplaats()%></td>
								<td><%=dateFormat.format(k.getLaatsteOnderhoud())%></td>
								<td><%=dateFormat.format(k.getBezoek())%></td>
								<td><%= s %></td>
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
		</div>
	</body>
</html>