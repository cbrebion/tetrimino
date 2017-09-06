<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<h5><b>Liste des joueurs</b></h5>
	<ul class="collection">

		<c:forEach items="${joueurs}" var="item">
		
			<c:set var="etatJoueur">
				<c:choose>
					<c:when test="${item.banni}">Joueur banni</c:when>
					<c:otherwise>Joueur actif</c:otherwise>
				</c:choose>
			</c:set>
			<c:set var="iconeBannir">
				<c:choose>
					<c:when test="${ item.banni }">thumb_down</c:when>
					<c:otherwise>thumb_up</c:otherwise>
				</c:choose>
			</c:set>
		
		
		    <li class="collection-item avatar">
		      <span class="title"><b><c:out value="${item.username}" /></b></span>
		      <p><c:out value="${item.nom}" /><br>
		         <c:out value="${item.prenom}" /><br>
		         <c:out value="${etatJoueur}" />
		         <a href="bannir?id=<c:out value="${ item.id }"/>" class="secondary-content" title="Bannir" onclick="Materialize.toast('Effectué !', 4000, 'rounded')"><i class="material-icons">${ iconeBannir }</i></a>
		      </p>
		    </li>
		</c:forEach>
	
	</ul>
</div>