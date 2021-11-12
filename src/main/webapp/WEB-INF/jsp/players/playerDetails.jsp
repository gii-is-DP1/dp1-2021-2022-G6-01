<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

    <h2>Player Information</h2>


    <table class="table table-striped">
    <tr>
            <th>Username</th>
            <td><c:out value="${player.user.username}"/></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><b><c:out value="${player.firstName} ${player.lastName}"/></b></td>
        </tr>
        
        <tr>
            <th>Points</th>
            <td><c:out value="${player.points}"/></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${player.description}"/></td>
        </tr>
    </table>

    <spring:url value="{playerId}/edit" var="editUrl">
        <spring:param name="playerId" value="${player.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Player</a>



</petclinic:layout>
