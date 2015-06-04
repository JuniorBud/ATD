<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Reservering aanmaken</title>
			<%@ page import="Domain.Reservering"%>
			<link href='http://fonts.googleapis.com/css?family=Roboto:300'
				rel='stylesheet' type='text/css'>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<div id="error">
			<%
				Object validation = request.getAttribute("validation");
				if (validation != null) {
					out.println(validation);
				}
			%>
		</div>
		<div id="reserveringaanmaken">
			<form action='ReserveringAanmaken.do' method='get'>
				<div>
					<label class="label">Naam</label> <input type="text" name="naam"
						id="naam" required placeholder="Naam" />
				</div>
				<div>
					<label class="label">Kenteken</label> <input type="text"
						name="kenteken" id="kenteken" required placeholder="Kenteken" />
				</div>
				<div>
					<label class="label">Begin Datum</label> <input type="date"
						id="beginDatum" required onchange="updatePlekken()"
						name="beginDatum" />
				</div>
				<div>
					<label class="label">Begin Tijd</label> <input type="time"
						id="beginTijd" name="beginTijd" required
						onchange="updatePlekken()" />
				</div>
				<div>
					<label class="label">Eind Datum</label> <input type="date"
						id="eindDatum" required name="eindDatum"
						onchange="updatePlekken()" />
				</div>
				<div>
					<label class="label">Eindtijd</label> <input type="time"
						id="eindTijd" required name="eindTijd" onchange="updatePlekken()" />
				</div>
				<div id="invalide">
					<label class="label">Invalide</label> <input type="checkbox" name="invalide" onclick="updatePlekken()"
						value="invalide" />
				</div>
				<div id="plek">
					<label class="label">Plek</label> <select id="plekDrop" required
						name="plek">
					</select>
				</div>
				<div class="button">
					<input type="submit" value="reserveer" />
				</div>
		</form>
	</div>
    </div>
</body>
</html>
