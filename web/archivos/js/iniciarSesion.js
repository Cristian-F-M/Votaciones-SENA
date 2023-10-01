/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var inputPassword = document.getElementById("PasswordAprendiz");
var inputMostrar = document.getElementById("checkbox");


inputMostrar.addEventListener('change', () => {
    inputMostrar.checked ? inputPassword.type = "text" : inputPassword.type = "password";
});