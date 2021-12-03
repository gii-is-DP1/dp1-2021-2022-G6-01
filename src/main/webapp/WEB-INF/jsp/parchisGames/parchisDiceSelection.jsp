<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<petclinic:layout pageName="ParchisDices">
   
    <jsp:body>
        <h2>ParchiGames</h2>
        <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><c:out value="${parchisGame.name}"/></td>
        </tr>
        <tr>
            <th>Tablero</th>
            <td><img class="card-img-top" src="/resources/images/tablero_cadiz.jpeg"
							alt="Dubai" height="1000" width="1000"></td>
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
           <th>PLAYER</th>
            <td><c:out value="${parchisTurn.player.user.username}"/></td>
     
          </tr>
                 <tr>
            <th>TURN</th>
            <td><c:out value="${parchisTurn.turn}"/></td>
        </tr>
          <tr>
            <th>FICHA 1</th>
            <td><c:out value="${piece1.square.position}"/></td>
        </tr>  <tr>
            <th>FICHA 2</th>
            <td><c:out value="${piece2.square.position}"/></td>
        </tr>  <tr>
            <th>FICHA 3</th>
            <td><c:out value="${piece3.square.position}"/></td>
        </tr>  <tr>
            <th>FICHA 4</th>
            <td><c:out value="${piece4.square.position}"/></td>
        </tr>
        <tr>
         <th>PLAYER</th>
            <td><c:out value="${parchisTurn.player.user.username}"/></td>
     
          </tr>

    </table>
 	<h2>
        Move Selection 
    </h2>
     <div class="col-md-3">
          
                   
                        <spring:url value="/parchisTurn/{parchisGameId}/{playerId}/{dice}/{pieceId}" var="ficha1">
                        <spring:param name="parchisGameId" value="${parchisTurn.id}"/>
                          <spring:param name="playerId" value="${player.id}"/>
                          <spring:param name="pieceId" value="${piece1.id}"/>
                          <spring:param name="dice" value="${dice}"/>
                    </spring:url>
                    <br>
                    <a class="btn btn-primary" href="${fn:escapeXml(ficha1)}">FICHA 1</a>
                   
                     
                     <spring:url value="/parchisTurn/{parchisGameId}/{playerId}/{dice}/{pieceId}" var="ficha2">
                        <spring:param name="parchisGameId" value="${parchisTurn.id}"/>
                          <spring:param name="playerId" value="${player.id}"/>
                          <spring:param name="pieceId" value="${piece2.id}"/>
                          <spring:param name="dice" value="${dice}"/>
                    </spring:url>
                    <br>
                    <a class="btn btn-primary" href="${fn:escapeXml(ficha2)}">FICHA 2</a>
                   
                    
                     
                     <spring:url value="/parchisTurn/{parchisGameId}/{playerId}/{dice}/{pieceId}" var="ficha3">
                        <spring:param name="parchisGameId" value="${parchisTurn.id}"/>
                          <spring:param name="playerId" value="${player.id}"/>
                          <spring:param name="pieceId" value="${piece3.id}"/>
                          <spring:param name="dice" value="${dice}"/>
                    </spring:url>
                    <br>
                    <a class="btn btn-primary" href="${fn:escapeXml(ficha3)}">FICHA 3</a>
                   
                    
                     <spring:url value="/parchisTurn/{parchisGameId}/{playerId}/{dice}/{pieceId}" var="ficha4">
                        <spring:param name="parchisGameId" value="${parchisTurn.id}"/>
                          <spring:param name="playerId" value="${player.id}"/>
                          <spring:param name="pieceId" value="${piece4.id}"/>
                          <spring:param name="dice" value="${dice}"/>
                    </spring:url>
                    <br>
                    <a class="btn btn-primary" href="${fn:escapeXml(ficha4)}">FICHA 4</a>
                    
 
        </div>
        </jsp:body>


</petclinic:layout>