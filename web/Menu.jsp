<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="Domain.User"%>
</head>
<body>

	<%
		User u = (User) request.getSession().getAttribute("user");
		int permission = 0;
		if (u == null) {
			permission = 1;
		} else {
			permission = u.getPermission();
		}
	%>
	<div id="menu">
		<ul>
			<li><a href="#">Werkplaats</a>
				<ul class="hidden">
					<li><a
						href="/THO4/Werkplaats/WeekplanningOverzicht.jsp">Overzicht</a></li>
					<%
						if (permission < 3) {
					%>
					<li><a
						href="/THO4/Werkplaats/KlusAanmaken.jsp">Nieuwe
							werkzaamheid</a></li>
					<li><a
						href="/THO4/Werkplaats/KlusInplannen.jsp">Werkzaamheid
							inplannen</a></li>
					<%
						}
					%>
				</ul></li>
			<li><a href="#">Voorraadbeheer</a>
				<ul class="hidden">
					<li><a
						href="/THO4/Voorraadbeheer/OnderdelenOverzicht.jsp">Onderdelen</a></li>
					<%
						if (permission < 3) {
					%>
					<li><a
						href="/THO4/Voorraadbeheer/BrandstofOverzicht.jsp">Brandstoffen</a></li>
					<%
						}
					%>
				</ul></li>
			<%
				if (permission < 3) {
			%>
			<li><a href="#">Parkeergarage</a>
				<ul class="hidden">
					<li><a
						href="/THO4/Reserveringen/ReserveringOverzicht.jsp">Overzicht</a></li>
					<li><a
						href="/THO4/Reserveringen/ReserveringAanmaken.jsp">Nieuwe
							reservering</a></li>
				</ul></li>
			<li><a href="#">Klanten</a>
				<ul class="hidden">
					<li><a
						href="/THO4/Klantenbinding/BrievenGenereren.jsp">Klanten
							overzicht</a></li>
					<li><a
						href="/THO4/Klantenbinding/TemplateOverzicht.jsp">Template
							overzicht</a></li>
					<li><a
						href="/THO4/Klantenbinding/TemplateAanmaken.jsp">Template
							aanmaken</a></li>

				</ul></li>
			<%
				}
			%>
			<%
				if (permission == 1) {
			%>
			<li><a href="#">Instellingen</a>
				<ul class="hidden">
					<li><a
						href="/THO4/Instellingen/AccountAanmaken.jsp">Account
							aanmaken</a></li>
				</ul></li>
			<%
				}
			%>
			<li><a href="/THO4/Logout.do">Uitloggen</a></li>
		</ul>
	</div>

	<div id="welkom">Welkom ${user.username}, je bent ingelogd als
		${user.type}</div>
</body>
</html>
