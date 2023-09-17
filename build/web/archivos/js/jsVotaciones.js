/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function PopUp() {

    var mensaje = 'request';
    if (mensaje !== "null") {
        var BTNPopUp = document.getElementById("BTNPopUp");
        BTNPopUp.click();
    }
}



var boton = document.querySelector("button[value=Enviar]");
var BTNModal = document.getElementById("BTNModal");



boton.addEventListener('click', (evt) => {
    var inputsRadio = document.querySelectorAll("input[name=fCandidato]");
    // console.log(inputsRadio)
    evt.preventDefault();

    inputsRadio.forEach(function (input) {
        if (input.checked) {
            document.querySelector(".pMensaje").innerHTML = "";
            var a = document.querySelector("input[name=fCandidato]:checked");
            console.log(a)
            document.querySelector(".modal-body").innerHTML = `Vas a votar por ${a.id} confirmar voto?`;
            BTNModal.click();
            return;
        } else {
            document.querySelector(".pMensaje").innerHTML = "Selecciona a un candidato";
            evt.preventDefault();
        }
    });
});

var radios = document.querySelectorAll('input[type=radio]');
// console.log(radios);

radios.forEach(function (radio) {
    radio.addEventListener('change', () => {
        if (radio.checked) {
            var label = document.querySelector("label[for='" + radio.id + "']");
            // console.log(radio.id);
            label.innerHTML = "Selecionado";
        }

        radios.forEach(function (radio) {
            if (!radio.checked) {
                var label = document.querySelector("label[for='" + radio.id + "']");
                label.innerHTML = "Seleccionar";
            }
        });

    });
});

// Hacer la consulata AJAX para saber la fecha y asignarla a fechaFin Votacion
var fechaFinVotacion = new Date("2023-09-21T00:00:00");

function actualizarCuentaRegresiva() {
    const ahora = new Date();
    const diferencia = fechaFinVotacion - ahora;
    const p = document.getElementById("cuenta-regresiva");
    if (diferencia <= 0) {
        console.log("La fecha objetivo ya ha pasado.");
    } else {
        const dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        const horas = Math.floor((diferencia % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutos = Math.floor((diferencia % (1000 * 60 * 60)) / (1000 * 60));
        const segundos = Math.floor((diferencia % (1000 * 60)) / 1000);

        p.innerHTML = `Faltan ${dias} dias, ${horas} horas, ${minutos} minutos y ${segundos} segundos.`;
    }
}

// Llama a la función para actualizar la cuenta regresiva cada segundo
setInterval(actualizarCuentaRegresiva, 1000);

// Llama a la función inicial para mostrar la cuenta regresiva al cargar la página
actualizarCuentaRegresiva();


// Hace que se ejecute una función cuando se le de click a boton para enviar 
// Hacer consulta ajax para saber 
