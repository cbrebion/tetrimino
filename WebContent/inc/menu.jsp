<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="accueil">
	<c:choose>
		<c:when test="${sessionScope.joueur}">/tetrimino/accueilJoueur</c:when>
		<c:otherwise>/tetrimino/admin/accueilAdmin</c:otherwise>
	</c:choose>
</c:set>

<nav class="red darken-4" role="navigation">
<div class="nav-wrapper container">
	<a id="logo-container" href="${ accueil }" class="brand-logo">Tetrimino</a>

	<c:if test="${ !empty sessionScope.joueur or !empty sessionScope.admin }">
	
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
		
		<c:if test="${ !empty sessionScope.admin }">
			<ul class="right hide-on-med-and-down">
				<li><a href="/tetrimino/admin/listeParties">Liste Parties</a></li>
			</ul>
			<ul id="nav-mobile" class="side-nav">
				<li><a href="/tetrimino/admin/listeParties">Liste Parties</a></li>
			</ul>
		</c:if>
	
		<ul class="right hide-on-med-and-down">
			<li><a href="/tetrimino/deconnexion">Déconnexion</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="/tetrimino/deconnexion">Déconnexion</a></li>
		</ul>
	
		<a href="#" data-activates="nav-mobile" class="button-collapse"><i
			class="material-icons">menu</i></a>
	</c:if>
</div>
</nav>