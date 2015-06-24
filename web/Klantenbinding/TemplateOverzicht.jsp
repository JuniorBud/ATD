<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Template Overzicht</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300"
	rel="stylesheet" type="text/css">
<%@ page import="java.util.ArrayList"%>
<%@ page import="Domain.Klantbinding"%>
<%@ page import="Domain.Template"%>
</head>
<body>
	<jsp:include page="../Menu.jsp" />
	<div id="content">
		<div id="error">
			<%
				Object msgs = request.getAttribute("msgs");
				if (msgs != null) {
			%>
			<div class="success"><%=msgs%></div>
			<%
				}
			%>
		</div>
		<form action="TemplateAanpassen.jsp">
			<div id="buttons">
				<div class="button">
					<input type="submit" value="Template(s) Aanpassen" />
				</div>
				<div class="button">
					<input type="submit" onclick="form.action='TemplateVerwijderen.do'"
						value="Template(s) Verwijderen" />
				</div>
			</div>
			<div>
				<table>
					<tr>
						<th>Selecteer</th>
						<th>Templatenaam</th>
					</tr>


					<%
						Klantbinding klantbinding = (Klantbinding) application.getAttribute("klantbinding");
						for (Template t : klantbinding.getAlleTemplates()) {
					%>
					<tr>
						<td><input type="checkbox" name=<%=t.getTemplateNaam()%>>
						</td>
						<td><%=t.getTemplateNaam()%></td>
					</tr>
					<%
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
