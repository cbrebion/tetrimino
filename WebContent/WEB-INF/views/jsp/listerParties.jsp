<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="collection">
		<c:forEach items="${parties}" var="item">

			<c:set var="etatPartie">
				<c:choose>
					<c:when test="${item.finie}">Partie finie</c:when>
					<c:otherwise>Partie en cours</c:otherwise>
				</c:choose>
			</c:set>
			
			<c:set var="typePartie">
				<c:choose>
					<c:when test="${!item.type}">Solo</c:when>
					<c:otherwise>1 vs 1</c:otherwise>
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
				<b>Partie <c:out value="${item.id}" /> - <c:out value="${typePartie}" /></b><br>
				Score <c:out value="${item.joueur1.username}" /> : <c:out value="${ scoreJoueur1 }" /><br>
				<c:if test="${ !empty item.joueur2 }">
					Score <c:out value="${item.joueur2.username}" /> : <c:out value="${ scoreJoueur2 }" /><br>
				</c:if>
				<c:out value="${etatPartie}" />
			</div>
		</c:forEach>
	</div>
</div>