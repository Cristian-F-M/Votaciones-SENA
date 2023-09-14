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

/**
 *
 * @author pirul
 */
@WebServlet(name = "ControladorMenu", urlPatterns = {"/ControladorMenu"})

public class ControladorMenu extends HttpServlet {

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

        PrintWriter out = response.getWriter();
        String opcion = request.getParameter("opcion");
        switch (opcion) {
            case "IniciarSesion":
                request.getRequestDispatcher("WEB-INF/IniciarSesionAprendiz.jsp").forward(request, response);
                break;

            case "Registrar":
                request.getRequestDispatcher("WEB-INF/RegistrarAprendiz.jsp").forward(request, response);
                break;

            case "CerrarSesion":
                HttpSession session = request.getSession();
                session.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "Home":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "Representante":
                request.getRequestDispatcher("infoReprensentante.jsp").forward(request, response);
                break;

            case "Votaciones":
                request.getRequestDispatcher("votaciones.jsp").forward(request, response);
                break;

            case "Nosotros":
                request.getRequestDispatcher("nosotros.jsp").forward(request, response);
                break;

            case "Contacto":
                request.getRequestDispatcher("WEB-INF/Contacto.jsp").forward(request, response);
                break;

            default:
                out.println("No hay ninguna opcion en el controlador");
        }

        response.setContentType("text/html;charset=UTF-8");
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
