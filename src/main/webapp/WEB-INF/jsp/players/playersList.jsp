<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    <h2>Players</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Username</th>
            <th style="width: 120px">Description</th>
            <th style="width: 120px">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${players}" var="player">
            <tr>
                <td>
                <c:out value="${player.firstName} ${player.lastName}"/>
                </td>
                <td>
                    <c:out value="${player.user.username}"/>
                </td>
                <td>
                    <c:out value="${player.description}"/>
                </td>
                <td>
                 <spring:url value="/players/{playerId}" var="playerUrl">
                        <spring:param name="playerId" value="${player.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(playerUrl)}">Details</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
