<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start</title>
</head>
<body>
<h1>Hallo</h1>
<form method = "get" action = "KlantAanmaken.jsp">
Registreren: <input type = "submit" value = "Registreren">
</form>
<form method = "get" action = "monteur.jsp">
Monteur: <input type = "submit" value = "Zie afspraken">
</form>
<h2>Inloggen</h2>
<%
	Object message = request.getAttribute("error");
	if(message != null){
		out.println(message);
	}
%>
<form method = "post" action = "InloggenServlet.do">
Email: <input type = "text" name = "email">
Wachtwoord: <input type = "password" name = "wachtwoord">
<input type = "submit" value = "Inloggen">
</form>
</body>
</html>