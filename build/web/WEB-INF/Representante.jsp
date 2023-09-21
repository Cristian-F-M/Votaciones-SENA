<%-- 
    Document   : Representante
    Created on : 19/09/2023, 10:57:41 a. m.
    Author     : pirul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="archivos/css/cssRepresentante.css">
        <link rel="stylesheet" href="archivos/css/css/bootstrap.css"/>
        <title>Representante</title>
    </head>

    <body>
        <jsp:include page="jspf/menu.jspf"></jsp:include>
            <div class="main">
                <div class="foto-nombre">
                    <div class="foto">
                        <img src="archivos/images/Foto.jpg" alt="Foto del representante">
                    </div>
                    <div class="nombre">
                        <h1>Cristian Morales</h1>
                    </div>
                </div>
                <div class="contenedor-informacion-candidato">
                    <div class="card-informacion-candidato">
                        <div class="descripcion">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Error quas nisi maxime inventore atque eveniet nemo, modi beatae sapiente alias ipsam! Maxime delectus iste voluptatibus doloremque, amet molestias ab possimus!</div>
                    </div>
                </div>
            </div>
        <jsp:include page="jspf/footer.jsp"></jsp:include>
        <script src="archivos/js/js/bootstrap.js"></script>

    </body>

</html>
