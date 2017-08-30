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

<style>
.posRelative {
	position : relative !important;
}
</style>

<title>Liste des parties</title>
</head>

<body>
	<c:import url="/inc/menu.jsp" />

	<div class="container">
		<div class="collection">
			<c:forEach items="${parties}" var="item">

				<c:set var="etatPartie">
					<c:choose>
						<c:when test="${item.finie}">Partie finie</c:when>
						<c:otherwise>Partie en cours</c:otherwise>
					</c:choose>
				</c:set>
				
				<c:set var="scoreJoueur1">null</c:set>
				<c:set var="scoreJoueur2">null</c:set>
				
				<c:forEach items="${ item.scores }" var="score">
					<c:if test="${ score.joueur.id eq item.joueur1.id }">
						<c:set var="scoreJoueur1">${ score.points }</c:set>
					</c:if>
					<c:if test="${ score.joueur.id eq item.joueur2.id }">
						<c:set var="scoreJoueur2">${ score.points }</c:set>
					</c:if>
				</c:forEach>

				<div class="collection-item avatar">
					<b>Partie <c:out value="${item.id}" /></b><br>
					Score <c:out value="${item.joueur1.username}" /> : <c:out value="${ scoreJoueur1 }" /><br>
					Score <c:out value="${item.joueur2.username}" /> : <c:out value="${ scoreJoueur2 }" /><br>
					<c:out value="${etatPartie}" />
				</div>
			</c:forEach>
		</div>
	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/tetrimino/js/materialize.min.js"></script>
</body>
</html>