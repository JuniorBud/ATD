<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Betaling Invoeren</title>

<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css">

</head>
<body>
	<%@ page import="Domain.Factuur"%>s
	<%@ page import="Domain.Klant"%>
	<%@ page import="Domain.Klus"%>
	<%@ page import="java.util.Calendar"%>
	<%@ page import="java.util.ArrayList"%>

	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<form action="BetalingInvoeren.do" method="post">
			<div>
				<table>
					<tr>
						<th>Voornaam</th>
						<th>achternaam</th>
						<th>kosten</th>
						<th>Datum</th>
						<th>Betaald</th>
						<th>Betaling</th>
					</tr>
					<%
						ArrayList<Factuur> facturen = (ArrayList<Factuur>) request.getAttribute("geselecteerdeFacturen");

						for (Factuur f : facturen) {
							Klant k = f.getKlant();
							int maand = f.getDatum().get(Calendar.MONTH) + 1;
							int jaar = f.getDatum().get(Calendar.YEAR);
							out.print("<tr>");
							out.print("<td> " + k.getNaam() + " </td>");
							out.print("<td>  " + k.getAchternaam() + "  </td>");
							out.print("<td> " + f.getKosten() + " </tdBr>");
							out.print("<td> " + "1" + "/" + maand + "/" + jaar + " </td>");
							out.print("<td>" + f.getBetaald() + "</td>");
							out.print("<td>  <input type='text' name='" + k.getNaam() + k.getAchternaam() + maand + jaar + "'> </td>");
							out.print("</tr>");
						}
					%>
				</table>
				<div class="button">
					<input type="submit" value="Sla betaalde bedrag op" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>
