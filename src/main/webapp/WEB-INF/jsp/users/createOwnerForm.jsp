<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="owners">

	<div class="callout callout-primary">
		<h4>
			<a href="/login" id="">Si ya tienes cuenta, pulsa aquí para iniciar sesión.</a>
		</h4>
		¡Una vez hayas iniciado sesión podrás jugar con tus amigos creando
		partidas o uniéndote a ellas!
	</div>


	<h2>
		<c:if test="${owner['new']}">Nuevo </c:if>
		usuario:
	</h2>
	<form:form modelAttribute="owner" class="form-horizontal"
		id="add-owner-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="First Name" name="firstName" />
			<petclinic:inputField label="Last Name" name="lastName" />
			<petclinic:inputField label="Address" name="address" />
			<petclinic:inputField label="City" name="city" />
			<petclinic:inputField label="Telephone" name="telephone" />
			<petclinic:inputField label="Username" name="user.username" />
			<petclinic:inputField label="Password" name="user.password" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${owner['new']}">
						<button class="btn btn-default" type="submit">Add Owner</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							Owner</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</petclinic:layout>
