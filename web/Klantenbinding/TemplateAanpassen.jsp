<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pas Template Aan</title>
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
					out.println(msgs);
				}
			%>
		</div>
		<form action="TemplateAanpassen.do" method="post">
			<div id="tags">Mogelijke tags: [voornaam] [achternaam]
				[woonplaats] [laatste_onderhoud] [laatste_bezoek]</div>
			<%
				Klantbinding klantbinding = (Klantbinding) application.getAttribute("klantbinding");
				for (Template t : klantbinding.getAlleTemplates()) {
					Object template = request.getParameter(t.getTemplateNaam());
					if (template != null) {
			%>
			<div>
				<label class="label">Templatenaam:</label>
			</div>
			<div>
				<label class="templateNaam"> <%=t.getTemplateNaam()%></label>
			</div>
			<div>
				<label class="label">Templatetekst:</label>
			</div>
			<div class="templateInhoud">
				<textarea name="<%=t.getTemplateNaam()%>" rows="20" cols="50"
					required placeholder="templateInhoud" wrap="soft"
					style="resize: none"><%=t.getTemplateInhoud()%></textarea>
			</div>
			<%
				}
				}
			%>


			<div class="button">
				<input type="submit" value="Template(s) Aanpassen" />
			</div>
		</form>
	</div>
</body>
</html>