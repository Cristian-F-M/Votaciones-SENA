<%-- 
    Document   : menu
    Created on : 28/09/2023, 4:39:14 p. m.
    Author     : pirul
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="archivos/css/cssMenu.css"/>
<link rel="stylesheet" href="archivos/icons/icons/bootstrap-icons.css"/>
<header>
    <div class="img">
        <img id="logo" src="archivos/images/logo-SENA.jpg" alt="Logo del sena">
    </div> 
    <ul>
        <li><a href="ControladorMenu?opcion=Home">Inicio</a></li>
        <li><a id="Representante" href="ControladorMenu?opcion=Representante">Representante</a></li>
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


        case "Representante":
            var header = document.querySelector("header");
            var opcionInicio = document.querySelector('a[href="ControladorMenu?opcion=Representante"]');
            opcionInicio.style.borderBottom = "2px solid white";
            break;


    }

//            Consulta para saber si hay representante y si no haga una alerta que diga que no se ha elegido   
    var aRepresentante = document.getElementById("Representante");
    aRepresentante.addEventListener('click', (evt) => {


        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                if (xhr.responseText === "false") {
                    evt.preventDefault();
                    alert("Aun no hay representante elegido");
                    return;
                } else if (xhr.responseText === "true") {
                    window.location.href = aRepresentante.href;
                }
            }
        };
        evt.preventDefault();
        xhr.open("POST", "ControladorVotacion", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var datos = "fAccion=Representante";
        xhr.send(datos);

        var logo = document.getElementById("logo");
        logo.addEventListener('click', () => {
            window.location.href = "ControladorMenu?opcion=InicioAdministrador";
        });

    });
</script>
<script src="archivos/js/js/bootstrap.js"></script>

