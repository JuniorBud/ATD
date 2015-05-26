<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Auto's</title>
</head>
<body>
<H1>Uw auto's</H1>
<h2><%if(request.getAttribute("auto0") != null){out.println(request.getAttribute("auto0"));} %></h2>
<h2><%if(request.getAttribute("auto0") != null){out.println(request.getAttribute("auto1"));} %></h2>
<h2><%if(request.getAttribute("auto0") != null){out.println(request.getAttribute("auto2"));} %></h2>
<h2><%if(request.getAttribute("auto0") != null){out.println(request.getAttribute("auto3"));} %></h2>
<h2><%if(request.getAttribute("auto0") != null){out.println(request.getAttribute("auto4"));} %></h2>
<p>U kunt een maximaal van 5 auto's registreren </p>
<form method = "get" action = "nieuwe_auto.jsp">
<input type = "submit" value = "Nieuwe auto registreren">
</form>
<form method = "get" action = "welcome.jsp">
<input type = "submit" value = "Terug">
</form>
</body>
</html>