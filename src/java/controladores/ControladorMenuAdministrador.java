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
@WebServlet(name = "ControladorMenuAdministrador", urlPatterns = {"/ControladorMenuAdministrador"})
public class ControladorMenuAdministrador extends HttpServlet {

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

        PrintWriter out = response.getWriter();
        String opcion = request.getParameter("opcion");

        switch (opcion) {
            case "IniciarSesion":
                request.getRequestDispatcher("WEB-INF/IniciarSesionAdministrador.jsp").forward(request, response);
                break;

            case "EditarPefil":
                HttpSession session = request.getSession();
                out.println(session.getAttribute("nombreAdministrador"));
                out.println("<br>");
                out.println(session.getAttribute("rolAdministrador"));
//                request.getRequestDispatcher("WEB-INF/EditarAdministrador.jsp").forward(request, response);
                break;

            case "CerrarSesion":
                HttpSession sessionAdministrador = request.getSession();
                sessionAdministrador.invalidate();
                request.getRequestDispatcher("WEB-INF/Administrador.jsp").forward(request, response);
                break;

            case "Inicio":
                request.getRequestDispatcher("WEB-INF/Administrador.jsp").forward(request, response);
                break;

            case "Aprendices":
                request.getRequestDispatcher("WEB-INF/Aprendices.jsp").forward(request, response);
                break;

            case "Candidatos":
                request.getRequestDispatcher("WEB-INF/Candidatos.jsp").forward(request, response);
                break;

            case "Historial":
                request.getRequestDispatcher("WEB-INF/Historial.jsp").forward(request, response);
                break;

            case "Administrador":
                request.getRequestDispatcher("WEB-INF/Administradores.jsp").forward(request, response);
                break;

            case "Otros":
                request.getRequestDispatcher("WEB-INF/Otros.jsp").forward(request, response);
                break;
                

            default:
                out.println("No hay opción en el controlador");
                throw new AssertionError();
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
