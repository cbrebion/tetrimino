<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="accueil">
	<c:choose>
		<c:when test="${ empty sessionScope.admin }">/tetrimino/accueil</c:when>
		<c:otherwise>/tetrimino/accueilAdmin</c:otherwise>
	</c:choose>
</c:set>

<c:set var="uri" value="${ requestScope['javax.servlet.forward.request_uri'] }" />

<nav class="red darken-4" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="${ accueil }" class="brand-logo">Tetrimino</a>
		
		<c:if test="${ !empty sessionScope.admin }">
			<ul class="right hide-on-med-and-down">
				<li <c:if test='${ uri.equals("/tetrimino/listeTetriminos") }'>class="active"</c:if>><a href="/tetrimino/listeTetriminos"><spring:message code="navigation.tetriminos" /></a></li>
			</ul>
			
			<ul class="right hide-on-med-and-down">
				<li <c:if test='${ uri.equals("/tetrimino/listeJoueurs") }'>class="active"</c:if>><a href="/tetrimino/listeJoueurs"><spring:message code="navigation.joueurs" /></a></li>
			</ul>
			
			<ul class="right hide-on-med-and-down">
				<li <c:if test='${ uri.equals("/tetrimino/listeParties") }'>class="active"</c:if>><a href="/tetrimino/listeParties"><spring:message code="navigation.parties" /></a></li>
			</ul>
		
		</c:if>
		
		<c:if test="${ !empty sessionScope.admin or !empty sessionScope.joueur }">
			<ul class="right hide-on-med-and-down">
				<li><a href="/tetrimino/deconnexion"><spring:message code="navigation.deconnexion" /></a></li>
			</ul>
		</c:if>
		
		<!-- OK mais nécessité de refresh la page pour que le drapeau change -->
		<c:set var="lang">${ cookie['lang'].value }</c:set>
		<c:if test='${ !lang.equals("fr") }'><a href="${uri}?lang=fr"><img alt="Français" src="/tetrimino/img/fr.png" style="float:right;" /></a></c:if>
		<c:if test='${ !lang.equals("en") }'><a href="${uri}?lang=en"><img alt="Anglais" src="/tetrimino/img/en.png" style="float:right;" /></a></c:if>
		
	</div>
</nav>