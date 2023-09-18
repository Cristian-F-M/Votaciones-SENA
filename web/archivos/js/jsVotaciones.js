/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

//Declaracion de variables 
var boton = document.querySelector("button[value=Enviar]");
var BTNModal = document.getElementById("BTNPopUp");


//Cuando se haga click en el boton se hace una funcion y se detiene la accion default del boton
boton.addEventListener('click', (evt) => {
    var inputsRadio = document.querySelectorAll("input[name=fCandidato]");

    for (let i = 0; i < inputsRadio.length; i++) {

//if para saber si hay alguno seleccionado   
//Si es así se despliega una ventana emergente con la verificacion
        var input = inputsRadio[i];

        if (input.checked) {
//            console.log(input);
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
                    } else if (xhr.responseText === "false") {
                        document.getElementById("exampleModalLabel").innerHTML = "Mensaje";
                        document.querySelector(".modal-body").innerHTML = "Ya tu has registrado tu voto";
//                                `;}
                    }
                }

                xhr.open("POST", "ControladorAprendiz", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                var datos = "CRUD=votoValido";
                xhr.send(datos);
            };



            break;
            ;
        } else {
            document.querySelector(".pMensaje").innerHTML = "Selecciona a un candidato";
            evt.preventDefault();
        }
    }

});



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
var fechaFinVotacion = new Date("2023-09-21T00:00:00");



//Funcion para una cuenta regresiva para el fin de las votaciones
function actualizarCuentaRegresiva() {
    const ahora = new Date();
    const diferencia = fechaFinVotacion - ahora;
    const p = document.getElementById("cuenta-regresiva");
    if (diferencia <= 0) {
//        console.log("La fecha objetivo ya ha pasado.");
        p.innerHTML = "La fecha objetivo ya ha pasado.";
    } else {
        const dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        const horas = Math.floor((diferencia % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutos = Math.floor((diferencia % (1000 * 60 * 60)) / (1000 * 60));
        const segundos = Math.floor((diferencia % (1000 * 60)) / 1000);

        p.innerHTML = `Faltan ${dias} dias, ${horas} horas, ${minutos} minutos y ${segundos} segundos.`;
    }
}

// La funcion se ejecuta cada 1000ms (1seg)
setInterval(actualizarCuentaRegresiva, 1000);

// se ejecuta la funcion al inicio para ver una cuenta regresiva
actualizarCuentaRegresiva();


// Hace que se ejecute una función cuando se le de click a boton para enviar 
// Hacer consulta ajax para saber 
