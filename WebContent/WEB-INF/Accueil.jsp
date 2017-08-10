<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
	<nav class="light-red lighten-1" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="#" class="brand-logo">TETRIMINO</a>

		<ul class="right hide-on-med-and-down">
			<li><a href="#">Liste Tetriminos</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="#">Liste Tetriminos</a></li>
		</ul>
		<ul class="right hide-on-med-and-down">
			<li><a href="#">Liste Joueurs</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="#">Liste Joueurs</a></li>
		</ul>


		<a href="#" data-activates="nav-mobile" class="button-collapse"><i
			class="material-icons">menu</i></a>
	</div>
	</nav>

	<div class="container">
		<form method="POST" class="row center">
			<h5>Nom utilisateur</h5>
			<input type="text" name="username" value="<c:out value="${ valUsername }"/>" class="row center"></input><br>
			<h5>Mot de passe</h5>
			<input type="password" name="password" class="row center"></input>

			<button class="btn waves-effect waves-light red lighten-1" type="submit" name="action">
				Se connecter <i class="material-icons right">send</i>
			</button>
		</form>
	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

</body>
</html>