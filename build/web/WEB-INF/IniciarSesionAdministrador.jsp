<%-- 
    Document   : IniciarSesionAdministrador
    Created on : 19/09/2023, 6:10:57 p. m.
    Author     : pirul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="archivos/css/cssFormulario.css"/>
        <link rel="stylesheet" href="archivos/css/css/bootstrap.css"/>
        <link rel="shortcut icon" href="archivos/images/logo-SENA.jpg" type="image/x-icon">
        <title>Iniciar sesion administrador</title>
    </head>
    <jsp:useBean class="modelos.Rol" id="Rol" scope="request"></jsp:useBean>
    <jsp:useBean class="modelos.TipoDocumento" id="TipoDocumento" scope="request"/>
    <body style="background-color: gray;">
        <div class="contenedor-formulario">
            <header>
                <h1>Inicio Administrades</h1>
                <div>
                    <img src="archivos/images/logo-SENA.jpg" alt="Logo del sena"/>
                </div>
            </header>

            <form autocomplete="off" action="ControladorAdministrador" method="post">
                <label for="TipoDocumento">Tipo Documento</label>
                <select id="TipoDocumento" name="fTipoDocumentoAdministrador">
                    <c:forEach items="${TipoDocumento.Listar(0)}" var="tipoDocumento">
                        <option value="${tipoDocumento.idTipoDocumento}">${tipoDocumento.descripcionTipoDocumento}</option>
                    </c:forEach>
                </select>
                <label for="Documento">Documento</label>
                <input id="Documento" type="text" name="fDocumentoAdministrador">
                <label for="Password">Contraseña</label>
                <input id="Password" type="password" name="fPasswordAdministrador">
                <button type="submit" value="IniciarSesion" name="CRUD">Iniciar sesion</button>
            </form>
        </div>
    </body>
</html>
