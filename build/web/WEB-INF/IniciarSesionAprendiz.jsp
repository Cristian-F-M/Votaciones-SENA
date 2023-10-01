<%-- 
    Document   : IniciarSesion
    Created on : 24/08/2023, 8:01:51 p. m.
    Author     : pirul
--%>

<%
    String mensaje = request.getParameter("mensaje");
    System.out.println(mensaje);  
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="archivos/css/cssFormulario.css"/>
        <link rel="stylesheet" href="archivos/fonts/Crimson_Text,EB_Garamond.zip"/>
        <link rel="shortcut icon" href="archivos/images/logo-SENA.jpg" type="image/x-icon">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <title>Iniciar Sesion</title>
    </head>
    <jsp:useBean id="Rol" class="modelos.Rol" scope="request" />
    <jsp:useBean id="TipoDocumento" class="modelos.TipoDocumento" scope="request"/>
    <body>
        <div class="contenedor-formulario">
            <header>
                <h1>Iniciar Sesion</h1>
                <div>
                    <img src="archivos/images/logo-SENA.jpg" alt="Logo SENA">
                </div>
            </header>
            <form autocomplete="off" action="ControladorAprendiz" method="post"">
                <%if(mensaje != null){%>
                <p class="mensaje">${mensaje}</p>
                <%}%>
                <label>Tipo de documento</label><br>
                <select name="fTipoDocumentoAprendiz">
                    <c:forEach items="${TipoDocumento.Listar(0)}" var="tipoDocumento">
                        <option value="${tipoDocumento.idTipoDocumento}">${tipoDocumento.descripcionTipoDocumento}</option>
                    </c:forEach>
                </select><br>
                <label>Documento</label><br>
                <input required type="type" name="fDocumentoAprendiz"><br>
                <label>Contraseña</label><br>
                <input id="PasswordAprendiz" required type="password" name="fPasswordAprendiz">
                <div class="contenedor__mostrar__password">
                    <input id="checkbox" type="checkbox" name="name">
                    <label for="checkbox">Mostrar contraseña</label>
                </div>
                <button type="submit" value="IniciarSesion" name="CRUD">Iniciar sesion</button>
                <p class="sesion">No tiene una cuenta? <a href="ControladorMenu?opcion=Registrar">Registrese</a></p>
            </form>
        </div>
        <script src="archivos/js/iniciarSesion.js"></script>
    </body>
</html>
