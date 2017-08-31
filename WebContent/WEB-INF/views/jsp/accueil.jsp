<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
	<form:form method="post" class="row center" modelAttribute="personne">
		<h5><form:label path="username">Nom utilisateur</form:label></h5>
		<form:input type="text" name="username" path="username" class="row center" /><br>
		<h5><form:label path="password">Mot de passe</form:label></h5>
		<form:input type="password" path="password" class="row center" />

		<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
			Se connecter <i class="material-icons right">send</i>
		</button>
		<a href="signin" class="waves-effect waves-light btn blue darken-4"><i class="material-icons right">edit</i>S'inscrire</a>
		
		<br><span class="erreur" style="color: red;">${ erreur }</span><br>
		<span class="erreur" style="color: red;"><form:errors path="username" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="password" /></span>
	</form:form>
</div>