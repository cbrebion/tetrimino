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

<title>Bienvenue sur Tetrimino</title>
</head>

<body>
	<c:import url="/inc/menu.jsp" />
	<br>
	<div class="container">
		<p>
			<a class="waves-effect waves-light btn-large red darken-4" href="ajoutTetrimino"><i class="material-icons left">add</i>Ajouter Tetrimino</a>
		</p>
		<p>
			<a class="waves-effect waves-light btn-large red darken-4" href="listeParties"><i class="material-icons left">list</i>Liste des parties</a>
		</p>
		<p>
			<a class="waves-effect waves-light btn-large red darken-4" href="listeTetriminos"><i class="material-icons left">view_list</i>Liste des tetriminos</a>
		</p>
		<p>
			<a class="waves-effect waves-light btn-large red darken-4" href="listeJoueurs"><i class="material-icons left">list</i>Liste des joueurs</a>
		</p>
	</div>
	
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/tetrimino/js/materialize.min.js"></script>

</body>

</html>