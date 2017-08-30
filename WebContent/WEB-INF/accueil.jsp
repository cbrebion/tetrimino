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
	
	<c:set var="placeholderUsername">
		<c:choose>
			<c:when test="${ empty erreurs['username'] }">Nom d'utilisateur</c:when>
			<c:otherwise>${ erreurs['username'] }</c:otherwise>
		</c:choose>
	</c:set>
	<c:set var="placeholderPassword">
		<c:choose>
			<c:when test="${ empty erreurs['password'] }">Mot de passe</c:when>
			<c:otherwise>${ erreurs['password'] }</c:otherwise>
		</c:choose>
	</c:set>

	<div class="container">
		<form method="POST" class="row center">
			<span class="erreur" style="color: red;">${ erreurs['connexion'] }</span>
			<h5>Nom utilisateur</h5>
			<input type="text" name="username" value="<c:out value="${ valUsername }"/>" class="row center" placeholder="${ placeholderUsername }"></input><br>
			<h5>Mot de passe</h5>
			<input type="password" name="password" class="row center" placeholder="${ placeholderPassword }"></input>

			<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
				Se connecter <i class="material-icons right">send</i>
			</button>
			<a href="signin" class="waves-effect waves-light btn blue darken-4"><i class="material-icons right">edit</i>S'inscrire</a>
		</form>
	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

</body>
</html>