<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Onderdeel toevoegen</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css">
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
		<form action='VoorraadBeheer.do' method='post'>

			<div>
				<label class="label">Onderdeel nummer</label> <input type="text"
					name="onderdeelNummer" required placeholder="Onderdeel Nummer" />
			</div>
			<div>
				<label class="label">Onderdeel omschrijving</label> <input
					type="text" name="omschrijving" required placeholder="Omschrijving" />
			</div>
			<div>
				<label class="label">Minimaal aantal onderdelen in voorraad</label>
				<input type="text" name="minimaleVoorraad" required
					placeholder="Minimale Voorraad" />
			</div>
			<div>
				<label class="label">Huidig aantal onderdelen in voorraad</label> <input
					type="text" name="huidigeVoorraad" required
					palceholder="Huidige Voorraad" />
			</div>
			<input type="hidden" name="soort" value="onderdeel" /> <input
				type="submit" name="toegevoegd" value="Onderdeel toevoegen">
		</form>
	</div>
</body>
</html>