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

<title>Modification d'une figure</title>
</head>

<body>
	<c:import url="/inc/menu.jsp" />
	
	<c:set var="valeurOrdre">
		<c:choose>
			<c:when test="${ empty erreurs['ordre'] }">value="${ figure.ordreRotation }"</c:when>
			<c:otherwise>placeholder="${ erreurs['ordre'] }"</c:otherwise>
		</c:choose>
	</c:set>
	
	<br>
	<div class="container">
		<!-- Définition de la taille (temporaire tant qu'on n'a pas implémenté la sélection de la taille -->
		<c:set var="tailleMatrice">
			<c:choose>
				<c:when test="${ empty taille }">3</c:when>
				<c:otherwise>${ taille }</c:otherwise>
			</c:choose>
		</c:set>
		
		<p><b>Modification d'une figure</b></p>
		<hr>
		<span class="erreur" style="color: red;">${ erreurs['bloc'] }</span>
		<div class="figure">
		<c:forEach var="x" begin="0" end="${ tailleMatrice }">
			<c:forEach var="y" begin="0" end="${ tailleMatrice }">
				<c:set var="selectionne">
					<c:forEach items="${ figure.blocs }" var="item">
						<c:choose>
							<c:when test="${ item.x eq x and item.y eq y }">style="background-color: #b71c1c;"</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</c:forEach>
				</c:set>
				<div class="bloc" ${ selectionne }>
					<!-- TO DO Si la case a été selectionnée, on change son fond -->
					<a style="display: block;" href="modifFigure?idTetrimino=<c:out value="${ idTetrimino }"/>&idFigure=${ figure.id }&x=${ x }&y=${ y }">&nbsp;</a>
				</div>
			</c:forEach>
		</c:forEach>
		</div>
		
		<p>
		<c:forEach items="${ figure.blocs }" var="item">
			(${ item.x }, ${ item.y }) 
		</c:forEach>
		</p>
		
		<form method="post">
			<input type="hidden" name="idFigure" value="${ figure.id }" />
			<input type="hidden" name="idTetrimino" value="${ idTetrimino }" />
			<input type="number" id="ordre" name="ordre" ${ valeurOrdre } />
			<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
				Modifier <i class="material-icons right">send</i>
			</button>
		</form>
		
	</div>
	
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/tetrimino/js/materialize.min.js"></script>

</body>

</html>