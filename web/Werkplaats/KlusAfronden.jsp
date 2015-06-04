<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Klus afronden</title>

<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css"/>
<%@ page import="Domain.Monteur"%>
<%@ page import="Domain.Werkplaats"%>
<%@ page import="Domain.Klus"%>
<%@ page import="Domain.Voorraad"%>
<%@ page import="Domain.Onderdeel"%>
<%@ page import="Domain.Reservering"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
<%@ page import="java.util.ArrayList"%>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">

		<%
			Object msgs = request.getAttribute("validation");
			if (msgs != null) {
				out.println("<div class='validation'>" + msgs + "</div>");
			}
		%>
		<form action="KlusAfronden.do" method="post">
			<div>
				<table>
					<tr>
						<th>Nummer</th>
						<th>omschrijving</th>
						<th>aantal in vooraad</th>
						<th>minimale voorraad</th>
					</tr>
					<%
						int i = 0;
						Voorraad voorraad = (Voorraad) application.getAttribute("voorraad");
						for (Onderdeel o : voorraad.getAlleOnderdelen()) {
					%>
					<tr>
						<td><%=o.getOnderdeelNummer()%></td>
						<td><%=o.getOnderdeelOmschrijving()%></td>
						<td><%=o.getOnderdeelVoorraad()%></td>
						<td><%=o.getOnderdeelMinimaleVoorraad()%></td>
						<td><input type="text" name=<%=o.getOnderdeelNummer()%>
							value="0"/></td>
					</tr>
					<%
						i++;
						}
					%>
				</table>
				<label class="label">Aantal Manuren</label> <select name="manuren">
					<%
						Klus k = (Klus) request.getSession().getAttribute("Klus");
						if (k == null) {
							RequestDispatcher rd = null;
							rd = request.getRequestDispatcher("Werkplaats/WeekplanningOverzicht.jsp");
							rd.forward(request, response);
						} else {
							ArrayList<Monteur> monteurs = k.getMonteurs();
							for (double j = (0.5 * monteurs.size()); j <= (monteurs.size() * 8); j += 0.5) {
								out.println("<option value='" + j + "'>" + j + "</option>");
							}
						}
					%>
				</select>
				<textarea name="opmerking" cols="38" rows="4" style="resize: none"
					required></textarea>
				<input type="hidden" name="soort" value="onderdeel" />
				<div class="button">
					<input type="submit" name="afgerond" value="Klus afronden" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>



