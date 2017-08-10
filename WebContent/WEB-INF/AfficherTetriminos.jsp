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
			<li><a href="listeTetriminos">Liste Tetriminos</a></li>

		</ul>
		<ul class="right hide-on-med-and-down">
			<li><a href="listeJoueurs">Liste Joueurs</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="listeJoueurs">Liste Joueurs</a></li>
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
			<c:forEach items="${tetriminos}" var="item">
			    <li class="collection-item avatar">
			     	<c:choose>
				     	<c:when test="${ item.nom eq 'T' || item.nom eq 'L' || item.nom eq 'S' || item.nom eq 'carre' || item.nom eq 'ligne' }"><img src="img/<c:out value="${ item.nom }" />.png" alt="" class="circle"></c:when>
				     	<c:otherwise><img src="img/tetris.jpg" alt="" class="circle"></c:otherwise>
			     	</c:choose>
					<c:out value="${item.id}" /> : <c:out value="${item.nom}" /> : <c:out value="${item.couleur}" />
					<a href="supprimePiece?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Supprimer"><i class="material-icons">cancel</i></a>
					<a href="modifPiece?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Editer"><i class="material-icons">edit</i></a>
					<a href="affichePiece?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Afficher"><i class="material-icons">airplay</i></a>
				</li>
			</c:forEach>
		</div>
	</div>
</body>
</html>