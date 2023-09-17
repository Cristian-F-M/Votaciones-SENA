<%-- 
    Document   : Candidato
    Created on : 17/09/2023, 11:57:34 a. m.
    Author     : pirul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="candidatos2.css">
        <link rel="stylesheet" href="bootstrap-5.3.1-dist/css/bootstrap.css">
        <title>Candidatos</title>
    </head>

    <body>

        <!-- Header -->
        <header>
            <!-- <div class="img"><img src="archivos/images/logo-SENA.png" alt="Logo del sena"></div> -->
            <div class="img"><a href="a.html"><img src="logo-SENA.png" alt="Logo del sena"></a></div>
            <ul>
                <li><a href="ControladorMenu?opcion=Home">Home</a></li>
                <li><a href="representante.html">Representante</a></li>
                <li><a href="ControladorMenu?opcion=Candidato">Candidato</a></li>
                <li><a href="tabla.html">Nosotros</a></li>
                <li><a href="Contacto.html">Contacto</a></li>
            </ul>
            <div class="contenedor-menu">
                <button class="btn-menu" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight"
                        aria-controls="offcanvasRight">☰
                </button>
        </header>

        <!-- header -->

        <!-- main -->
        <main class="main">
            <h1>Votacion para el representante del sena</h1>
            <div class="informacion alert alert-warning">
                <Strong>Para las votaciones</Strong>
                Tenga en cuenta que solo podra vota una vez y que despues de
                finalizada la votación ya no prodra votar.
            </div>

            <div class="fecha">
                <Strong>Tiempo restante</Strong>
                <p id="cuenta-regresiva"></p>
            </div>
            <form action="ControladorAprendiz" method="post">
                <div class="candidatos">


                    <div class="card-candidato">
                        <a href=""><span>Saber más</span>
                            <div class="foto"><img src="Foto.jpg" alt=""></div>
                            <div class="nombre">
                                <h1>Cristian Fernando Morales Diaz</h1>
                            </div>
                        </a>
                        <div class="inputRadio">
                            <input id="1" name="fCandidato" type="radio">
                            <label for="1">Seleccionar</label>
                        </div>
                    </div>


                    <div class="card-candidato">
                        <a href=""><span>Saber más</span>
                            <div class="foto"><img src="Foto.jpg" alt=""></div>
                            <div class="nombre">
                                <h1>Cristian Fernando Morales Diaz</h1>
                            </div>
                        </a>
                        <div class="inputRadio">
                            <input id="2" name="fCandidato" type="radio">
                            <label for="2">Seleccionar</label>
                        </div>
                    </div>


                    <div class="card-candidato">
                        <a href=""><span>Saber más</span>
                            <div class="foto"><img src="Foto.jpg" alt=""></div>
                            <div class="nombre">
                                <h1>Cristian Fernando Morales Diaz</h1>
                            </div>
                        </a>
                        <div class="inputRadio">
                            <input id="3" name="fCandidato" type="radio">
                            <label for="3">Seleccionar</label>
                        </div>
                    </div>


                    <div class="card-candidato">
                        <a href=""><span>Saber más</span>
                            <div class="foto"><img src="Foto.jpg" alt=""></div>
                            <div class="nombre">
                                <h1>Cristian Fernando Morales Diaz</h1>
                            </div>
                        </a>
                        <div class="inputRadio">
                            <input id="4" name="fCandidato" type="radio">
                            <label for="4">Seleccionar</label>
                        </div>
                    </div>
                </div>
                <p class="pMensaje"></p>
                <button type="button" name="CRUD" value="Enviar">Enviar</button>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmar voto</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Vas a votar por Cristian Morales, confirmar voto?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-primary">Confirmar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </main>

        <button style="display: none;" id="BTNModal" type="button" class="btn btn-primary" data-bs-toggle="modal"
                data-bs-target="#exampleModal">
        </button>

        <!-- main -->
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasRightLabel">Usuario</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul>
                    <li><a href="ControladorMenu?opcion=EditarPefil">Editar perfil</a></li>
                    <li><a href="ControladorMenu?opcion=IniciarSesion">Iniciar Sesion</a></li>
                    <li><a href="ControladorMenu?opcion=Registrar">Registrarse</a></li>
                    <li><a href="ControladorMenu?opcion=CerrarSesion">Cerrar sesión</a></li>
                </ul>
            </div>
        </div>
        <!-- main -->

        <!-- footer -->
        <footer>
            <div>Ultima actualización</div>
            <div>Horario de atención</div>
            <div id="año"></div>
        </footer>
        <!-- footer -->
</html>
