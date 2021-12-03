<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<petclinic:layout pageName="ParchisDices">
   
    <jsp:body>
        <h2>ParchiGames</h2>
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