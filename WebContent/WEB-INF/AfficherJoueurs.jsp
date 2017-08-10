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

<style>
.posRelative {
	position : relative !important;
}
</style>

<title>Bienvenue sur Tetrimino</title>
</head>

<body>
	<nav class="light-red lighten-1" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="#" class="brand-logo">TETRIMINO</a>

		<ul class="right hide-on-med-and-down">
			<li><a href="listeTetriminos">Liste Tetriminos</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="#">Liste Tetriminos</a></li>
		</ul>
		<ul class="right hide-on-med-and-down">
			<li><a href="listeJoueurs">Liste Joueurs</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="#">Liste Joueurs</a></li>
		</ul>


		<a href="#" data-activates="nav-mobile" class="button-collapse"><i
			class="material-icons">menu</i></a>
	</div>
	</nav>
	</div>

	</form>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<div class="container">
		<div class="collection">
			<c:forEach items="${joueurs}" var="item">
			    <li class="collection-item avatar">
					<c:out value="${item.id}" /> : <c:out value="${item.nom}" /> : <c:out value="${item.couleur}" />
					<a href="#!" class="secondary-content posRelative"><i class="material-icons">airplay</i></a> 
					<a href="#!" class="secondary-content posRelative"><i class="material-icons">edit</i></a> 
					<a href="#!" class="secondary-content posRelative"><i class="material-icons">cancel</i></a> 
				</li>
			</c:forEach>
		</div>
	</div>
</body>
</html>