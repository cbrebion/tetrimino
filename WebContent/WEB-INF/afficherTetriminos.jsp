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

<style>
.posRelative {
	position : relative !important;
}
</style>

<title>Bienvenue sur Tetrimino</title>

<link href="/tetrimino/css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
</head>

<body>
	<c:import url="/inc/menu.jsp" />

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<div class="container">
		<ul class="collection">
			<c:forEach items="${tetriminos}" var="item">
			<li class="collection-item avatar">
				<div class="figureMini">
				<c:forEach var="x" begin="0" end="3">
					<c:forEach var="y" begin="0" end="3">
						<c:set var="selectionne">
							<c:forEach items="${ item.figures[0].blocs }" var="bloc">
								<c:choose>
									<c:when test="${ bloc.x eq x and bloc.y eq y }">style="background-color: ${ item.couleur };"</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</c:forEach>
						</c:set>
						<div class="blocMini" ${ selectionne }>
						</div>
					</c:forEach>
				</c:forEach>
				</div>
				<input type="color" value="<c:out value="${item.couleur}" />" disabled />&nbsp;<span class="nomTetrimino"><c:out value="${item.nom}" />&nbsp;</span> <span class="nomTetrimino">coeff : <c:out value="${item.coeff}" /></span>
				<div style="float: right;">
					<a href="supprimerPiece?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Supprimer"><i class="material-icons">cancel</i></a>
					<a href="modifPiece?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Editer"><i class="material-icons">edit</i></a>
					<a href="ajoutFigure?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Ajouter figure"><i class="material-icons">add_circle</i></a>
					<a href="tetrimino?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Afficher tetrimino"><i class="material-icons">visibility</i></a>
				</div>
				</li>
			</c:forEach>
		</ul>
		<p>
			<a class="waves-effect waves-light btn-large red darken-4" href="ajoutTetrimino"><i class="material-icons left">add</i>Ajouter Tetrimino</a>
		</p>
	</div>
</body>
</html>