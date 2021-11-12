<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>JUEGOS</h2>

<div class="wrap-table">
    <table id="gamesTable" class="table table-hover ">
        <thead>
        <tr class="row table-header">
            <th style="padding-left: 25px;">Nombre</th>
            <th>Recompensa</th>
            <th>En juego</th>
            <th>Partida por equipos</th>
             <th>Acciones</th>
           
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr class="row">
                <td style="padding-left: 25px;">
                   
                   <c:out value="${game.name}"/>
                </td>
                <td>
                    <c:out value="${game.reward}"/>
                </td>
                <td>
                    <c:out value="${game.inGame}"/>
                </td>
                <td>
                    <c:out value="${game.teamMatch}"/>
                </td>
                <td>
                 <spring:url value="/games/delete/{gameId}" var="gameUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(gameUrl)}">Delete</a>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
    <div class="col-md-2">
    <a href="/games/new" class="btn btn-primary" id="creacion">¡Crea una nueva partida!</a>
    
	</div>    
</petclinic:layout>
