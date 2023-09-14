<%
    String mensaje = request.getParameter("mensaje");
   
%>
<%-- 
    Document   : index
    Created on : 24/08/2023, 5:12:12 p. m.
    Author     : pirul
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registrar aprendiz</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="archivos/css/css/bootstrap.css"/>
        <link rel="stylesheet"  href="archivos/css/cssFormulario.css"/>
        <link rel="shortcut icon" href="archivos/images/logo-SENA.jpg" type="image/x-icon">
        <link rel="stylesheet" href="archivos/fonts/Crimson_Text,EB_Garamond.zip"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    </head>
    <jsp:useBean id="TipoDocumento" class="modelos.TipoDocumento" scope="request"/>
    <body onload="PopUp()">
        <div class="contenedor-formulario">
            <header>
                <h1>Registrar Aprendices</h1>
                <div>
                    <img src="archivos/images/logo-SENA.jpg" alt="Logo SENA">
                </div>
            </header>
            <form autocomplete="off" action="ControladorAprendiz" method="post">
                <label for="NombreAprendiz">Nombre</label><br>
                <input required name="fNombreAprendiz" placeholder="Nombre y apellido" id="NombreAprendiz" pattern="^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$" type="text" title="Solo texto"><br>
                <label for="TipoDocumentoAprendiz">Tipo de documento</label>
                <select name="fTipoDocumentoAprendiz" id="TipoDocumentoAprendiz">     
                    <c:forEach items="${TipoDocumento.Listar(0)}" var="tipoDocumento">
                        <option value="${tipoDocumento.idTipoDocumento}">${tipoDocumento.descripcionTipoDocumento}</option>
                    </c:forEach>
                </select><br>
                <label for="DocumentoAprendiz">Documento</label><br>
                <input required  onkeyup="verificarDocumentoAprendiz()" name="fDocumentoAprendiz" placeholder="Número de documento" id="DocumentoAprendiz" pattern="[0-9]{7,20}" type="text" title="Minimo 7 y maximo 15 digitos"><br>
                <p style="padding: 0; margin: 0; color: red;" id="pDocumento"></p>
                <label for="CorreoAprendiz">Correo</label><br>
                <input onkeyup="verificarCorreoAprendiz()"  required name="fCorreoAprendiz" placeholder="Correo electronico" id="CorreoAprendiz" type="email" title="correo"><br>
                <p style="padding: 0; margin: 0; color: red;" id="pCorreo"></p>
                <label for="PasswordAprendiz">Contraseña</label><br>
                <div class="contenedor-InputSpan">
                    <input required name="fPasswordAprendiz" pattern=".{8,16}" id="PasswordAprendiz" type="password" title="Entre 8 y 16 caracteres">
                    <span id="Visto"><i id="ojo" class="bi-eye-slash-fill"></i></span>
                </div>
                <button type="submit" name="CRUD" value="Registrar" id="Registar" >Registar</button>
                <p class="sesion">Ya tiene una cuenta? <a href="ControladorMenu?opcion=IniciarSesion">Iniciar sesión</a></p>
            </form>
        </div>
        <!-- Button trigger modal -->
        <button style="display: none;" id="BTNPopUp" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Launch demo modal
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <c:if test="${param.mensaje != null}">
                            <p class="mensaje">${param.mensaje}</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function PopUp() {

                var mensaje = '<%= request.getParameter("mensaje") %>';
                if (mensaje !== "null") {
//                    alert(mensaje);
                    var BTNPopUp = document.getElementById("BTNPopUp");
                    BTNPopUp.click();
                }
            }

            var pDocumento = document.getElementById("pDocumento");
            var pCorreo = document.getElementById("pCorreo");
            var botonRegistrar = document.getElementById("Registar");
            
            function verificarDocumentoAprendiz() {
                var inputDocumento = document.getElementById("DocumentoAprendiz").value;
                if (inputDocumento.length >= 7) {
                    var xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            if (xhr.responseText === "true") {
                                botonRegistrar.disabled = true;
                                pDocumento.innerHTML = "N° documento no disponible";
                            } else {
                                botonRegistrar.disabled = false;
                                pDocumento.innerHTML = "";
                            }
                        }
                    };
                    xhr.open("POST", "ControladorAprendiz", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    var datos = "fDocumentoAprendiz=" + inputDocumento + "&CRUD=documentoValido";
                    xhr.send(datos);
                } else {
                    if (inputDocumento.length <= 6) {
                        pDocumento.innerHTML = "";
                    }
                }
            }
            
//            var p
            function verificarCorreoAprendiz() {
                var inputCorreo = document.getElementById("CorreoAprendiz");
                if (inputCorreo.checkValidity()) {
                    var xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            if (xhr.responseText === "true") {
                                botonRegistrar.disabled = true;
                                pCorreo.innerHTML = "correo no disponible";
                            } else {
                                botonRegistrar.disabled = false;
                                pCorreo.innerHTML = "";
                            }
                        }
                    };
                    xhr.open("POST", "ControladorAprendiz", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    var datos = "fCorreoAprendiz=" + inputCorreo.value + "&CRUD=correoValido";
                    xhr.send(datos);
                } else {
                        pCorreo.innerHTML = "";
                }
            }
            
            
        </script>
        <script src="archivos/js/main.js"></script>
        <!--<script src="archivos/js/jsRegistrar.js"></script>-->
        <script src="archivos/js/js/bootstrap.js"></script>
    </body>
</html>
