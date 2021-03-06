<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                <th scope="col">Immagine Profilo</th>
                <th scope="col">Username</th>
                <th scope="col">Nome</th>
                <th scope="col">Cognome</th>
            </tr>
            <tr>
                <td>
                <c:if test="${not empty user.imageProfile}">
                    <img
                    src="<c:url value="/files/profile_pictures/${user.imageProfile}"/>"
                        alt="Immagine profilo" class="img-thumbnail mx-auto">
                </c:if>
                <c:if test="${empty user.imageProfile}">
                    <img
                        src="<c:url value="/files/profile_pictures/default.jpg"/>"
                        alt="Immagine profilo" class="img-thumbnail mx-auto">
                </c:if>
                </td>
                <td>${user.username}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
            </tr>
            </thead>
        </table>
    </div>
</div>
