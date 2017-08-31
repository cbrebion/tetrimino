<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
	<form:form method="POST" class="row center" modelAttribute="tetrimino">
		<h5>Nom Tetrimino</h5>
		<form:input path="nom" type="text" name="nom" class="row center" />
		<h5>Couleur</h5>
		<form:input path="couleur" type="color" name="couleur" class="row center" />
		<h5>Coefficient</h5>
		<form:input path="coeff" type="number" min="0" step="0.01" name="coeff" class="row center" />
		
		<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
			Modifier <i class="material-icons right">send</i>
		</button>
		<br><span class="erreur" style="color: red;"><form:errors path="nom" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="couleur" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="coeff" /></span>
		
		<input type="hidden" name="idTetrimino" value="${ tetrimino.id }" />
	
	</form:form>
</div>