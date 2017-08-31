<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
	<!-- Définition de la taille (temporaire tant qu'on n'a pas implémenté la sélection de la taille -->
	<c:set var="tailleMatrice">
		<c:choose>
			<c:when test="${ empty taille }">3</c:when>
			<c:otherwise>${ taille }</c:otherwise>
		</c:choose>
	</c:set>
	
	<p><b>Modification d'une figure</b></p>
	<hr>
	<span class="erreur" style="color: red;">${ erreurs['bloc'] }</span>
	<div class="figure">
	<c:forEach var="x" begin="0" end="${ tailleMatrice }">
		<c:forEach var="y" begin="0" end="${ tailleMatrice }">
			<c:set var="selectionne">
				<c:forEach items="${ figure.blocs }" var="item">
					<c:choose>
						<c:when test="${ item.x eq x and item.y eq y }">style="background-color: ${ tetrimino.couleur };"</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</c:forEach>
			</c:set>
			<div class="bloc" ${ selectionne }>
				<!-- TO DO Si la case a été selectionnée, on change son fond -->
				<a style="display: block;" href="modifFigure?idTetrimino=<c:out value="${ tetrimino.id }"/>&idFigure=${ figure.id }&x=${ x }&y=${ y }">&nbsp;</a>
			</div>
		</c:forEach>
	</c:forEach>
	</div>
	
	<p>
	<c:forEach items="${ figure.blocs }" var="item">
		(${ item.x }, ${ item.y }) 
	</c:forEach>
	</p>
	
	<form:form method="post" class="row center" modelAttribute="figure">
		<input type="hidden" name="idFigure" value="${ figure.id }" />
		<input type="hidden" name="idTetrimino" value="${ tetrimino.id }" />
		<form:input type="number" path="ordreRotation" id="ordreRotation" name="ordreRotation" min="0" />
		<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
			Modifier <i class="material-icons right">send</i>
		</button>
		<br><span class="erreur" style="color: red;">${ erreur }</span><br>
		<span class="erreur" style="color: red;"><form:errors path="ordreRotation" /></span>
	</form:form>
	
</div>