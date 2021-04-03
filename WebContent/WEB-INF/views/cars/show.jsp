<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!--<sec:authorize access="hasRole('admin')" var="isAdmin"/>-->
<c:url value="/car/new" var="newCar_url"/>

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
        <h5 class="text-center font-weight-bold mt-4 mb-4">Veicolo ${car.targa}</h5>
        <table class="table table-striped w-75 mx-auto">
        	<thead>
        		<tr>
        			<th>TARGA</th>
        			<th>MARCA</th>
        			<th>MODELLO</th>
                </tr>
                <tr>
            		<td>${car.targa}</td>
            		<td>${car.marca}</td>
            		<td>${car.modello}</td>
                </tr>
                <tr>
        			<th>NUMERO TELAIO</th>
        			<th>MASSA</th>
        			<th>DESTINAZIONE D'USO</th>
                </tr>
                <tr>
                    <td>${car.numeroTelaio}</td>
            		<td>${car.massa}</td>
            		<td>${car.destinazioneUso}</td>
                </tr>  
                <tr>
		        	<th>NUMERO ASSI</th>
        			<th>ALIMENTAZIONE</th>
        			<th>ELIMINA</th>
        		</tr> 
                <tr>
            		<td>${car.numeroAssi}</td>
            		<td>${car.alimentazione}</td>
            		<td><a class="btn btn-danger"
                                       href="<c:url value="/car/delete/${car.targa}"/>"
                                       title="Elimina &quot;${car.targa}&quot;"
                                       onclick='return confirm("Sei sicuro di voler eliminare il veicolo targato: \"${car.targa}\"?");'>
                                        <i class="fa fa-trash"></i></a>
                    </td>
                </tr>
        	</thead>
        </table>
    </div>
</div>