<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false"%>

<html>
<body>

<sec:authorize access="isAuthenticated()" var="isAuth"/>
<div class="container">
	<div class="row align-items-center my-5">
		<div class="col-lg-10">
		  <img class="img-fluid rounded mb-4 mb-lg-0" src="<c:url value="/immagini/background.jpg"/>" alt="">
		</div>
		<!-- /.col-lg-8 -->
		<div class="col-lg-5">
			<h1>Benvenuto in questo mondo</h1>
		</div>
	</div>
</div>

</body>
</html>