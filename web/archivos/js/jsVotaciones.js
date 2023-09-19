/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

//Declaracion de variables 
var boton = document.querySelector("button[value=Enviar]");

boton.addEventListener('submit', validarVoto);

var BTNModal = document.getElementById("BTNPopUp");
//Cuando se haga click en el boton se hace una funcion y se detiene la accion default del boton
function validarVoto(evt) {
    var diferencia = calcularDiferenciaDeTiempo(fechaFinVotacion);
    
    
    
    
    if (diferencia > 0) {
        if (idAprendizIniciado !== 0) {
            var inputsRadio = document.querySelector("input[type=radio]");
            for (let i = 0; i < inputsRadio.length; i++) {
                var input = inputsRadio[i];
                if (input.checked) {


                    var xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {

                        if (xhr.readyState === 4 && xhr.status === 200) {
                            if (xhr.responseText === "true") {
                                var espacio = input.closest(".card-candidato");
                                var h2 = espacio.querySelector("a .nombre h2");
                                document.getElementById("exampleModalLabel").innerHTML = "Confirmar voto";
                                document.querySelector(".modal-body").innerHTML = `Vas a votar por <strong>${h2.textContent}</strong> confirmar voto?`;
                                BTNModal.click();
                                document.querySelector(".pMensaje").innerHTML = "";
                                return;
                            } else if (xhr.responseText === "false") {
                                evt.preventDefault();
                                alert("Tu ya has registrado tu voto");
                                document.querySelector(".pMensaje").innerHTML = "";
                            }
                        }
                    };
                    xhr.open("POST", "ControladorAprendiz", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    var datos = "CRUD=votoValido";
                    xhr.send(datos);
                    break;
//            };
                } else {
                    document.querySelector(".pMensaje").innerHTML = "Selecciona a un candidato";
                    evt.preventDefault();
                }
            }
        } else {
            alert("Debes iniciar sesion para votar");
            window.location.href = "ControladorMenu?opcion=IniciarSesion";
        }
    } else {
        evt.preventDefault();
        alert("La votacion ya ha finalizad0\n\
                        espera por los resultados");
    }
}
;
//declaracion de variable
var radios = document.querySelectorAll('input[type=radio]');
// console.log(radios);


//se recorren los input para detectar un cambio

radios.forEach(function (radio) {
    radio.addEventListener('change', () => {


//        si esta checked el label cambia a seleccionado
        if (radio.checked) {
            var label = document.querySelector("label[for='" + radio.id + "']");
            // console.log(radio.id);
            label.innerHTML = "Selecionado";
        }
// Recorre todos los inputs para saber si no está checked : Canbia el texto del label a seleccionar
        radios.forEach(function (radio) {
            if (!radio.checked) {
                var label = document.querySelector("label[for='" + radio.id + "']");
                label.innerHTML = "Seleccionar";
            }
        });
    });
});
// Hacer la consulata AJAX para saber la fecha y asignarla a fechaFin Votacion ----- [falta]


var fechaFinVotacion = null; // Inicialmente null

var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        fechaFinVotacion = new Date(xhr.responseText); // Asigna la fecha obtenida de la consulta AJAX
        actualizarCuentaRegresiva(); // Llama a la función actualizarCuentaRegresiva para comenzar la cuenta regresiva
        validarVoto();
    }
};
xhr.open("POST", "ControladorVotacion", true);
xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
var datos = "fAccion=fechaVotacion";
xhr.send(datos);
function calcularDiferenciaDeTiempo(fechaFinVotacion) {
    const ahora = new Date();
    const diferencia = fechaFinVotacion - ahora;
    return diferencia;
}



function actualizarCuentaRegresiva() {
    const ahora = new Date();
    const diferencia = fechaFinVotacion - ahora;
    const p = document.getElementById("cuenta-regresiva");
    if (diferencia <= 0) {
        p.style.color = "red";
        p.innerHTML = "La fecha objetivo ya ha pasado.";
    } else {
        const dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        const horas = Math.floor((diferencia % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutos = Math.floor((diferencia % (1000 * 60 * 60)) / (1000 * 60));
        const segundos = Math.floor((diferencia % (1000 * 60)) / 1000);
        p.innerHTML = `Faltan ${dias} dias, ${horas} horas, ${minutos} minutos y ${segundos} segundos.`;
    }
}

// Actualiza la cuenta regresiva cada segundo
setInterval(actualizarCuentaRegresiva, 1000);