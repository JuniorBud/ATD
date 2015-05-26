<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
	<head> 
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" /> 
		<title>Brandstof toevoegen</title>
		<link href="http://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet" type="text/css">
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
			<div><label class="label">Type</label>
				<input type="text" name="type" required placeholder="type" /></div>
			<div><label class="label">Tankstation Indentifictatie</label>
				<input type="text" name="tid" required placeholder="tid"/></div>
			<div><label class="label">Minimaal aantal liters in voorraad</label>
				<input type="text" name="minimumLiters" required placeholder="minimumLiters" /></div>
			<div><label class="label">Huidig aantal liters in voorraad</label>
				<input type="text" name="huidigeLiters" required palceholder="huidigeLiters" /></div>
			<div><label class="label">Prijs per liter</label>
			<input type="text" name="prijsPerLiter" required placeholder="prijsPerLiter" /></div>
 			<input type="hidden" name="soort" value="brandstof"/>
 			<input type="submit" name="toegevoegd" value="Brandstof toevoegen">
		</form>
		</div>
	</body> 
</html>