<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="accueil">
	<c:choose>
		<c:when test="${ empty sessionScope.admin }">/tetrimino/accueil</c:when>
		<c:otherwise>/tetrimino/accueilAdmin</c:otherwise>
	</c:choose>
</c:set>

<nav class="red darken-4" role="navigation">
<div class="nav-wrapper container">
	<a id="logo-container" href="${ accueil }" class="brand-logo">Tetrimino</a>

	<c:if test="${ !empty sessionScope.admin }">
	
		<ul class="right hide-on-med-and-down">
			<li><a href="/tetrimino/listeTetriminos">Liste Tetriminos</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="/tetrimino/listeTetriminos">Liste Tetriminos</a></li>
		</ul>
		<ul class="right hide-on-med-and-down">
			<li><a href="/tetrimino/listeJoueurs">Liste Joueurs</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="/tetrimino/listeJoueurs">Liste Joueurs</a></li>
		</ul>
		
		<ul class="right hide-on-med-and-down">
			<li><a href="/tetrimino/listeParties">Liste Parties</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="/tetrimino/listeParties">Liste Parties</a></li>
		</ul>
	
		<a href="#" data-activates="nav-mobile" class="button-collapse"><i
			class="material-icons">menu</i></a>
	</c:if>
	
	<c:if test="${ !empty sessionScope.admin or !empty sessionScope.joueur }">
		<ul class="right hide-on-med-and-down">
			<li><a href="/tetrimino/deconnexion">Déconnexion</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="/tetrimino/deconnexion">Déconnexion</a></li>
		</ul>
	</c:if>
</div>
</nav>