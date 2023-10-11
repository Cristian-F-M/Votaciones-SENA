<%-- 
    Document   : index
    Created on : 4/09/2023, 8:01:23 a. m.
    Author     : pirul
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="archivos/css/cssIndex.css">
        <link href="archivos/fonts/fonts/Lato.zip" rel="stylesheet">
        <link rel="stylesheet" href="archivos/icons/icons/bootstrap-icons.css"/>
        <link rel="shortcut icon" href="archivos/images/logo-SENA.jpg" type="image/x-icon">
        <link rel="stylesheet" href="archivos/css/css/bootstrap.css"/>
        <title>SENA-Votaciones</title>
    </head>

    <body>
        <div class="contenedor-fondo">
            <jsp:include page="WEB-INF/jspf/menu.jsp"></jsp:include>
                <div class="contenedor-titulo">
                    <h1>Elecciones representantes 2023</h1>
                    <p>Centro de gestion agroempresarial del oriente</p>
                </div>
            </div>
            <div class="contenedor-about">
                <div class="card contacto">
                    <h3>Información de contacto</h3>
                    <p><strong>Dirección: </strong>Calle Ficticia #123, ciudad Imaginaria</p>
                    <p><strong>Correo: </strong>info@gmail.com</p>
                    <p><strong>Teléfono: </strong>+574567890</p>
                    <div class="contenedor-redes">
                        <ul>
                            <li><a href="#"><i class="bi bi-twitter"></i></a></li>
                            <li><a href="https://www.youtube.com/@senavelez3845/featured"><i class="bi bi-youtube"></i></a></li>
                            <li><a href="#"><i class="bi bi-facebook"></i></a></li>
                            <li><a href="#"><i class="bi bi-instagram"></i></a></li>
                            <li><a href="#"><i class="bi bi-tiktok"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card contacto">
                    <h3>Contacto desarrollador</h3>
                    <p><strong>Dirección: </strong>Calle Ficticia #123, ciudad Imaginaria</p>
                    <p><strong>Correo: </strong><a target="_blank" href="mailto:cfmorales.diaz@gmail.com">cfmorales.diaz@gmail.com</a></p>
                    <p><strong>Teléfono: </strong><a target="_blank" href="https://wa.me/+573107954663?text=Hola buen dia, desearia solicitar un software">+573107954663</a></p>
                    <div class="contenedor-redes">
                        <ul>
                            <li><a target="_blank" href="https://twitter.com/CFMD10"><i class="bi bi-twitter"></i></a></li>
                            <li><a target="_blank" href="#"><i class="bi bi-youtube"></i></a></li>
                            <li><a target="_blank" href="#"><i class="bi bi-facebook"></i></a></li>
                            <li><a target="_blank" href="https://www.instagram.com/cristianmorales9826/"><i class="bi bi-instagram"></i></a></li>
                            <li><a target="_blank" href="#"><i class="bi bi-tiktok"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card img">
                    <img id="logo" src="archivos/images/logo-SENA.jpg" alt="Logo del sena">
                </div>
            </div>
        <jsp:include page="WEB-INF/jspf/footer.jsp"></jsp:include>
        <!--        <script src="archivos/js/jsRegistrar.js"></script>-->
        <script>

            var logo = document.getElementById("logo");
            logo.addEventListener('click', () => {
                window.location.href = "ControladorMenu?opcion=InicioAdministrador";
            });
        </script>
    </body>
</html>
