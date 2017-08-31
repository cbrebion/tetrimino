<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Définition de la taille (temporaire tant qu'on n'a pas implémenté la sélection de la taille -->
<c:set var="tailleMatrice">
	<c:choose>
		<c:when test="${ empty taille }">3</c:when>
		<c:otherwise>${ taille }</c:otherwise>
	</c:choose>
</c:set>

<div class="container">
	<p><b>Nom du tetrimino : ${ tetrimino.nom }</b></p>
	<p>Couleur : <input type="color" value="<c:out value="${tetrimino.couleur}" />" disabled /></p>
	
	<p>
		<a class="waves-effect waves-light btn red darken-4" href="ajoutFigure?id=<c:out value="${ tetrimino.id }"/>"><i class="material-icons left">add</i>Ajouter une figure</a>
	</p>
	
	<!-- Affichage des figures correspondantes -->
	<div class="figures">
	<c:forEach items="${ tetrimino.figures }" var="figure">
		<p>
			<b>Figure ordre ${ figure.ordreRotation }</b>
			<a href="modifFigure?idTetrimino=<c:out value="${ tetrimino.id }"/>&idFigure=<c:out value="${ figure.id }"/>" title="Editer"><i class="tiny material-icons">edit</i></a>
			<a href="supprimerFigure?idTetrimino=<c:out value="${ tetrimino.id }"/>&idFigure=<c:out value="${ figure.id }"/>" title="Supprimer"><i class="tiny material-icons">cancel</i></a>
		</p>
		<div class="figure">
		<c:forEach var="x" begin="0" end="${ tailleMatrice }">
			<c:forEach var="y" begin="0" end="${ tailleMatrice }">
			
				<c:set var="selectionne">
					<c:forEach items="${ figure.blocs }" var="bloc">
						<c:choose>
							<c:when test="${ bloc.x eq x and bloc.y eq y }">background-color: ${ tetrimino.couleur };</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</c:forEach>
				</c:set>
				
				<div class="bloc" style="${ selectionne }">
				</div>
			</c:forEach>
		</c:forEach>
		<a href="ordonnerFigure?idFigure=<c:out value="${ figure.id }"/>&sens=0" title="Remonter"><i class="material-icons small">keyboard_arrow_up</i></a>
		<a href="ordonnerFigure?idFigure=<c:out value="${ figure.id }"/>&sens=1" title="Descendre"><i class="material-icons small">keyboard_arrow_down</i></a>
		</div>
		
		<br>
	</c:forEach>
	</div>
</div>