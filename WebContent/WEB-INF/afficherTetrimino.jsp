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

<title>Tetrimino ${ tetrimino.nom }</title>
</head>

<body>
	<c:import url="/inc/menu.jsp" />
	
	
	<!-- Définition de la taille (temporaire tant qu'on n'a pas implémenté la sélection de la taille -->
		<c:set var="tailleMatrice">
			<c:choose>
				<c:when test="${ empty taille }">3</c:when>
				<c:otherwise>${ taille }</c:otherwise>
			</c:choose>
		</c:set>

	<div class="container">
		<p>Nom du tetrimino : ${ tetrimino.nom }</p>
		<p>Couleur : ${ tetrimino.couleur }</p>
		
		<!-- Affichage des figures correspondantes -->
		<table class="centered bordered">
		<c:forEach items="${ tetrimino.figures }" var="figure">
			<c:forEach var="x" begin="0" end="${ tailleMatrice }">
				<tr>
				<c:forEach var="y" begin="0" end="${ tailleMatrice }">
				
					<c:set var="selectionne">
						<c:forEach items="${ figure.blocs }" var="bloc">
							<c:choose>
								<c:when test="${ bloc.x eq x and bloc.y eq y }">background-color: #b71c1c;</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</c:forEach>
					</c:set>
					
					<td style="border: 1px solid black;${ selectionne }">
					</td>
				</c:forEach>
				</tr>
			</c:forEach>
		</c:forEach>
		</table>
	</div>
	
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>