<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="ocaGames">

    <h2>Player Information</h2>

            <petclinic:ocaBoard ocaBoard="${ocaBoard}"/>
           	<petclinic:ocaPiece size="80" piece="${ocaPiece}"/>
            


    <table class="table table-striped">

        
        <tr>
            <th>Points</th>
            <td><c:out value="${ocaGame.name}"/></td>
        </tr>
         <tr>
         <th>DICE</th>
            <td><c:out value="${ocaTurn.dice}"/></td>
     
          </tr>
                 <tr>
            <th>TURN</th>
            <td><c:out value="${ocaTurn.turn}"/></td>
        </tr>
        <tr>
         <th>PLAYER</th>
            <td><c:out value="${ocaTurn.player.user.username}"/></td>
     
          </tr>
           <tr>
         <th>Your Position</th>
            <td><c:out value="${ocaPiece.position}"/></td>
          </tr>
          
          
    </table>
    <div class="col-md-3">
           <c:choose>
                    <c:when test="${player.id==ocaTurn.player.id}">
                        <spring:url value="/ocaTurn/{gameId}/{playerId}" var="joinUrl">
                        <spring:param name="gameId" value="${ocaGame.id}"/>
                          <spring:param name="playerId" value="${player.id}"/>
                    </spring:url>
                    <a class="btn btn-primary" href="${fn:escapeXml(joinUrl)}">ROLL IT MOTHER FUCKER</a>
                    </c:when>
                    <c:otherwise>
                        <p> NO ES TU TURNO </p> 
                    </c:otherwise>
                </c:choose>
        </div>
      

</petclinic:layout>
