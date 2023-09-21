<%-- 
    Document   : Contacto
    Created on : 10/09/2023, 4:11:58 p. m.
    Author     : pirul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="archivos/css/css/bootstrap.css"/>
        <link rel="stylesheet" href="archivos/css/cssContacto.css"/>
        <link rel="shortcut icon" href="archivos/images/logo-SENA.jpg" type="image/x-icon">
        <link rel="stylesheet" href="archivos/icons/icons/bootstrap-icons.css"/>
        <link rel="stylesheet" href="archivos/fonts/Crimson_Text,EB_Garamond.zip"/>
        <title>Contacto</title>
    </head>

    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
        <div class="main">
            <div class="title">
                <h1>Contacto</h1>
            </div>
            <div class="contacto">
                <div class="menu">
                    <ul>
                        <li>
                            <i class="bi bi-geo-alt-fill"></i>
                            <div>
                                <Strong>Localización</Strong>
                                <p>Transversal 8 No. 8A - 50, Vélez Santander</p>
                            </div>
                        </li>
                        <li>
                            <i class="bi bi-telephone-fill"></i>
                            <div>
                                <strong>Telefono celular </strong>
                                <p>7563502 - 7563017 - 7564253</p>
                            </div>
                        </li>
                        <li>
                            <i class="bi bi-headset"></i>
                            <div>
                                <Strong>Horarios de atención</Strong>
                                <p>Lunes a viernes 08:00 a.m. - 11:00 a.m. y 02:00 p.m. - 05:00 p.m.</p>
                            </div>
                        </li>
                        <li>
                            <i class="bi bi-envelope-at-fill"></i>
                            <div>
                                <strong>Correo electronico</strong>
                                <a href="">
                                    <p>a@gmail.com</p>
                                </a>
                            </div>
                        </li>
                        <li>
                            <i class="bi bi-whatsapp"></i>
                            <div>
                                <strong>WhatsApp</strong>
                                <a href="">
                                    <p>+57213213</p>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="mapa">
                    <div class="frame">
                        <iframe
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1983.9451090159446!2d-73.66890716941795!3d6.009809456353979!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8e41f06d1b94d193%3A0xd47ace7939d6dd68!2sSENA!5e0!3m2!1ses-419!2sco!4v1694376907243!5m2!1ses-419!2sco"
                            width="300" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                            referrerpolicy="no-referrer-when-downgrade">
                        </iframe>
                    </div>
                </div>
            </div>
        <jsp:include page="jspf/footer.jsp"></jsp:include>
        </div>
    </body>

</html>
