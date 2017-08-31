<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="container">
	<form:form method="post" modelAttribute="personne">
		<div class="input-field">
			<i class="material-icons prefix">account_circle</i>
			<form:label path="username"><spring:message code="home.username" /></form:label>
			<form:input type="text" name="username" path="username" /><br>
		</div>
		<div class="input-field">
			<i class="material-icons prefix">vpn_key</i>
			<form:label path="password"><spring:message code="home.password" /></form:label>
			<form:input type="password" path="password" />
		</div>

		<button class="btn waves-effect waves-light red darken-4" type="submit" name="action">
			<spring:message code="home.btnConnexion" /> <i class="material-icons right">send</i>
		</button>
		<a href="signin" class="waves-effect waves-light btn blue darken-4">
			<i class="material-icons right">edit</i><spring:message code="home.btnInscription" />
		</a>
		
		<br>
		<c:if test="${ !empty erreur }"><span class="erreur" style="color: red;"><spring:message code="${ erreur }" /></span><br></c:if>
		<span class="erreur" style="color: red;"><form:errors path="username" /></span><br>
		<span class="erreur" style="color: red;"><form:errors path="password" /></span>
	</form:form>
</div>