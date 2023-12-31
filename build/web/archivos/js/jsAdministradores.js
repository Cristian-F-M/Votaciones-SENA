/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


var rows = document.querySelectorAll("table tbody tr");
var administradores = [];


function obtenerIdDeFila(row) {
    var idAdministrador = row.querySelector("td:first-child").textContent;
    console.log("ID: " + idAdministrador);
    informacionAdministrador(idAdministrador)
            .then((administrador) => {
//                LLenar las cosas de el editar
                console.log(administrador);
                var IdAdministrador = document.getElementById("IdAdministrador");
                var fNombreAdministrador = document.getElementById("NombreAdministrador");
                var fTipoDocumentoAdministrador = document.getElementById("TipoDocumentoAdministrador");
                var fDocumentoAdministrador = document.getElementById("DocumentoAdministrador");
                var fcorreoAdministrador = document.getElementById("CorreoAdministrador");
                var fRolAdministrador = document.getElementById("RolAdministrador");


                IdAdministrador.value = administrador.idAdministrador;
                fNombreAdministrador.value = administrador.nombreAdministrador;

                llenarSelectTipoDocumento(administrador.tipoDocumentoAdministrador);
                llenarSelectRol(administrador.rolAdministrador);

//                fTipoDocumentoAdministrador

                fDocumentoAdministrador.value = administrador.documentoAdministrador;
                fcorreoAdministrador.value = administrador.correoAdministrador;



                fNombreAdministrador.innerHTML = administrador.nombreAdministrador;
            });
}



function llenarSelectTipoDocumento(tipoDocumento) {
    var fTipoDocumentoAdministrador = document.getElementById("TipoDocumentoAdministrador");
    for (var i = 0; i < fTipoDocumentoAdministrador.options.length; i++) {

        var option = fTipoDocumentoAdministrador.options[i];

        if (option.value === tipoDocumento.idTipoDocumento.toString()) {
            option.selected = true;
            break;
        }
    }
}

function llenarSelectRol(rol) {
    var fRolAdministrador = document.getElementById("RolAdministrador");
    for (var i = 0; i < fRolAdministrador.options.length; i++) {

        var option = fRolAdministrador.options[i];

        if (option.value === rol.idRol.toString()) {
            option.selected = true;
            break;
        }
    }
}



var links = document.querySelectorAll("table tbody tr td:last-child a");

links.forEach(function (link) {
    link.addEventListener('click', function (event) {
        event.preventDefault();
        var fila = this.closest("tr");
        obtenerIdDeFila(fila);
    });
});


var accionAdministrador = document.querySelectorAll('a[name="accionAdministrador"]');
var contenedor__ventana = document.querySelector(".contenedor__ventana");
var contenedor__popUp = document.querySelector(".contenedor__pop-up");


accionAdministrador.forEach(function (link) {
    link.addEventListener('click', (evt) => {
        evt.preventDefault();
        // contenedor__ventana.style.display = "flex";
        contenedor__ventana.classList.add("mostrar");


        setTimeout(function () {
            contenedor__popUp.classList.add("mostrar");
        }, 100);
    });
});




var btn__cancelar = document.querySelector('button[value="Cancelar"]');
var btn__close = document.querySelector(".BTN-close");


btn__cancelar.addEventListener('click', cerraVentana);
btn__close.addEventListener('click', cerraVentana);

function cerraVentana() {
    contenedor__popUp.classList.remove("mostrar");
    setTimeout(function () {
        // contenedor__ventana.style.display = "none";
        contenedor__ventana.classList.remove("mostrar");
    }, 200);
}




var btnRegistrar = document.getElementById("Registrar");


var contenedor__ventana_registrar = document.getElementById("contenedor__ventana_registrar");
var contenedor__pop_registrar = document.getElementById("contenedor__pop_registrar");

btnRegistrar.addEventListener("click", () => {
    // evt.preventDefault();
    contenedor__ventana_registrar.classList.add("mostrar");
    setTimeout(function () {
        contenedor__pop_registrar.classList.add("mostrar");
    }, 100);
});

var CancelarRegistrar = document.getElementById("CancelarRegistrar");
var BTN_closeRegistrar = document.getElementById("BTN-close");

CancelarRegistrar.addEventListener('click', cerrarVentanaRegistrar);
BTN_closeRegistrar.addEventListener('click', cerrarVentanaRegistrar);

function cerrarVentanaRegistrar() {
    contenedor__pop_registrar.classList.remove("mostrar");
     LimpiarFormulario();
    setTimeout(function () {
        contenedor__ventana_registrar.classList.remove("mostrar");
    }, 200);
}

function LimpiarFormulario(){
    
    document.getElementById("NombreAdministradorR").value = "";
    document.getElementById("TipoDocumentoAdministradorR").options[0].selected = "true";
    document.getElementById("DocumentoAdministradorR").value = "";
    document.getElementById("CorreoAdministradorR").value = "";
    document.getElementById("RolAdministradorR").options[0].selected = true;
}


function informacionAdministrador(idAdministrador) {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
//                    console.log(JSON.parse(xhr.response));
                    var administrador = JSON.parse(xhr.response);
                    resolve(administrador);
                } else {
                    reject(new Error("Error en la solicitud AJAX"));
                }
            }
        };

        xhr.open("POST", "ControladorAdministrador", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var datos = "CRUD=infoAdministrador&idAdministrador=" + idAdministrador;
        xhr.send(datos);
    });
}


var documentoAdministradorR = document.getElementById("DocumentoAdministradorR");

documentoAdministradorR.addEventListener('input', () => {
    verificarDocumentoAdministrador(documentoAdministradorR.value);
});

function verificarDocumentoAdministrador(documentoAdministradorR) {

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if (xhr.responseText === "true") {
                document.querySelector("p[data-name=MensajeDocumento]").innerHTML = "Documento no disponible";
                document.getElementById("BTN__RegistrarA").disabled = "true";
            } else {
                document.querySelector("p[data-name=MensajeDocumento]").innerHTML = "";
                document.getElementById("BTN__RegistrarA").disabled = false;
            }
        }
    };

    xhr.open("POST", "ControladorAdministrador", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    var datos = "CRUD=verificarDocumento&documentoAdministrador=" + documentoAdministradorR;
    xhr.send(datos);
}


