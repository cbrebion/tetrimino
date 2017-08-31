<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
	<form:form method="post" class="row center" modelAttribute="personne">
		<!-- <input type="hidden" name="action" value="signin" /> -->
		
		<div class="input-field">
			<i class="material-icons prefix">account_circle</i>
			<form:input path="username" id="username" type="text" class="validate" name="username" />
			<form:label path="username">Votre nom d'utilisateur</form:label>
		</div>
		
		<div class="input-field">
			<i class="material-icons prefix">account_circle</i>
			<form:input path="nom" id="nom" type="text" class="validate" name="nom" />
			<form:label path="nom">Votre nom</form:label>
		</div>
		
		<div class="input-field">
			<i class="material-icons prefix">account_circle</i>
			<form:input path="prenom" id="prenom" type="text" class="validate" name="prenom" />
			<form:label path="prenom">Votre prénom</form:label>
		</div>
		
		<div class="input-field">
			<i class="material-icons prefix">vpn_key</i>
			<form:input path="password" id="password" type="password" class="validate" name="password" />
			<form:label path="password">Votre mot de passe</form:label>
		</div>
		
		<button class="btn waves-effect waves-light red darken-4" type="submit">
			OK <i class="material-icons right">send</i>
		</button>
		<br><span class="erreur" style="color: red;"><form:errors path="username" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="nom" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="prenom" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="password" /></span>
	</form:form>
</div>