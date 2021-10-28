<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
  
    <jsp:body>
        <h2>Game</h2>

        

        <form:form modelAttribute="game" class="form-horizontal" action="/game/save">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Review" name="review"/>
                  <input type="checkbox" name="inGame" value="1"> In Game <br> 
                <input type="checkbox" name="teammatch" value=1> Team Match <br>
                 
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${game.id}"/>
                    <button class="btn btn-default" type="submit">Add Game</button>
                </div>
            </div>
        </form:form>

       
    </jsp:body>

</petclinic:layout>
