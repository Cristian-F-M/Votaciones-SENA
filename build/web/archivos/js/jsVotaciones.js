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
//            var espacio = input.closest(".card-candidato");
//            var h2 = espacio.querySelector("a .nombre h2");
//            document.getElementById("exampleModalLabel").innerHTML = "Confirmar voto";
//            document.querySelector(".modal-body").innerHTML = `Vas a votar por <strong>${h2.textContent}</strong> confirmar voto?`;
//            BTNModal.click();
//            document.querySelector(".pMensaje").innerHTML = "";
            boton.addEventListener('submit', verificarVoto());
            return;
        } else {
//            alert("Selecciona un candidato");
            document.querySelector(".pMensaje").innerHTML = "Selecciona a un candidato";
        }

        function verificarVoto(evt) {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {

                if (xhr.readyState === 4 && xhr.status === 200) {
                    if (xhr.responseText === "true") {
                        console.log(xhr.responseText);
                        var espacio = input.closest(".card-candidato");
                        var h2 = espacio.querySelector("a .nombre h2");
                        document.getElementById("exampleModalLabel").innerHTML = "Confirmar voto";
                        document.querySelector(".modal-body").innerHTML = `Vas a votar por <strong>${h2.textContent}</strong> confirmar voto?`;
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

console.log(cardsCandidato);

cardsCandidato.forEach(function (card) {
    card.addEventListener('click', (evt) => {
        evt.preventDefault();
        informacionCandidato(card.getAttribute("data")).then(() => {
            console.log("aqui?");
        });
    });


});



async function informacionCandidato(idCandidato) {

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("Here");
            console.log(JSON.parse(xhr.response));

        } else {
        }
    };

    xhr.open("POST", "ContralodorCandidato", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    var datos = JSON.stringify({
        "CRUD": "infoCandidato",
        "idCandidato": idCandidato
    })
    console.log(datos);
    xhr.send(datos);

    await new Promise(resolve => setTimeout(resolve, 800));
}

