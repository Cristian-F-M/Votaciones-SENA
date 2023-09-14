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
import modelos.Rol;

/**
 *
 * @author pirul
 */
@WebServlet(name = "ControladorRol", urlPatterns = {"/ControladorRol"})
public class ControladorRol extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorRol</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorRol at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String id = request.getParameter("fIdRol");
        String descripcionRol = request.getParameter("fDescripcionRol");
        String accion = request.getParameter("fAccion");
        
        int idRol = 0;
        try {
            idRol = Integer.parseInt(id);
        } catch (NullPointerException ex) {}

        Rol rol = new Rol();
        rol.setIdRol(idRol);
        rol.setDescripcionRol(descripcionRol);
        
        String mensaje;
        switch(accion){
            case "Registrar":
                mensaje = "Rol registrado";
                rol.Insertar();
                request.getRequestDispatcher("/WEB-INF/ListarRol.jsp?mensaje=" + mensaje).forward(request, response);
                break;
                
            case "Modificar":
                mensaje = "Rol editado";
                rol.Modificar();
                request.getRequestDispatcher("/WEB-INF/ListarRol.jsp?mensaje=" + mensaje).forward(request, response);
                break;
                
            case "Eliminar":
                mensaje = "Rol eliminado";
                rol.Eliminar();
                request.getRequestDispatcher("/WEB-INF/ListarRol.jsp?mensaje=" + mensaje).forward(request, response);
                break;
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
