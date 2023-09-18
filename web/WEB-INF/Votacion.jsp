<%-- 
    Document   : Candidato
    Created on : 17/09/2023, 11:57:34 a. m.
    Author     : pirul
--%>

<%
    String mensaje = request.getParameter("mensaje");
    System.out.println(mensaje);
   
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="archivos/css/cssVotacion.css">
        <link rel="stylesheet" href="archivos/css/css/bootstrap.css">
        <link rel="shortcut icon" href="archivos/images/logo-SENA.jpg" type="image/x-icon">
        <title>Votaciones</title>
    </head>

    <jsp:useBean id="Candidato" class="modelos.Candidato" scope="request"/>

    <body onload="PopUp()">

        <!-- Header -->
        <jsp:include page="jspf/menu.jsp"></jsp:include>

            <!-- header -->

            <!-- main -->
            <main class="main">
                <h1>Votacion para el representante del sena</h1>
                <div class="informacion alert alert-warning">
                    <Strong>Para las votaciones</Strong>
                    Tenga en cuenta que solo podra vota una vez y que despues de
                    finalizada la votación ya no prodra votar.
                </div>

                <div class="fecha">
                    <Strong>Tiempo restante</Strong>
                    <p id="cuenta-regresiva"></p>
                </div>
                <form action="ControladorAprendiz" method="post">

                    <div class="candidatos">
                    <%--<c:choose>--%>
                    <%--<c:when>--%>
                    <c:forEach items="${Candidato.Listar(0)}" var="candidato">
                        <div class="card-candidato">
                            <a href="#">
                                <span>Saber más</span>
                                <div class="foto">
                                    <img src="${candidato.fotoCandidato}" alt="Foto de ${candidato.aprendiz.nombreAprendiz}"/>
                                </div>
                                <div class="nombre">
                                    <h2>${candidato.aprendiz.nombreAprendiz}</h2>
                                </div>
                            </a>
                            <div class="inputRadio">
                                <input id="${candidato.idCandidato}" type="radio" name="fCandidato" value="${candidato.idCandidato}">
                                <label for="${candidato.idCandidato}">Seleccionar</label>
                            </div>
                        </div>
                    </c:forEach>  
                    <%--</c:when>--%>
                    <%--<c:otherwise></c:otherwise>--%>
                    <%--</c:choose>--%>
                </div>
                <p class="pMensaje"></p>
                <button type="button" name="CRUD" value="Enviar">Enviar</button>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">¿</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <c:if test="${param.mensaje != null}">
                                    <p class="mensaje">${param.mensaje}</p>
                                </c:if>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-primary" name="CRUD" value="Votar">Confirmar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </main>
        <button style="display: none;" id="BTNPopUp" type="button" class="btn btn-primary" data-bs-toggle="modal"
                data-bs-target="#exampleModal">
        </button>

        <!-- main -->
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasRightLabel">Usuario</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul>
                    <li><a href="ControladorMenu?opcion=EditarPefil">Editar perfil</a></li>
                    <li><a href="ControladorMenu?opcion=IniciarSesion">Iniciar Sesion</a></li>
                    <li><a href="ControladorMenu?opcion=Registrar">Registrarse</a></li>
                    <li><a href="ControladorMenu?opcion=CerrarSesion">Cerrar sesión</a></li>
                </ul>
            </div>
        </div>
        <!-- main -->

        <jsp:include page="jspf/footer.jsp"></jsp:include>
            lsñalf{sl
            <script>
                function PopUp() {
                    var mensaje = '<%= request.getParameter("mensaje") %>';
//                    console.log(mensaje);
                    if (mensaje !== "null") {
                        var BTNPopUp = document.getElementById("BTNPopUp");
                        document.getElementById("exampleModalLabel").innerHTML = "Mensaje";
                        BTNPopUp.click();

                        if (mensaje === "Debes iniciar sesión para votar") {
                            document.querySelector("button[value=Votar]").style.display = "none";
                        } else if (mensaje === "Voto registrado") {
                            document.querySelector("button[value=Votar]").style.display = "none";
                        }
                    }
                }


//                var BTNVotar = document.querySelector("button[value=Votar]");
//
//                BTNVotar.addEventListener('submit', () => {
//
//                    var xhr = new XMLHttpRequest();
//                    xhr.onreadystatechange = function () {
//                        if (xhr.readyState === 4 && xhr.status === 200) {
//                            if (xhr.responseText === "true") {
//                                document.getElementById("exampleModalLabel").innerHTML = "Mensaje";
//                                document.querySelector(".modal-body").innerHTML = "Ya tu has registrado tu voto";
////                                `;
//                                BTNModal.click();
//                            }
//                        }
//
//                        xhr.open("POST", "ControladorAprendiz", true);
//                            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//                            var datos = "CRUD=votoValido";
//                            xhr.send(datos);
//                    };
//                });
        </script>
        <script src="archivos/js/jsVotaciones.js"></script>
        <script src="archivos/js/js/bootstrap.js"></script>
    </body>
</html>
