/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

//Declaracion de variables 
var boton = document.querySelector("button[value=Enviar]");
var BTNModal = document.getElementById("BTNPopUp");


function validarVoto() {

    var diferencia = calcularDiferenciaDeTiempo(fechaFinVotacion);
    if (diferencia <= 0) {
        alert("La votacion ya ha finalizado");
        return;
    }

    if (idAprendizIniciado === 0) {
        alert("Debes iniciar sesion para votar");
    }

    var inputsRadio = document.querySelectorAll("input[type=radio]");
    for (let i = 0; i < inputsRadio.length; i++) {
        var input = inputsRadio[i];
        if (input.checked) {
            boton.addEventListener('submit', verificarVoto());
            return;
        } else {
            document.querySelector(".pMensaje").innerHTML = "Selecciona a un candidato";
        }

        function verificarVoto(evt) {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {

                if (xhr.readyState === 4 && xhr.status === 200) {
                    if (xhr.responseText === "true") {
                        var espacio = input.closest(".card-candidato");
                        var h2 = espacio.querySelector("a .nombre h2");
                        document.getElementById("exampleModalLabel").innerHTML = "Confirmar voto";
                        document.querySelector(".modal-body").innerHTML = `<div>Vas a votar por <strong>${h2.textContent}</strong> confirmar voto?</div>`;
                        BTNModal.click();
                        document.querySelector(".pMensaje").innerHTML = "";
                        return;
                    } else if (xhr.responseText === "false") {
                        alert("Tu ya has registrado tu voto");
                        document.querySelector(".pMensaje").innerHTML = "";
                        evt.preventDefault();
                    }
                }
            };
            xhr.open("POST", "ControladorAprendiz", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var datos = "CRUD=votoValido";
            xhr.send(datos);
        }
    }

}

//declaracion de variable
var radios = document.querySelectorAll('input[type=radio]');





//se recorren los input para detectar un cambio

radios.forEach(function (radio) {
    radio.addEventListener('change', () => {


//        si esta checked el label cambia a seleccionado
        if (radio.checked) {
            var label = document.querySelector("label[for='" + radio.id + "']");

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
        boton.addEventListener('click', validarVoto);
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


var cardsCandidato = document.querySelectorAll("a[id=cardCandidatos]");



cardsCandidato.forEach(function (card) {
    card.addEventListener('click', (evt) => {
        evt.preventDefault();
        informacionCandidato(card.getAttribute("data"))
                .then((candidato) => {



                    var card = "<div class='card' style='width: 18rem;'>\n\
                                    <img id='fotoCandidato' src='" + candidato.fotoCandidato + "' class='card-img-top' alt='Foto de " + candidato.aprendiz.nombreAprendiz + "'>\n\
                                    <div class='card-body'>\n\
                                        <p class='card-text'>" + candidato.descripcionCandidato + "</p>\n\
                                    </div>\n\
                                </div>\n\
                                <div class='inputRadio'>\n\
                                    <input data-id='" + candidato.idCandidato + "' type='radio'>\n\
                                    <label data-id='" + candidato.idCandidato + "' for='" + candidato.idCandidato + "'>Seleccionar</label>\n\
                                </div>";
                    var modalbody = document.querySelector(".modal-body");
                    var h1 = document.getElementById("exampleModalLabel");
                    h1.innerHTML = candidato.aprendiz.nombreAprendiz;
                    h1.style.textAlign = "center";
                    h1.style.width = "100%";
                    h1.style.padding = "0";
                    document.getElementById("BTN-Modal").style.display = "none";
                    document.querySelector("button[value=Votar]").style.display = "none";

                    modalbody.style.display = "flex";
                    modalbody.style.justifyContent = "center";
                    modalbody.innerHTML = card;
                    BTNModal.click();

                    var inputPopUp = document.querySelector(".modal-body input[type=radio]");
                    var inputCardCandidato = document.getElementById(inputPopUp.getAttribute("data-id"));

                    inputCardCandidato.addEventListener('change', () => {
                        inputPopUp.checked = true;

                        var labelPopUp = document.querySelector(".modal-body label[data-id='" + candidato.idCandidato + "']");
                        labelPopUp.innerHTML = "Seleccionado";
                    });

                })
                .catch((error) => {
                    console.error("Error al obtener la información del candidato:", error);
                });
    });
});

function informacionCandidato(idCandidato) {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    var candidato = JSON.parse(xhr.response);
                    resolve(candidato); // Resuelve la promesa con el valor del candidato
                } else {
                    reject(new Error("Error en la solicitud AJAX"));
                }
            }
        };

        xhr.open("POST", "ContralodorCandidato", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var datos = "CRUD=infoCandidato&idCandidato=" + idCandidato;
        xhr.send(datos);
    });
}




