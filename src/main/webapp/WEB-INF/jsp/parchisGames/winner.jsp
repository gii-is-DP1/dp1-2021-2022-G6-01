<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

    <h2>Enhorabuena Jugador eres el Ganador</h2>


    <table class="table table-striped">
    <tr>
            <th>Has Ganado estos puntos:</th>
            <td><c:out value="${parchisGame.reward}"/></td>
        </tr>
        <tr>
            <th>Tus Puntos Ahora son:</th>
            <td><b><c:out value="${player.points}"/></b></td>
        </tr>

    </table>
    <spring:url value="/parchisGames/delete/{parchisGameId}" var="deleteUrl">
        <spring:param name="parchisGameId" value="${parchisGame.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Volver al Inicio</a>



</petclinic:layout>
