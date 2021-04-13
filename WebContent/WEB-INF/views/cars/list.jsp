<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- <sec:authorize access="hasRole('admin')" var="isAdmin"/> -->
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
        <h5 class="text-center font-weight-bold mt-4 mb-4">Lista di tutti i veicoli</h5>
        <div class="font-weight-bold text-center">Numero veicoli: ${numCars}</div>
        <table class="table table-striped w-75 mx-auto">
        	<thead>
        		<tr>
        			<th>TARGA</th>
        			<th>MARCA</th>
        			<th>MODELLO</th>
        			<th>NUMERO TELAIO</th>
        			<th>MASSA</th>
        			<th>DESTINAZIONE D'USO</th>
		        	<th>NUMERO ASSI</th>
        			<th>ALIMENTAZIONE</th>
        			<th>ELIMINA</th>
        			<th>SHOW</th>
                    <th>EDIT</th>
        		</tr> 
        	</thead>
            <tbody>
            <c:forEach items="${cars}" var="car">
            	<tr>
            		<td>${car.targa}</td>
            		<td>${car.marca}</td>
            		<td>${car.modello}</td>
            		<td>${car.numeroTelaio}</td>
            		<td>${car.massa}</td>
            		<td>${car.destinazioneUso}</td>
            		<td>${car.numeroAssi}</td>
            		<td>${car.alimentazione}</td>
                    <td><a class="btn btn-danger"
                                       href="<c:url value="/car/delete/${car.targa}"/>"/
                                       title="Elimina &quot;${car.targa}&quot;"
                                       onclick='return confirm("Sei sicuro di voler eliminare il veicolo targato: \"${car.targa}\"?");'>
                                        <i class="fa fa-trash"></i></a></td>
                    <td><a class="btn btn-info" href="<c:url value="/car/${car.targa}"/>"><i class="fa fa-info"></i></a></td>
                    <td><a class="btn btn-info" href="<c:url value="/car/edit/${car.targa}"/>"><i class="fa fa-edit"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center mb-4">
            <a type="button" class="btn btn-success" href="${newCar_url}">Aggiungi un nuovo veicolo</a>
   	</div>
</div>