<%-- 
    Document   : menuAdministrador
    Created on : 19/09/2023, 7:14:07 p. m.
    Author     : pirul
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="archivos/css/cssMenu.css"/>
    <link rel="stylesheet" href="archivos/icons/icons/bootstrap-icons.css"/>
</head>
<header>
    <div class="img"><img src="archivos/images/logo-SENA.jpg" alt="Logo del sena"></div> 
    <!--<div class="img"><a href="a.html"><img src="logo-SENA.png" alt="Logo del sena"></a></div>-->
    <ul>
        <li><a id="Inicio" href="ControladorMenuAdministrador?opcion=Inicio">Inicio</a></li> <!--El inicio es el mismo votacion-->
        <li><a id="Aprendices" href="ControladorMenuAdministrador?opcion=Aprendices">Aprendices</a></li>
        <li><a id="Candidatos" href="ControladorMenuAdministrador?opcion=Candidatos">Candidato</a></li>
        <li><a id="Historial" href="ControladorMenuAdministrador?opcion=Historial">Historial</a></li>
            <c:choose>
                <c:when test="${sessionScope.rolAdministrador == 1}">
                <li><a id="Administradores" href="ControladorMenuAdministrador?opcion=Administrador">Administradores</a></li>
                <li><a id="Otros" href="ControladorMenuAdministrador?opcion=Otros">Otros</a></li>
                </c:when>

        </c:choose>

        <!--Administradores no pueden ver administradores, roles, tipo documento-->
    </ul>
    <div class="contenedor-menu">
        <button class="btn-menu" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight"
                aria-controls="offcanvasRight">
            <i class="bi bi-list"></i>
        </button>
</header>

<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasRightLabel">
            <c:choose>
                <c:when test="${sessionScope.idAdministrador != null}">
                    ${sessionScope.nombreAdministrador} : ${sessionScope.rolAdministrador}
                </c:when>
                <c:otherwise>
                    Administrador
                </c:otherwise>
            </c:choose>


        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <ul>
            <c:choose>
                <c:when test="${sessionScope.idAdministrador != null}">
                    <li><a href="ControladorMenuAdministrador?opcion=EditarPefil">Editar perfil</a></li>
                    <li><a href="ControladorMenuAdministrador?opcion=CerrarSesion">Cerrar sesión</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="ControladorMenuAdministrador?opcion=IniciarSesion">Iniciar Sesion</a></li>
                    </c:otherwise>
                </c:choose>

        </ul>
    </div>
</div>
<script>
//    hacer que todos tenga un id en lugar de el query selector
    var title = document.title;
    console.log(title);
    var opcionSeleccionada;
    switch (title) {
        case "Votaciones":
            opcionSeleccionada = document.getElementById("Inicio");
            break;
        case "Aprendices":
            opcionSeleccionada = document.getElementById("Aprendices");
            break;
        case "Candidatos":
            opcionSeleccionada = document.getElementById("Candidatos");
            break;
        case "Auditoria":
            opcionSeleccionada = document.getElementById("Historial");
            break;
        case "Administradores":
            opcionSeleccionada = document.getElementById("Administradores");
            break;
        case "Otros":
            var opcionSeleccionada = document.getElementById("Otros");
            break;
    }
    opcionSeleccionada.style.borderBottom = "2px solid white";

</script>
<script src="archivos/js/js/bootstrap.js"></script>
</html>
