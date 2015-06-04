<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Klus aanmaken</title>


<%@ page import="Domain.Werkplaats"%>
<%@ page import="Domain.Klantbinding"%>
<%@ page import="Domain.Klant"%>


<script type="text/javascript">
	function klantAjax() {
		getKlant();
		autoLijst();
		getAuto();
	}
	function startup() {
		getKlant();
		getAuto();
	}
</script>
</head>
<body onload="startup()">
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<form action='Klus.do' method='post'>
			<%
				Object msgs = request.getAttribute("validation");
				if (msgs != null) {
					out.println("<div class='validation'>" + msgs + "</div>");
				}
			%>
			<div id="alles">
				<div class="klantInformatie">
					<label class="label">Type werkzaamheid</label> <label class="label">Klant</label>
					<label class="label">Voornaam</label> <label class="label">Tussenvoegsel</label>
					<label class="label">Achternaam</label> <label class="label">Geboortedatum</label>
					<label class="label">Straatnaam</label> <label class="label">Huisnummer</label>
					<label class="label">Toevoeging</label> <label class="label">Postcode</label>
					<label class="label">Woonplaats</label>
				</div>
				<div class="klantGegevens">
					<select name="type"><br />
						<option value="reparatie">Reparatie</option>
						<option value="onderhoud">Onderhoud</option>
					</select></br> <select id="klant" name="klant" onchange="klantAjax()">
						<option value="nieuw">Nieuwe Klant</option>
						<%
							Klantbinding klantbinding = (Klantbinding) application.getAttribute("klantbinding");
							for (Klant k : klantbinding.getAlleKlanten()) {
								if (!k.isGeblokkeerd()) {
									String naam = k.getAchternaam() + ", " + k.getNaam();
									if (!k.getTussenvoegsel().equals("")) {
										naam += " " + k.getTussenvoegsel();
									}
						%>
						<option
							value='<%=k.getNaam() + "-" + k.getTussenvoegsel() + "-" + k.getAchternaam()%>'>
							<%=naam%></option>
						<%
							}
							}
						%>
					</select>
					<div id="klantContent"></div>
				</div>
				<div class="autoInformatie">
					<label class="label">Auto</label> <label class="label">Merk</label>
					<label class="label">Model</label> <label class="label">Kenteken</label>
					<label class="label">Bouwjaar</label> <label class="label">Verwachte
						manuren</label> <label class="label">Opmerkingen</label>
				</div>
				<div class="autoGegevens">
					<select id="auto" name="auto" onchange="getAuto()">
						<option value="nieuw">Nieuwe Auto</option>
					</select>
					<div id="autoContent"></div>
					<div>
						<select name="manuren">
							<%
								Werkplaats werkplaats = (Werkplaats) application.getAttribute("werkplaats");
								int monteurs = werkplaats.getAlleMonteurs().size();
								for (double i = 0.5; i <= (monteurs * 8); i += 0.5) {
									out.println("<option value='" + i + "'>" + i + "</option>");
								}
							%>
						</select>
						<textarea name="opmerking" cols="38" rows="4" style="resize: none"></textarea>
					</div>
				</div>
			</div>
			<div class="button">
				<input type="submit" name="aangemaakt" value="Maak Klus aan">
			</div>
		</form>
	</div>
</body>
</html>
