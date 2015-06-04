<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<title>Werkzaamheid Inplannen</title>


<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css"/>

<%@ page import="Domain.Werkplaats"%>
<%@ page import="Domain.Klus"%>
<%@ page import="Domain.Monteur"%>
    <%@ page import="java.text.SimpleDateFormat" %>

</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<form action="KlusInplannen.do" method="post">
			<div id="select">
				<select id="Klus" name="Klus" onchange='getTijden()'>
					<%
						Werkplaats werkplaats = (Werkplaats) application.getAttribute("werkplaats");
						int i = 0;
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						for (Klus k : werkplaats.getAlleOningeplandeWerkzaamheden()) {
					%>
					<option value="<%=i%>"><%=dateFormat.format(k.getAangemaakt()) + " - " + k.getKlusType() + " - " + k.getAuto().getKenteken() + " (" + k.getVerwachteUrenNodig() + "uur)"%></option>
					<%
						i++;
						}
					%>
				</select></br> <select id="dagen" name="dag" onchange='getTijden()'>
					<option value="1">Maandag</option>
					<option value="2">Dinsdag</option>
					<option value="3">Woensdag</option>
					<option value="4">Donderdag</option>
					<option value="5">Vrijdag</option>
				</select> <select id="tijden" name="tijden">
				</select>
			</div>
			<div id="monteurs">
				<%
					for (Monteur m : werkplaats.getAlleMonteurs()) {
				%>
				<input type="checkbox" onclick='getTijden()'
					name=<%=m.getNaam() + "-" + m.getTussenvoegsel() + "-" + m.getAchternaam()%>>
					<%=m.getNaam() + " " + m.getTussenvoegsel() + " " + m.getAchternaam()%></br>
					<%
						}
					%>
				
			</div>
			<div class="button">
				<input type="submit" name="inplannen" value="Inplannen">
			</div>
		</form>
	</div>
</body>
</html>
