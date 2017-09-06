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
		
		<!-- Affichage des drapeaux pour l'internationalisation -->
		<c:set var="langCookie">${ cookie['lang'].value }</c:set>
		<c:set var="langURL">${ param.lang }</c:set>
		
		<c:choose>
			<c:when test="${ empty langURL }">
				<c:choose>
					<c:when test='${ langCookie.equals("fr") }'>
							<a href="${uri}?lang=en"><img title="Anglais" alt="Anglais" src="/tetrimino/img/en.png" style="float:right;" /></a>
					</c:when>
					<c:otherwise>
							<a href="${uri}?lang=fr"><img title="Français" alt="Français" src="/tetrimino/img/fr.png" style="float:right;" /></a>
					</c:otherwise>
				</c:choose>
			</c:when>
			
			<c:otherwise>
				<c:choose>
					<c:when test='${ langURL.equals("fr") }'>
							<a href="${uri}?lang=en"><img title="Anglais" alt="Anglais" src="/tetrimino/img/en.png" style="float:right;" /></a>
					</c:when>
					<c:when test='${ langURL.equals("en") }'>
							<a href="${uri}?lang=fr"><img title="Français" alt="Français" src="/tetrimino/img/fr.png" style="float:right;" /></a>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
		
		<!-- Déclaration du menu dropdown -->
		<ul id="menuFAQ" class="dropdown-content">
			<li><a href="/tetrimino/faq.xhtml">FAQ</a></li>
			<li><a href="/tetrimino/ajoutFAQ.xhtml">Ajout FAQ</a></li>
		</ul>
		
		
		<!-- Lien de déconnexion si connecté -->
		<c:if test="${ !empty sessionScope.admin or !empty sessionScope.joueur }">
			<ul class="right hide-on-med-and-down">
				<li><a href="/tetrimino/deconnexion"><spring:message code="navigation.deconnexion" /></a></li>
				<li><a class="dropdown-button" href="/tetrimino/faq.xhtml" data-activates="menuFAQ">FAQ</a></li>
			</ul>
		</c:if>
		
		<!-- Lien du menu, uniquement si connecté en tant qu'admin -->
		<c:if test="${ !empty sessionScope.admin }">
			<ul class="right hide-on-med-and-down">
				<li <c:if test='${ uri.equals("/tetrimino/listeJoueurs") }'>class="active"</c:if>>
					<a href="/tetrimino/listeJoueurs"><spring:message code="navigation.joueurs" /></a>
				</li>
			</ul>
			
			<ul class="right hide-on-med-and-down">
				<li <c:if test='${ uri.equals("/tetrimino/listeParties") }'>class="active"</c:if>>
					<a href="/tetrimino/listeParties"><spring:message code="navigation.parties" /></a>
				</li>
			</ul>
		
			<ul class="right hide-on-med-and-down">
				<li <c:if test='${ uri.equals("/tetrimino/listeTetriminos") }'>class="active"</c:if>>
					<a href="/tetrimino/listeTetriminos"><spring:message code="navigation.tetriminos" /></a>
				</li>
			</ul>
		</c:if>
	</div>
</nav>