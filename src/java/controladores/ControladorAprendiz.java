/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Aprendiz;

/**
 *
 * @author pirul
 */
@WebServlet(name = "ControladorAprendiz", urlPatterns = {"/ControladorAprendiz"})
public class ControladorAprendiz extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControladorAprendiz</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControladorAprendiz at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("fIdAprendiz");
        String nombreAprendiz = request.getParameter("fNombreAprendiz");
        String tipoDocumento = request.getParameter("fTipoDocumentoAprendiz");
        String documentoAprendiz = request.getParameter("fDocumentoAprendiz");
        String correoAprendiz = request.getParameter("fCorreoAprendiz");
        String contraseñaAprendiz = request.getParameter("fPasswordAprendiz");
        String candidato = request.getParameter("fCandidato");
        String accion = request.getParameter("CRUD");

        int idAprendiz = 0;

        try {
            idAprendiz = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
        }

        int tipoDocumentoAprendiz = 0;
        if (tipoDocumento != null) {
            try {
                tipoDocumentoAprendiz = Integer.parseInt(tipoDocumento);
            } catch (NumberFormatException ex) {
                System.err.println(ex);
            }
        }
        int candidatoAprendiz = 0;
        try {
            candidatoAprendiz = Integer.parseInt(candidato);
        } catch (NumberFormatException ex) {
        }

        Aprendiz aprendiz = new Aprendiz();
        aprendiz.setIdAprendiz(idAprendiz);
        aprendiz.setNombreAprendiz(nombreAprendiz);
        aprendiz.setTipoDocumentoAprendiz(tipoDocumentoAprendiz);
        aprendiz.setDocumentoAprendiz(documentoAprendiz);
        aprendiz.setCorreoAprendiz(correoAprendiz);
        aprendiz.setContraseñaAprendiz(contraseñaAprendiz);
        aprendiz.setCandidato(candidatoAprendiz);

        PrintWriter out = response.getWriter();
        String mensaje = null;

        switch (accion) {
            case "Registrar":
                if (!(aprendiz.getNombreAprendiz() == null || (aprendiz.getDocumentoAprendiz() == null || aprendiz.getCorreoAprendiz() == null || aprendiz.getContraseñaAprendiz() == null))) {
                    boolean rsD = aprendiz.verificarDocumento();
                    boolean rsC = aprendiz.verificarCorreo();
                    if (rsD == false && rsC == false) {
                        aprendiz.Insertar();
                        mensaje = "Registrado con exito";
                    } else {
                        mensaje = "NO se registro, datos no validos";
                    }
                } else {
                    mensaje = "Completa todos los campos";
                }

                request.getRequestDispatcher("WEB-INF/RegistrarAprendiz.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "Modificar":
                aprendiz.Modificar();
                mensaje = "Aprendiz modificado";
                request.getRequestDispatcher("/WEB-INF/EditarAprendiz.jsp?mensaje = " + mensaje).forward(request, response);
                break;

            case "Eliminar":
                aprendiz.Eliminar();
                mensaje = "Aprendiz eliminado";
                request.getRequestDispatcher("/WEB-INF/ListarAprendiz.jsp?mensaje = " + mensaje).forward(request, response);
                break;

            case "IniciarSesion":
                boolean verificar = aprendiz.IniciarSesion();
                Aprendiz aprendizIniciado = Aprendiz.getAprendizIniciado();
                if (verificar) {
                    HttpSession session = request.getSession();
                    session.setAttribute("idAprendiz", aprendizIniciado.getIdAprendiz());
                    session.setAttribute("nombreAprendiz", aprendizIniciado.getNombreAprendiz());
                    session.setAttribute("tipoDocumentoAprendiz", aprendizIniciado.getTipoDocumentoAprendiz());
                    session.setAttribute("documentoAprendiz", aprendizIniciado.getDocumentoAprendiz());
                    session.setAttribute("correoAprendiz", aprendizIniciado.getCorreoAprendiz());
                    session.setAttribute("candidato", aprendizIniciado.getCandidato());
                    request.getRequestDispatcher("index.jsp?mensaje = " + mensaje).forward(request, response);
                } else {
                    mensaje = "Datos de usuario invalidos";
                    request.getRequestDispatcher("WEB-INF/IniciarSesionAprendiz.jsp?mensaje=" + mensaje).forward(request, response);
                }
                break;

            case "documentoValido":
                String respuesta = String.valueOf(aprendiz.verificarDocumento());
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
                break;

            case "correoValido":
                respuesta = String.valueOf(aprendiz.verificarCorreo());
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
                break;

            case "Votar":
                HttpSession session = request.getSession();
                System.out.println(session.getAttribute("idAprendiz"));
                if (session.getAttribute("idAprendiz") != null) {
                    aprendiz.Votar();
                    mensaje = "Voto registrado";
                    request.getRequestDispatcher("WEB-INF/Votacion.jsp?mensaje=" + mensaje).forward(request, response);
                } else {
                    mensaje = "Debes iniciar sesión para votar";
                    request.getRequestDispatcher("WEB-INF/Votacion.jsp?mensaje=" + mensaje).forward(request, response);
                }

                break;

            case "votoValido":
                aprendizIniciado = Aprendiz.getAprendizIniciado();
                int a = aprendiz.verificarVoto(aprendizIniciado.getIdAprendiz());

                if (a != 0) {
                    respuesta = "true";
                } else {
                    respuesta = "false";
                }

                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
                break;
            default:
                System.out.println("No se encontro ninguna accion en el controlador");
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
