<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue sur Tetrimino</title>
</head>
<body>

		<form method="POST">
			<h1>Nom utilisateur</h1>
			 <input type="text" name="username" ></input><br>
			 <h1>Mot de passe</h1>
			 <input type="password" name="password" ></input>
			<input type="submit" name="submit" value="connexion" ></input>
			<br>
		</form>	

</body>
</html>