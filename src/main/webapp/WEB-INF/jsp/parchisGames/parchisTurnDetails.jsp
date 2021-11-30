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
         <th>DICE 1</th>
            <td><c:out value="${parchisTurn.dice1}"/></td>
          </tr>
          <tr>
          <th>DICE 2</th>
     		<td><c:out value="${parchisTurn.dice2}"/></td>
          </tr>
          
          
          
    </table>
   
   <div class="control-group">
                    <petclinic:selectField name="pieces" label="Pieces " names="${pieces}"
                        size="4" />
                </div>
    
    <div class="col-md-3">
           <c:choose>
                    <c:when test="${player.id==parchisTurn.player.id}">
                        <spring:url value="/parchisGame/{gameId}" var="joinUrl">
                        <spring:param name="gameId" value="${parchisGame.id}"/>
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
