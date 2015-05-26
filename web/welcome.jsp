<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welkom</title>
</head>
<body>
<h1>Welkom</h1>
<div>
<form method = "get" action = "inplannen.jsp">
<input type = "submit" value = "Afspraak maken">
</form>
</div>
<div>
<form method = "get" action = "LaadAutosServlet.do">
<input type = "submit" value = "Uw auto's">
</form>
</div>
<div>
<form method = "get" action = "Start.jsp">
<input type = "submit" value = "Uitloggen">
</form>
</div>
</body>
</html>