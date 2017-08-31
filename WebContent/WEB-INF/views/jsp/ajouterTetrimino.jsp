<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="/tetrimino/css/materialize.min.css" type="text/css" rel="stylesheet"
	media="screen,projection" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Ajout de tetrimino</title>

<link href="/tetrimino/css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
</head>

<body>
	<c:import url="/inc/menu.jsp" />
	
	
	<c:set var="placeholderNom">
		<c:choose>
			<c:when test="${ empty erreurs['nom'] }">Nom</c:when>
			<c:otherwise>${ erreurs['nom'] }</c:otherwise>
		</c:choose>
	</c:set>
	<c:set var="valueCoeff">
		<c:choose>
			<c:when test="${ empty coeff }">0</c:when>
			<c:otherwise>${ coeff }</c:otherwise>
		</c:choose>
	</c:set>
	
	<div class="container">
		<form method="POST" class="row center">
			<p><c:out value ="${ placholderNom }"/></p>
			<h5>Nom Tetrimino</h5>
			<input type="text" name="nom" class="row center" placeholder="<c:out value="${ placeholderNom }" />" />
			<h5>Couleur</h5>
			<input type="color" name="couleur" class="row center" />
			<h5>Coefficient</h5>
			<input type="number" value="${ valueCoeff }" min="0" step="0.01" name="coeff" class="row center" />

			<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
				Ajouter <i class="material-icons right">send</i>
			</button>
		</form>
	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/tetrimino/js/materialize.min.js"></script>

</body>
</html>