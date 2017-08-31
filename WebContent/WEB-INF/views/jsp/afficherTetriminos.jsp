<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<ul class="collection">
		<c:forEach items="${tetriminos}" var="item">
		<li class="collection-item avatar">
			<div class="figureMini">
			<c:forEach var="x" begin="0" end="3">
				<c:forEach var="y" begin="0" end="3">
					<c:set var="selectionne">
						<c:forEach items="${ item.figures[0].blocs }" var="bloc">
							<c:choose>
								<c:when test="${ bloc.x eq x and bloc.y eq y }">style="background-color: ${ item.couleur };"</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</c:forEach>
					</c:set>
					<div class="blocMini" ${ selectionne }>
					</div>
				</c:forEach>
			</c:forEach>
			</div>
			<input type="color" value="<c:out value="${item.couleur}" />" disabled />&nbsp;<span class="nomTetrimino"><c:out value="${item.nom}" />&nbsp;</span> <span class="nomTetrimino">coeff : <c:out value="${item.coeff}" /></span>
			<div style="float: right;">
				<a href="supprimerPiece?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Supprimer"><i class="material-icons">cancel</i></a>
				<a href="modifPiece?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Editer"><i class="material-icons">edit</i></a>
				<a href="ajoutFigure?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Ajouter figure"><i class="material-icons">add_circle</i></a>
				<a href="tetrimino?id=<c:out value="${ item.id }"/>" class="secondary-content posRelative" title="Afficher tetrimino"><i class="material-icons">visibility</i></a>
			</div>
			</li>
		</c:forEach>
	</ul>
	<p>
		<a class="waves-effect waves-light btn-large red darken-4" href="ajoutTetrimino"><i class="material-icons left">add</i>Ajouter Tetrimino</a>
	</p>
</div>