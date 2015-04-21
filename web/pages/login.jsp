<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>ATD Login</title>
</head>
<body>
<form method="post" action="/login.do">
    <table>
      <thead>
      <tr>
        <th colspan="2">Inloggen bij ATD</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>Gebruikersnaam</td>
        <td><input type="text" name="uname" value="" /></td>
      </tr>
      <tr>
        <td>Wachtwoord</td>
        <td><input type="password" name="pass" value="" /></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="reset" value="Reset" /><input type="submit" value="Login" /></td>
      </tr>
      <tr>
        <td colspan="2">Nog niet geregistreerd? <a href="/pages/registration.jsp">Registreer hier</a></td>
      </tr>
      </tbody>
    </table>
</form>
</body>
</html>
