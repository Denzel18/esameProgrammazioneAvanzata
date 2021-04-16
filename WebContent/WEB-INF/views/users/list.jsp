<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/user/new" var="newUser_url"/>
<div class="col-md-12 mb-4">
    <div class="row">
        <c:if test="${fn:length(successMessage) > 0}">
            <div class="alert alert-success mx-auto" role="alert">${successMessage}
                <button type="button" class="close ml-3" data-dismiss="alert" aria-label="Close" style="font-size: 1.4rem;">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <c:if test="${fn:length(errorMessage) > 0}">
            <div class="alert alert-danger mx-auto" role="alert">${errorMessage}
                <button type="button" class="close ml-3" data-dismiss="alert" aria-label="Close" style="font-size: 1.4rem;">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
    </div>
    <div class="overflow-auto">
    	<div class="font-weight-bold text-center">Numero utenti: ${numUsers}</div>
  
        <h5 class="text-center font-weight-bold mt-4 mb-4">Lista di tutti gli utenti</h5>
        
        <table class="table table-striped w-75 mx-auto">
            <thead>
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Nome</th>
                <th scope="col">Cognome</th>
                <th scope="col">Ruolo</th>
                <th scope="col">Show</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.ruolo}</td>
                    <td><a class="btn btn-info" href="<c:url value="/user/${user.username}"/>"><i class="fa fa-info"></i></a></td>
                    <td><a class="btn btn-info" href="<c:url value="/user/edit/${user.username}"/>"><i class="fa fa-edit"></i></a></td>
                    <td><a class="btn btn-danger"
                        href="<c:url value="/user/delete/${user.username}"/>"/
                        title="Elimina &quot;${car.targa}&quot;"
                        onclick='return confirm("Sei sicuro di voler eliminare l'utente : \"${user.username}\"?");'>
                         <i class="fa fa-trash"></i></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center mb-4">
        <a type="button" class="btn btn-success" href="${newUser_url}">Aggiungi un nuovo utente</a>
    </div>
</div>
