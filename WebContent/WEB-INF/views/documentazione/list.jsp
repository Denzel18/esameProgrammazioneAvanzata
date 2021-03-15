<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<sec:authorize access="isAuthenticated()" var="isAuth"/>
<script type="text/javascript">
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })
</script>
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
        <h5 class="text-center font-weight-bold mt-4 mb-4">Lista di tutti i documenti</h5>
        <div class="font-weight-bold text-center">Numero documenti: ${numDocumenti}</div>
        <table class="table table-striped w-75 mx-auto">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Titolo</th>
                <th scope="col">Descrizione</th>
                <th scope="col">Utente</th>
                <th scope="col">Data Scadenza</th>
                <th scope="col">Costo</th>
                <th scope="col">Modifica Documento</th>
                <th scope="col">Gestisci Allegati</th>
                <th scope="col">Elimina</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${docs}" var="post">
                <tr>
                    <td>${doc.id}</td>
                    <td><a href="<c:url value="/documentazione/${doc.id}"/>"> ${doc.titolo}</a></td>
                    <td>${doc.descrizione }</td>
                    <td>${doc.getUtente().username }</td>
                    <td>${post.dataScadenza }</td>
                    <td>${post.costo }</td>
                    <td>
                        <div class="row">
                            <div class="col-lg">
                                <a class="btn btn-success"
                                   href="<c:url value="/documentazione/edit/${doc.id}"/>"
                                   title="Modifica &quot;${doc.titolo}&quot;">
                                    <i class="fa fa-pencil-square-o"></i>
                                </a>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="row">
                            <div class="col-lg">
                                <a class="btn btn-warning"
                                   href="<c:url value="/documentazione/${doc.id}/allegati"/>"
                                   title="Modifica allegati del documento">
                                    <i class="fa fa-copy"></i>
                                </a>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="row">
                            <div class="col-lg">
                                <a class="btn btn-danger"
                                   href="<c:url value="/documentazione/delete/${doc.id}"/>"
                                   title="Elimina &quot;${doc.titolo}&quot;"
                                   onclick='return confirm("Sei sicuro di voler eliminare il post \"${documento.titolo}\"?");'>
                                    <i class="fa fa-trash"></i>
                                </a>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
