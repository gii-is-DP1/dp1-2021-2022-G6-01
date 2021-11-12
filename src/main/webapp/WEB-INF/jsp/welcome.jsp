<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<petclinic:layout pageName="home">
	<sec:authorize access="!isAuthenticated()">
		<div class="callout callout-primary">
			<h4>
				<a href="users/new" id=""><img
					src="resources/images/Estrella_amarilla.png"> �Inicia sesi�n
					o reg�strate para jugar en Parch�s&Oca! <img
					src="resources/images/Estrella_amarilla.png"></a>
			</h4>
			�Una vez hayas iniciado sesi�n podr�s jugar con tus amigos creando
			partidas o uni�ndote a ellas!
		</div>
	</sec:authorize>
	<%--	<div>
		<div class="card-home">
			<img class="card-img-top" src="resources/images/tablero_parchis.png"
				alt="Tablero Parch�s">
			<div class="card-body">
				<h5 class="card-title">PARCH�S</h5>
				<p class="card-text">Aqu� podr�s elegir el tipo de partida que
					quieras de parch�s. Podr�s jugar con amigos o solo.</p>
				<a href="#" class="btn btn-primary">Juega una partida</a>
			</div>
		</div>
	</div>
 --%>
	<div class="container">
		<div class="row">
			<section class="col-md-7">
				<div class="card">

					<div class="inner">
						<a href="/games"><img class="card-img-top"
							src="resources/images/tablero_parchis.png" alt="Dubai"></a>
					</div>

					<div class="card-body">
						<h3 class="card-title">�Hola! Aqu� podr�s divertirte jugando
							al m�tico juego del Parch�s Online. Podr�s elegir si jugar contra
							tus amigos en una partida inidividual o en una partida por
							equipos en el que ganar� el equipo que primero meta todas su
							fichas.</h3>
						<a href="/games" class="btn btn-success">�JUEGA AHORA!</a>
					</div>
					</div>
			</section>
			<section class="col-md-1">
			</section>
			<section class="col-md-5">
				<div class="card">

					<div class="inner">
						<a href="/games"><img class="card-img-top" src="resources/images/tablero_oca.jpg"
							alt="Dubai"></a>
					</div>

					<div class="card-body">
						<h3 class="card-title">�Hola! Aqu� podr�s jugar a la Oca
							Online. Juega contra tus amigos creando una sala o uni�ndote a
							ella. Pueden jugar hasta 4 jugadores lanzando el dado al azar, y
							avanzando en las diferentes casillas, que tienen premios o
							castigos.</h3>
						<a href="/games" class="btn btn-success">�JUEGA AHORA!</a>
					</div>
				</div>
			</section>
		</div>
	</div>

	<%-- 				<div class="card" style="border-color: black;">

					<div class="inner">
						<img class="card-img-top"
							src="resources/images/tablero_parchis.png" alt="Dubai">
					</div>

					<div class="card-body">
						<h3 class="card-title">�Hola! Aqu� puedes jugar al
							tradicional juego del Parch�s. Podr�s elegir si jugar con amigos
							o solo.</h3>
						<a id="boton" href="">M�s informaci�n</a>
					</div>
				</div>
--%>

</petclinic:layout>
