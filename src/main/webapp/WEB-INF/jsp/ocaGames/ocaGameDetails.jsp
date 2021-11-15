<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="ocaGames">

    <h2>Player Information</h2>


    <table class="table table-striped">
    <tr>
            <th>Username</th>
            <td><c:out value="${ocaGame.reward}"/></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><b><c:out value="${ocaGame.players }"/></b></td>
        </tr>
        
        <tr>
            <th>Points</th>
            <td><c:out value="${ocaGame.name}"/></td>
        </tr>
        <tr>
            <th>Tablero</th>
            <td><img class="card-img-top" src="/resources/images/tablero_oca.jpg"
							alt="Dubai"></td>
        </tr>
    </table>
    <form:form action="${pageContext.request.contextPath}/myservlet" method="get">  
      <button type="submit" name="btn1" value="${ocaTurn.id}">Button 1</button>
</form:form>  

</petclinic:layout>
