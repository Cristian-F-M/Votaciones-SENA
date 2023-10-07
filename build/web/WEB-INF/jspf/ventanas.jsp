<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="modelos.TipoDocumento" id="TipoDocumento" scope="request"/>
<jsp:useBean class="modelos.Rol" id="Rol" scope="request"/>
<!-- Ventana editar -->
<div class="contenedor__ventana">
    <div class="contenedor__pop-up">
        <div class="contenedor__cerrar">
            <button type="button" class="BTN-close">
                <i class="bi bi-x-lg"></i>
            </button>
        </div>
        <div class="contenedor__header">
            <!-- head -->
            <div class="contenedor__h1">
                <h2>Editar Administrador</h2>
            </div>
            <div class="contenedor__image">
                <img src="archivos/images/logo-SENA.jpg" alt="Logo del SENA">
            </div>
        </div>
        <div class="contenedor__body">
            <!-- body -->
            <div class="contenedor__form">
                <form action="ControladorAdministrador" method="post">
                    <input id="IdAdministrador" type="hidden" name="fIdAdministrador">
                    <label for="NombreAdministrador">Nombre</label>
                    <input id="NombreAdministrador" name="fNombreAdministrador" type="text">
                    <label for="TipoDocumentoAdministrador">Tipo Documento</label>
                    <select name="fTipoDocumentoAdministrador" id="TipoDocumentoAdministrador">
                        <c:forEach items="${TipoDocumento.Listar(0)}" var="tipoDocumento">
                            <option value="${tipoDocumento.idTipoDocumento}">${tipoDocumento.descripcionTipoDocumento}</option>
                        </c:forEach>
                    </select>
                    <label for="DocumentoAdministrador">Documento</label>
                    <input id="DocumentoAdministrador" name="fDocumentoAdministrador" type="text">
                    <label for="CorreoAdministrador">correo</label>
                    <input id="CorreoAdministrador" name="fCorreoAdministrador" type="email">
                    <label for="RolAdministrador">Rol</label>
                    <select name="fRolAdministrador" id="RolAdministrador">
                        <c:forEach items="${Rol.Listar(0)}" var="rol">
                            <option value="${rol.idRol}">${rol.descripcionRol}</option>
                        </c:forEach>
                    </select>
                    <div class="contenedor__botones">
                        <button type="button" value="Cancelar">Cancelar</button>
                        <button name="CRUD" value="Eliminar">Eliminar</button>
                        <button name="CRUD" value="Editar">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- fin de Ventana editar -->

<!-- Registrar -->
<div id="contenedor__ventana_registrar" class="contenedor__ventana">
    <div id="contenedor__pop_registrar" class="contenedor__pop-up">
        <div class="contenedor__cerrar">
            <button id="BTN-close" type="button" class="BTN-close">
                <i class="bi bi-x-lg"></i>
            </button>
        </div>
        <div class="contenedor__header">
            <!-- head -->
            <div class="contenedor__h1">
                <h2>Registrar Administrador</h2>
            </div>
            <div class="contenedor__image">
                <img src="archivos/images/logo-SENA.jpg" alt="Logo del SENA">
            </div>
        </div>
        <div class="contenedor__body">
            <!-- body -->
            <div class="contenedor__form">
                <form action="ControladorAdministrador" method="post">
                    <label for="NombreAdministradorR">Nombre</label>
                    <input required id="NombreAdministradorR" name="fNombreAdministrador" type="text">
                    <label for="TipoDocumentoAdministradorR">Tipo Documento</label>
                    <select name="fTipoDocumentoAdministrador" id="TipoDocumentoAdministradorR">
                        <c:forEach items="${TipoDocumento.Listar(0)}" var="tipoDocumento">
                            <option value="${tipoDocumento.idTipoDocumento}">${tipoDocumento.descripcionTipoDocumento}</option>
                        </c:forEach>
                    </select>
                    <label for="DocumentoAdministradorR">Documento</label>
                    <input required id="DocumentoAdministradorR" name="fDocumentoAdministrador" type="text">
                    <p id="mensaje" data-name="MensajeDocumento"></p>
                    <label for="CorreoAdministradorR">correo</label>
                    <input required id="CorreoAdministradorR" name="fCorreoAdministrador" type="email">
                    <p id="mensaje" data-name="MensajeCorreo"></p>
                    <label for="RolAdministradorR">Rol</label>
                    <select name="fRolAdministrador" id="RolAdministradorR">
                        <c:forEach items="${Rol.Listar(0)}" var="rol">
                            <option value="${rol.idRol}">${rol.descripcionRol}</option>
                        </c:forEach>
                    </select>
                    <div id="contenedor__botones" class="contenedor__botones">
                        <button id="CancelarRegistrar" value="Cancelar" type="button">Cancelar</button>
                        <button id="BTN__RegistrarA" name="CRUD" value="Registrar">Registrar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Fin de Ventana registrar -->

