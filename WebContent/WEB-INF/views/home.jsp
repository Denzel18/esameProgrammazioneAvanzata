<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false"%>

<sec:authorize access="isAuthenticated()" var="isAuth"/>

<div class="container">
	<h1>Benvenuto in questo mondo</h1>
</div>
    <!-- /.row -->

</div>
