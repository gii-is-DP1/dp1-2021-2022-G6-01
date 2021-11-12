<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Games">
   
    <jsp:body>
        <h2>Game</h2>
 	<h2>
        <c:if test="${game['new']}">New </c:if> Game
    </h2>
        <form:form modelAttribute="game" class="form-horizontal" action="/games/save">
            <div class="form-group has-feedback">
         	  <petclinic:inputField label="Name" name="name"/>
                <petclinic:inputField label="Reward" name="reward"/>
                <petclinic:inputField label="Players(min 2,max 4)" name="players"/>
   
                <form:select path="gameMode">
              <form:option value="oca" label="oca"/>
              <form:option value="parchis" label="parchis"/>
      
            </form:select>
                
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${game.id}"/>
                    <button class="btn btn-default" type="submit">Save Game</button>
                    
                </div>
            </div>
            
        </form:form>

        </jsp:body>


</petclinic:layout>