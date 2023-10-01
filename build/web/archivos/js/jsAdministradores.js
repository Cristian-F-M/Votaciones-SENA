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
            });
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
    setTimeout(function () {
        // contenedor__ventana.style.display = "none";
        contenedor__ventana_registrar.classList.remove("mostrar");
    }, 200);
}




function informacionAdministrador(idAdministrador) {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    var administrador = JSON.parse(xhr.response);
                    resolve(administrador); // Resuelve la promesa con el valor del candidato
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
