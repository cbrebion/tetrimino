<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
	<form:form method="POST" class="row center" modelAttribute="tetrimino">
		<div class="input-field">
			<form:label path="nom">Nom</form:label>
			<form:input path="nom" type="text" name="nom" />
		</div>
		<div>
			<form:label path="couleur">Couleur</form:label>
			<form:input path="couleur" type="color" name="couleur" />
		</div>
		<div class="input-field">
			<form:label path="coeff">Coefficient</form:label>
			<form:input path="coeff" type="number" min="0" step="0.01" name="coeff" />
		</div>
		
		<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
			Modifier <i class="material-icons right">send</i>
		</button>
		<br><span class="erreur" style="color: red;"><form:errors path="nom" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="couleur" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="coeff" /></span>
		
		<input type="hidden" name="idTetrimino" value="${ tetrimino.id }" />
	
	</form:form>
</div>