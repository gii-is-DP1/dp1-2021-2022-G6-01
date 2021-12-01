<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="ParchisDices">
   
    <jsp:body>
        <h2>ParchiGames</h2>
 	<h2>
        Move Selection
    </h2>
        <form class="form-horizontal" action="/parchisGames/save">
            <div class="form-group has-feedback">        
                <petclinic:selectField name="pieces" label="Pieces " names="${pieces}"
			size="4" />
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Save Game</button>
                    
                </div>
            </div>
            
        </form>

        </jsp:body>


</petclinic:layout>