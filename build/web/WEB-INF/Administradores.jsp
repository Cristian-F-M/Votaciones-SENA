<%-- 
    Document   : Administradores
    Created on : 19/09/2023, 9:01:01 p. m.
    Author     : pirul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="archivos/css/css/bootstrap.css"/>
        <link rel="shortcut icon" href="archivos/images/logo-SENA.jpg" type="image/x-icon">
        <link rel="stylesheet" href="archivos/css/cssAdministradores.css"/>
        <title>Administradores</title>
    </head>
    <jsp:useBean class="modelos.Administrador" id="Administrador" scope="request"/>
    <body>
        <jsp:include page="jspf/menuAdministrador.jsp" />
        <main class="main">
            <div class="contenedor__h1">
                <h1>Gestion Administradores</h1>
            </div>

            <div class="contenedor__acciones">
                <div class="contenedor__buscar">
                    <label for="">Buscar</label>
                    <input type="text">
                </div>
                <div class="contenedor__boton__registrar">
                    <button id="Registrar">Registrar</button>
                </div>
            </div>

            <div class="contenedor__tabla">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Documento</th>
                            <th>Correo</th>
                            <th>Rol</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${Administrador.Listar(0)}" var="administrador">
                            <tr>
                                <td>${administrador.idAdministrador}</td>
                                <td>${administrador.nombreAdministrador}</td>
                                <td>${administrador.documentoAdministrador}</td>
                                <td>${administrador.correoAdministrador}</td>
                                <td>${administrador.rolAdministrador}</td>
                                <td>
                                    <a name="accionAdministrador" href="#">Editar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
        <jsp:include page="jspf/footer.jsp" />
        <jsp:include page="jspf/ventanas.jsp" />       
        <script src="archivos/js/jsAdministradores.js"></script>
    </body>
</html>
