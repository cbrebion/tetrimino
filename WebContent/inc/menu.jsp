<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="light-red lighten-1" role="navigation">
<div class="nav-wrapper container">
	<a id="logo-container" href="accueil" class="brand-logo">TETRIMINO</a>

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
		<ul class="right hide-on-med-and-down">
			<li><a href="/tetrimino/admin/listeParties">Liste Parties</a></li>
		</ul>
		<ul id="nav-mobile" class="side-nav">
			<li><a href="/tetrimino/admin/listeParties">Liste Parties</a></li>
		</ul>
	
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