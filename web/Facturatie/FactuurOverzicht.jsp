<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Factuur Overzicht</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css"></link>

<%@ page import="Domain.Facturatie"%>
<%@ page import="Domain.Factuur"%>
<%@ page import="Domain.Klus"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.DecimalFormat;"%>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<%
			if (request.getAttribute("errorMessage") != null) {
		%>
		<div class='error'>${errorMessage}</div>
		<%
			}
		%>

		<form action='FactuurBeheer.do' method="post">
			<div>
				<div id="buttons">
					<div class="button">
						<input type="submit" value="Geselecteerd factuur betalen"
							name="betalen" />
					</div>
					<div class="button">
						<input type="submit" value="Klant Blokkeren" name="blokkeer" />
					</div>
					<div class="button">
						<input type="submit" value="eerste Factuur maken"
							name="eersteFactuur" />
					</div>
					<div class="button" id="maak">
						<input type="submit" value="maak Waarschuwingsbrief"
							name="waarschuwing" />
					</div>
				</div>

				<table>
					<tr>
						<th></th>
						<th>Voornaam</th>
						<th>achternaam</th>
						<th>kosten</th>
						<th>Datum</th>
						<th>Betaald</th>
					</tr>
					<%
						DecimalFormat df = new DecimalFormat("#.00");

						int i = 0;

						Facturatie facturatie = (Facturatie) application.getAttribute("facturatie");
						for (Factuur f : facturatie.getAlleFacturen()) {

							int dag = f.getDatum().get(Calendar.DATE);
							int maand = f.getDatum().get(Calendar.MONTH) + 1;
							int jaar = f.getDatum().get(Calendar.YEAR);
							out.print("<tr>");
							out.print("<td><input type='checkbox'  name='" + i + "'></td>");
							out.print("<td> " + f.getKlant().getNaam() + " </td>");
							out.print("<td>  " + f.getKlant().getAchternaam() + "  </td>");
							out.print("<td> " + df.format(f.getKosten()) + " </tdBr>");
							out.print("<td> " + dag + "/" + maand + "/" + jaar + " </td>");
							out.print("<td>" + f.getBetaald() + "</td>");
							out.print("</tr>");
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



