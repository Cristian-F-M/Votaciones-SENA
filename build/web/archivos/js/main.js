/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

iPassword = document.getElementById("ojo");
inputPasswordOculta = document.getElementById("PasswordAprendiz");

iPassword.addEventListener('click', () => {
    iPassword.classList.toggle("bi-eye-slash-fill");
    iPassword.classList.toggle("bi-eye");
    inputPasswordOculta.type = inputPasswordOculta.type === 'password' ? 'text' : 'password';
});


