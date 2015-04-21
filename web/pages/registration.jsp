<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Registration</title>
</head>
<body>
<form method="post" action="/register.do">
    <table>
      <thead>
      <tr>
        <th colspan="2">Registratie</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>Voornaam</td>
        <td><input type="text" name="voornaam" value="" /></td>
      </tr>
      <tr>
        <td>Achternaam</td>
        <td><input type="text" name="achternaam" value="" /></td>
      </tr>
      <tr>
        <td>Email</td>
        <td><input type="text" name="email" value="" /></td>
      </tr>
      <tr>
        <td>Tel. nummer</td>
        <td><input type="text" name="telnr" value=""></td>
      </tr>
      <tr>
        <td>Adres</td>
        <td><input type="text" name="adres" value=""> </td>
      </tr>
      <tr>
        <td>Woonplaats</td>
        <td><input type="text" name="plaats" value=""> </td>
      </tr>
      <tr>
        <td>Gebruikersnaam</td>
        <td><input type="text" name="username" value="" /></td>
      </tr>
      <tr>
        <td>Wachtwoord</td>
        <td><input type="password" name="password" value="" /></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="reset" value="Reset" /><input type="submit" value="Submit" /></td>
      </tr>
      <tr>
        <td colspan="2">Al een account? <a href="/pages/login.jsp">Log hier in</a></td>
      </tr>
      </tbody>
    </table>
</form>
</body>
</html>