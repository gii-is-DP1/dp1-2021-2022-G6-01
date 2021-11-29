<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="parchisGames">

    <h2>Player Information</h2>


    <table class="table table-striped">

        
        <tr>
            <th>Name</th>
            <td><c:out value="${parchisGame.name}"/></td>
        </tr>
        <tr>
            <th>Tablero</th>
            <td><img class="card-img-top" src="/resources/images/tablero_parchis.png"
							alt="Dubai"></td>
        </tr>
         <tr>
         <th>DICE 1</th>
            <td><c:out value="${parchisTurn.dice1}"/></td>
          </tr>
          <tr>
          <th>DICE 2</th>
     		<td><c:out value="${parchisTurn.dice2}"/></td>
          </tr>
          
                 <tr>
            <th>TURN</th>
            <td><c:out value="${parchisTurn.turn}"/></td>
        </tr>
        <tr>
         <th>PLAYER</th>
            <td><c:out value="${parchisTurn.player.user.username}"/></td>
     
          </tr>

          
          
    </table>
    <div class="col-md-3">
           <c:choose>
                    <c:when test="${player.id==parchisTurn.player.id}">
                        <spring:url value="/parchisTurn/{gameId}/{playerId}" var="joinUrl">
                        <spring:param name="gameId" value="${parchisGame.id}"/>
                          <spring:param name="playerId" value="${player.id}"/>
                    </spring:url>
				<div class="control-group">
				</div>
				<a class="btn btn-primary" href="${fn:escapeXml(joinUrl)}">ROLL IT MOTHER FUCKER</a>
                    </c:when>
                    <c:otherwise>
                        <p> NO ES TU TURNO </p> 
                    </c:otherwise>
                </c:choose>
        </div>
      

</petclinic:layout>
