<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.min.css" type="text/css" rel="stylesheet"
	media="screen,projection" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Bienvenue sur Tetrimino</title>
</head>

<body>
	<c:import url="/inc/menu.jsp" />

	<div class="container">
		<form method="post" action="signin">
			<input type="hidden" name="action" value="signin" />
			
			<div class="input-field">
				<i class="material-icons prefix">account_circle</i>
				<input id="username" type="text" class="validate" name="username" />
				<label for="username">Votre nom d'utilisateur</label>
			</div>
			
			<div class="input-field">
				<i class="material-icons prefix">account_circle</i>
				<input id="nom" type="text" class="validate" name="nom" />
				<label for="nom">Votre nom</label>
			</div>
			
			<div class="input-field">
				<i class="material-icons prefix">account_circle</i>
				<input id="prenom" type="text" class="validate" name="prenom" />
				<label for="prenom">Votre prénom</label>
			</div>
			
			<div class="input-field">
				<i class="material-icons prefix">vpn_key</i>
				<input id="password" type="password" class="validate" name="password" />
				<label for="password">Votre mot de passe</label>
			</div>
			
			<button class="btn waves-effect waves-light red darken-4" type="submit">
				OK <i class="material-icons right">send</i>
			</button>
		</form>
	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

</body>
</html>