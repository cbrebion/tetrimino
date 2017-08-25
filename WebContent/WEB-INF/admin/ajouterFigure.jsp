<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="/tetrimino/css/materialize.min.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
	
<link href="/tetrimino/css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Ajout d'une figure</title>
</head>

<body>
	<c:import url="/inc/menu.jsp" />
	<br>
	<div class="container">
		<!-- Définition de la taille (temporaire tant qu'on n'a pas implémenté la sélection de la taille -->
		<c:set var="tailleMatrice">
			<c:choose>
				<c:when test="${ empty taille }">3</c:when>
				<c:otherwise>${ taille }</c:otherwise>
			</c:choose>
		</c:set>
		
		<table class="centered bordered figure">
		<c:forEach var="x" begin="0" end="${ tailleMatrice }">
			<tr>
			<c:forEach var="y" begin="0" end="${ tailleMatrice }">
				<c:set var="selectionne">
					<c:forEach items="${ blocs }" var="item">
						<c:choose>
							<c:when test="${ item.x eq x and item.y eq y }">background-color: #b71c1c;</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</c:forEach>
				</c:set>
				<td class="bloc" style="border: 1px solid black;${ selectionne }">
					<!-- TO DO Si la case a été selectionnée, on change son fond -->
					<a style="display: block;" href="ajoutFigure?id=${ tetrimino.id }&x=${ x }&y=${ y }">&nbsp;</a>
				</td>
			</c:forEach>
			</tr>
		</c:forEach>
		</table>
		
		<p>
		<c:forEach items="${ blocs }" var="item">
			(${ item.x }, ${ item.y }) 
		</c:forEach>
		</p>
		
		<form method="post">
			<input type="text" name="ordre" placeholder="Ordre de rotation" />
			<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
				Envoyer <i class="material-icons right">send</i>
			</button>
		</form>
		
	</div>
	
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/tetrimino/js/materialize.min.js"></script>

</body>

</html>