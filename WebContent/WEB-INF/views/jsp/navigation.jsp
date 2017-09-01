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
	
		<c:set var="uri" value="${ requestScope['javax.servlet.forward.request_uri'] }" />
		
		<ul class="right hide-on-med-and-down">
			<li <c:if test='${ uri.equals("/tetrimino/listeTetriminos") }'>class="active"</c:if>><a href="/tetrimino/listeTetriminos">Liste Tetriminos</a></li>
		</ul>
		
		<ul class="right hide-on-med-and-down">
			<li <c:if test='${ uri.equals("/tetrimino/listeJoueurs") }'>class="active"</c:if>><a href="/tetrimino/listeJoueurs">Liste Joueurs</a></li>
		</ul>
		
		<ul class="right hide-on-med-and-down">
			<li <c:if test='${ uri.equals("/tetrimino/listeParties") }'>class="active"</c:if>><a href="/tetrimino/listeParties">Liste Parties</a></li>
		</ul>
	
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