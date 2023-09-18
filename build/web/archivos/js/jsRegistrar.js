/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
//function PopUp() {
//
//    var mensaje = document.querySelector(".");
//    console.log(mensaje);
//    if (mensaje !== "null") {
//        console.log(mensaje);
//        alert(mensaje);
////        function IniciaNotificacion() {
////            const notificacion = new Notification(
////                    'Ha iniciado la votación',
////                    {
////                        body: 'Para este año 2023 vota por tu candidato a representante',
////                        icon: 'logo-SENA.png'
////                    }
////            );
////        }
//    }
//}


var request;
var inputDocumento = document.getElementById("TipoDocumentoAprendiz").value;
var botinRegistrar = document.getElementById("Registar");


function documentoAprendiz() {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
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

