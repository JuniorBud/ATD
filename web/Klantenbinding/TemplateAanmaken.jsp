<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Maak Template</title>
<link href="http://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet" type="text/css">
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
		<div id="templateaanmaken">
			<form action="TemplateAanmaken.do" method="post">
				<div id="templatebox">
					<div id="tags">Mogelijke tags: [voornaam] [achternaam]
						[woonplaats] [laatste_onderhoud] [laatste_bezoek]</div>
					<div><label class="label">Templatenaam:</label>
					<input type="text" name="templateNaam" required placeholder="templateNaam"></div>
					<div><label class="label">Templatetekst:</label>
					<div><textarea name="templateTekst" rows="20" cols="50" required placeholde="templateTekst"
						wrap="soft" style="resize: none"></textarea></div>
					<div class="button">
						<input type="submit" value="Maak Template Aan" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>