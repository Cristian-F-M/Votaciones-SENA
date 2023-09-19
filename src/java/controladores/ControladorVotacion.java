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
import java.sql.Date;
import java.time.format.DateTimeParseException;
import modelos.Votacion;

/**
 *
 * @author pirul
 */
@WebServlet(name = "ControladorVotacion", urlPatterns = {"/ControladorVotacion"})
public class ControladorVotacion extends HttpServlet {

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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControladorVotacion</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControladorVotacion at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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

        PrintWriter out = response.getWriter();

        String fechaInicio = "2023-09-28";
        String fechaFin = "2023-09-28";

        String id = request.getParameter("fIdVotacion");
        fechaInicio = request.getParameter("fFechaInicioVotacion");
        fechaFin = request.getParameter("fFechaFinVotacion");
        String cantVotos = request.getParameter("fCantVotosVotacion");
        String ganador = request.getParameter("fGanadorVotacion");
        String accion = request.getParameter("fAccion");

        int idVotacion = 0;
        try {
            idVotacion = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
        }

        Date fechaInicioVotacion = null;
        try {
            if (fechaInicio != null) {
                fechaInicioVotacion = Date.valueOf(fechaInicio);
            }
        } catch (DateTimeParseException ex) {
        }

        Date fechaFinVotacion = null;
        try {
            if (fechaFin != null) {
                fechaFinVotacion = Date.valueOf(fechaFin);
            }
        } catch (DateTimeParseException ex) {
        }

        int cantVotosVotacion = 0;
        try {
            cantVotosVotacion = Integer.parseInt(cantVotos);
        } catch (NumberFormatException ex) {
        }

        int ganadorVotacion = 0;
        try {
            ganadorVotacion = Integer.parseInt(ganador);
        } catch (NumberFormatException ex) {
        }

        Votacion votacion = new Votacion();
        votacion.setIdVotacion(idVotacion);
//        votacion.setFechaInicioVotacion(fechaInicioVotacion);
//        votacion.setFechaFinVotacion(fechaFinVotacion);
        votacion.setCantVotosVotacion(cantVotosVotacion);
        votacion.setGanadorVotacion(ganadorVotacion);

        String mensaje;
        switch (accion) {
            case "Registrar":
                mensaje = "Votacion registrada";
                votacion.Insertar();
                request.getRequestDispatcher("/WEB-INF/ListarVotacion.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "Modificar":
                mensaje = "Votacion editada";
                votacion.Modificar();
                request.getRequestDispatcher("/WEB-INF/ListarVotacion.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "Eliminar":
                mensaje = "Votacion eliminada";
                votacion.Eliminar();
                request.getRequestDispatcher("/WEB-INF/ListarVotacion.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "fechaVotacion":
                Votacion unaVotacion = votacion.ListarVotacionActual();

                String respuesta = String.valueOf(unaVotacion.getFechaFinVotacion());
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);

                break;

            default:
                out.println("No hay opcion en el controlador");
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
