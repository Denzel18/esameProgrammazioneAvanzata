<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/documentazione/edit/${documento_id}/allegati/link/new" var="new_link_url"/>
<c:url value="/documentazione/edit/${documento_id}/allegati/file/new" var="new_file_url"/>

<div class="col">
    <c:if test="${empty filesDocumentazione and empty linksDocumentazione}">
        <div class="text-center">
            <h3 class="font-weight-bold mx-auto">Il Documento non contiene alcun allegato!</h3>
        </div>
    </c:if>
    <c:if test="${not empty filesDocumentazione}">
        <div class="text-center">
            <h3 class="mb-3 font-weight-bold">Allegati di tipo file del documento</h3>
        </div>
        <table class="table table-striped w-75 mx-auto">
            <thead>
            <tr>
                <th scope="col">File</th>
                <th scope="col">Modifica</th>
                <th scope="col">Elimina</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${filesDocumentazione}" var="file">
                <tr>
                    <td><i class="fa fa-file">
                        <a href="<c:url value="/files/documentazione_allegati/${file.name}"/>"
                           title="Scarica ${file.descrizione}" download>${file.descrizione}</a>
                    </i></td>
                    <td><a class="btn btn-success"
                           href="<c:url value="/documentazione/edit/${documento_id}/allegati/file/${file.id}/edit"/>"
                           title="Modifica &quot;${file.descrizione}&quot;">
                        <i class="fa fa-pencil-square-o"></i>
                    </a></td>
                    <td><a class="btn btn-danger"
                           href="<c:url value="/allegato/${file.id}/delete"/>"
                           title="Elimina &quot;${file.descrizione}&quot;"
                           onclick='return confirm("Sei sicuro di voler eliminare \"${file.descrizione}\"?");'>
                        <i class="fa fa-trash"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${not empty linksDocumentazione}">
        <div class="text-center">
            <h3 class="mt-5 mb-3 font-weight-bold">Allegati di tipo link del documento</h3>
        </div>
        <table class="table table-striped w-75 mx-auto">
            <thead>
            <tr>
                <th scope="col">Link</th>
                <th scope="col">Modifica</th>
                <th scope="col">Elimina</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${linksDocumentazione}" var="link">
                <tr>
                    <td><i class="fa fa-link"><a href="${link.link}"
                                                 title="Vai a ${link.descrizione}"
                                                 target="_blank">${link.descrizione}</a></i></td>
                    <td><a class="btn btn-success"
                           href="<c:url value="/documentazione/edit/${documento_id}/allegati/link/${link.id}/edit"/>"
                           title="Modifica &quot;${link.descrizione}&quot;">
                        <i class="fa fa-pencil-square-o"></i>
                    </a></td>
                    <td><a class="btn btn-danger"
                           href="<c:url value="/allegato/${link.id}/delete"/>"
                           title="Elimina &quot;${link.descrizione}&quot;"
                           onclick='return confirm("Sei sicuro di voler eliminare \"${link.descrizione}\"?");'>
                        <i class="fa fa-trash"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="text-center mb-4">
        <h4 class="mt-5 mb-3 font-weight-bold">Aggiungi allegati</h4>
        <a class="btn btn-success mr-3" href="${new_link_url}" role="button">Aggiungi un nuovo link</a>
        <a class="btn btn-success ml-3" href="${new_file_url}" role="button">Aggiungi un nuovo file</a>
    </div>

</div>
