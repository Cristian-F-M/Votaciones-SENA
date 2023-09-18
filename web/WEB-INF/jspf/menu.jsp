<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link rel="stylesheet" href="archivos/css/cssMenu.css"/>
    <link rel="stylesheet" href="archivos/icons/icons/bootstrap-icons.css"/>
</head>
<header>
    <div class="img"><img src="archivos/images/logo-SENA.jpg" alt="Logo del sena"></div> 
    <!--<div class="img"><a href="a.html"><img src="logo-SENA.png" alt="Logo del sena"></a></div>-->
    <ul>
        <li><a href="ControladorMenu?opcion=Home">Inicio</a></li>
        <li><a href="ControladorMenu?opcion=Representante">Representante</a></li>
        <li><a href="ControladorMenu?opcion=Votacion">Votaciones</a></li>
        <li><a href="ControladorMenu?opcion=Nosotros">Nosotros</a></li>
        <li><a href="ControladorMenu?opcion=Contacto">Contacto</a></li>
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
                <c:when test="${sessionScope.idAprendiz != null}">
                    ${sessionScope.nombreAprendiz}
                </c:when>
                <c:otherwise>
                    Aprendiz
                </c:otherwise>
            </c:choose>


        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <ul>
            <c:choose>
                <c:when test="${sessionScope.idAprendiz != null}">
                    <li><a href="ControladorMenu?opcion=EditarPefil">Editar perfil</a></li>
                    <li><a href="ControladorMenu?opcion=CerrarSesion">Cerrar sesión</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="ControladorMenu?opcion=IniciarSesion">Iniciar Sesion</a></li>
                    <li><a href="ControladorMenu?opcion=Registrar">Registrarse</a></li>
                    </c:otherwise>
                </c:choose>

        </ul>
    </div>
</div>
<script>
    var title = document.title;
    console.log(title);

    switch (title) {
        case "SENA-Votaciones":
            var opcionInicio = document.querySelector('a[href="ControladorMenu?opcion=Home"]');
            opcionInicio.style.borderBottom = "2px solid white";
            break;

        case "Contacto":
            var header = document.querySelector("header");
            var opcionInicio = document.querySelector('a[href="ControladorMenu?opcion=Contacto"]');
            header.style.backgroundColor = "rgb(73 72 72)";
            opcionInicio.style.borderBottom = "2px solid white";
            break;
            
            
        case "Votaciones":
            var header = document.querySelector("header");
            var opcionInicio = document.querySelector('a[href="ControladorMenu?opcion=Votacion"]');
            opcionInicio.style.borderBottom = "2px solid white";
            break;

    }

</script>
<script src="archivos/js/js/bootstrap.js"></script>
</html>
<%-- 
    Document   : menu
    Created on : 20/08/2023, 2:01:51 p. m.
    Author     : pirul
--%>
