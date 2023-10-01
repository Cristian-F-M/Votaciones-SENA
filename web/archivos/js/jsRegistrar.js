/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var inputDocumento = document.getElementById("TipoDocumentoAprendiz").value;
var botinRegistrar = document.getElementById("Registar");


function documentoAprendiz() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Procesa la respuesta aquí
            document.getElementById("p").innerHTML = xhr.responseText;
        }
    };
    xhr.open("POST", "ControladorAprendiz", true); // Cambia "tupagina.jsp" por la URL de tu controlador o servlet
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // Establece el tipo de contenido
    var datos = "fDocumentoAprendiz=" + inputDocumento; // Define los datos que deseas enviar
    xhr.send(datos);
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



var inputPassword = document.getElementById("PasswordAprendiz");
var inputMostrar = document.getElementById("checkbox");


inputMostrar.addEventListener('change', () => {
    inputMostrar.checked ? inputPassword.type = "text" : inputPassword.type = "password";
});