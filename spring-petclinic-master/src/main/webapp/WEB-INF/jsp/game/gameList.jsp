

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <h2>Games</h2>

    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Review</th>
          
            <th style="width: 120px">InGame</th>
            <th style="width: 120px">Actions</th>
           
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${game}" var="game">
            <tr>
                <td>
                    
                    <c:out value="${game.name} "/>
                </td>
                <td>
                    <c:out value="${game.review}"/>
                </td>
                <td>
                    <c:out value="${game.inGame}"/>
                </td>
                <td>
                    <c:out value="${game.teamMatch}"/>
                </td>
                 <td>
                 <spring:url value="/game/delete/{gameId}" var="gameUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(gameUrl)}"> Delete </a>
                </td>
               
                
      

                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
