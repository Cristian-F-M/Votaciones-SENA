<%-- 
    Document   : Representante
    Created on : 19/09/2023, 10:57:41 a. m.
    Author     : pirul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="archivos/css/cssRepresentante.css">
        <link rel="stylesheet" href="archivos/css/css/bootstrap.css"/>
        <link rel="shortcut icon" href="archivos/images/logo-SENA.jpg" type="image/x-icon">
        <title>Representante</title>
    </head>
    <jsp:useBean class="modelos.Votacion" id="VotacionActual" scope="request"/>
    <body>
        <jsp:include page="jspf/menu.jsp"></jsp:include>
        <c:forEach items="${VotacionActual.ListarVotacionActual()}" var="votacionActual">
            <div class="main">
                <div class="foto-nombre">
                    <div class="foto">
                        <img src="${votacionActual.ganadorVotacion.fotoCandidato ? votacionActual.ganadorVotacion.fotoCandidato : "archivos/images/Triste!.jpg"}" alt="Foto de ${votacionActual.ganadorVotacion.aprendiz.nombreAprendiz ? votacionActual.ganadorVotacion.aprendiz.nombreAprendiz : "No hay candidato Seleccionado"}">
                    </div>
                    <div class="nombre">
                        <h2>${votacionActual.ganadorVotacion.aprendiz.nombreAprendiz ? votacionActual.ganadorVotacion.aprendiz.nombreAprendiz : "No hay representante elegido"}</h2>
                    </div>
                </div>
                <div class="contenedor-informacion-candidato">
                    <div class="card-informacion-candidato">
                        <div class="descripcion">${votacionActual.ganadorVotacion.descripcionCandidato ? votacionActual.ganadorVotacion.descripcionCandidato : "No hay representante elegido"}</div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <jsp:include page="jspf/footer.jsp"></jsp:include>
        <script src="archivos/js/js/bootstrap.js"></script>

    </body>

</html>
