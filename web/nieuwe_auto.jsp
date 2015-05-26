<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nieuwe auto registreren</title>
</head>
<body>
<h1>Nieuwe auto registreren</h1>
Graag willen wij het volgende van uw auto weten:
<form method = "post" action = "AutoRegistrerenServlet.do">
<div>Merk: <input type = "text" name = "merk"></div>
<div>Type: <input type = "text" name = "type"></div>
<div>Kenteken: <input type = "text" name = "kenteken"></div>
<div><input type = "submit" value = "Registreren"></div>
</form>
</body>
</html>