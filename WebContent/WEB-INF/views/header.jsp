<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('admin')" var="isAdmin"/>
<sec:authorize access="hasRole('driver')" var="isDriver"/>
<sec:authorize access="hasRole('account')" var="isAccount"/>
<sec:authorize access="!isAuthenticated()" var="isNoAuth"/>
<sec:authorize access="isAuthenticated()" var="isAuth"/>


<!-- HOME PAGE -->
<c:url value="/cri" var="home_url"/>
<c:url value="/about" var="about_us_url"/>
<c:url value="/login" var="login_url"/>
<c:url value="/logout" var="logout_url"/>
<c:url value="/sign_up" var="sign_up_url"/>
<c:url value="/contacts" var="contacts_us_url"/>
<c:url value="/disclaimer" var="disclaimer_url"/>

<!-- DRIVER -->
<c:url value="/profile" var="profile_url"/>
<c:url value="/myprenotazioni" var="bookings_driver_url"/>

<!-- ADMIN -->
<c:url value="/profile" var="profile_url"/>
<c:url value="/users" var="users_admin_url"/>
<c:url value="/cars_admin" var="cars_admin_url"/>
<c:url value="/documentazioni_admin_show" var="docs_admin_url"/>
<c:url value="/manutenzioni_admin_show" var="man_admin_url"/>
<c:url value="/prenotazioni_admin_show" var="pre_admin_url"/>

<!-- ACCOUNT -->
<c:url value="/profile" var="profile_url"/>
<c:url value="/documentazioni" var="docs_account_url"/>
<c:url value="/cars" var="cars_account_url"/>
<c:url value="/manutenzioni" var="man_account_url"/>
<c:url value="/prenotazioni" var="pre_account_url"/>


<c:if test="${isNoAuth}">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="${home_url}">Advanced CODE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive1"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive1">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${home_url}">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${about_us_url}">Su di noi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contacts_us_url}">Contatti</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${disclaimer_url}">Disclaimer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${login_url}">Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <hr/>
</c:if>


<c:if test="${isDriver}">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="${home_url}">Advanced CODE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive2"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${home_url}">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${about_us_url}">Su di noi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contacts_us_url}">Contatti</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${disclaimer_url}">Disclaimer</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${bookings_driver_url}">Prenotazioni</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${profile_url}"><sec:authentication property="principal.username"/></a
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logout_url}">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <hr/>
</c:if>  
<c:if test="${isAdmin}">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="${home_url}">Advanced CODE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive3"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive3">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${home_url}">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${about_us_url}">Su di noi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contacts_us_url}">Contatti</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${disclaimer_url}">Disclaimer</a>
                    </li>
                    <li class="navbar-item">
                        <div class="btn-group">
                            <a role="button" href="" class="btn nav-link dropdown-toggle"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestisci</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="${man_admin_url}">Visualizza Manutenzioni</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${pre_admin_url}">Visualizza Prenotazioni</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${docs_admin_url}">Visualizza Documentazioni</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${cars_admin_url}">Gestisci Veicoli</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${users_admin_url}">Gestisci Utenti</a>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${profile_url}"><sec:authentication property="principal.username"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logout_url}">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <hr/>
</c:if>

<c:if test="${isAccount}">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="${home_url}">Advanced CODE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive3"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive3">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${home_url}">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${about_us_url}">Su di noi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contacts_us_url}">Contatti</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${disclaimer_url}">Disclaimer</a>
                    </li>
                    <li class="navbar-item">
                        <div class="btn-group">
                            <a role="button" href="" class="btn nav-link dropdown-toggle"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestisci</a>
                               <div class="dropdown-menu">
                                <a class="dropdown-item" href="${man_account_url}">Gestisci Manutenzioni</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${pre_account_url}">Gestisci Prenotazioni</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${docs_account_url}">Gestisci Documentazioni</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${cars_account_url}">Gestisci Veicoli</a>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${profile_url}"><sec:authentication property="principal.username"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logout_url}">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <hr/>
</c:if>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>