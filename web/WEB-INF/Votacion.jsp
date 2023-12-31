<%-- 
    Document   : Candidato
    Created on : 17/09/2023, 11:57:34 a. m.
    Author     : pirul
--%>


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
                            <a data="${candidato.idCandidato}" id="cardCandidatos">
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
                            <div class="modal-body" style="display: flex; align-items: center; flex-direction:column;">
                                <c:if test="${param.mensaje != null}">
                                    <p class="mensaje">${param.mensaje}</p>
                                </c:if>
                            </div>
                            <div class="modal-footer">
                                <button id="BTN-Modal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
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
        <jsp:include page="jspf/footer.jsp"></jsp:include>
            <script>
                function PopUp() {
                    var mensaje = '<%= request.getParameter("mensaje") %>';
                    if (mensaje !== "null") {
                        var BTNPopUp = document.getElementById("BTNPopUp");
                        document.getElementById("exampleModalLabel").innerHTML = "Mensaje";
                        BTNPopUp.click();

                        if (mensaje === "Debes iniciar sesión para votar") {
                            document.querySelector("button[value=Votar]").style.display = "none";
                        } else if (mensaje === "Voto registrado") {
                            document.querySelector("button[value=Votar]").style.display = "none";
                        } else {
//                            document.querySelector("button[value=Votar]").style.display = "block";
                        }
                    }
                }

                var idAprendizIniciado = 0;
                if ("${sessionScope.idAprendiz}" !== "") {
                    idAprendizIniciado = "${sessionScope.idAprendiz}";
                }
        </script>
        <script src="archivos/js/jsVotaciones.js"></script>
        <script src="archivos/js/js/bootstrap.js"></script>
    </body>
</html>
