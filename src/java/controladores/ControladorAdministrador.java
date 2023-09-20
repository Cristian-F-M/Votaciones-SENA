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
import modelos.Administrador;
import modelos.Rol;
import modelos.TipoDocumento;

/**
 *
 * @author pirul
 */
@WebServlet(name = "ControladorAdministrador", urlPatterns = {"/ControladorAdministrador"})
public class ControladorAdministrador extends HttpServlet {

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
            out.println("<title>Servlet ControladorAdministrador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAdministrador at " + request.getContextPath() + "</h1>");
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

        String id = request.getParameter("fIdAdministrador");
        String nombreAdministrador = request.getParameter("fNombreAdmistrador");
        String ftipoDocumento = request.getParameter("fTipoDocumentoAdministrador");
        String documentoAdministrador = request.getParameter("fDocumentoAdministrador");
        String correoAdministrador = request.getParameter("fCorreoAdministrador");
        String contraseñaAdministrador = request.getParameter("fPasswordAdministrador");
        String frol = request.getParameter("fRolAdministrador");
        String accion = request.getParameter("CRUD");

        int idAdministrador = 0;
        try {
            idAdministrador = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
        }

        int tipoDocumentoAdministrador = 0;
        try {
            tipoDocumentoAdministrador = Integer.parseInt(ftipoDocumento);
        } catch (NumberFormatException ex) {
        }

        int rolAdministrador = 0;
        try {
            rolAdministrador = Integer.parseInt(frol);
        } catch (NumberFormatException ex) {
        }

        Administrador administrador = new Administrador();
        TipoDocumento tipoDocumento = new TipoDocumento();
        Rol rol = new Rol();

        administrador.setIdAdministrador(idAdministrador);
        administrador.setNombreAdministrador(nombreAdministrador);

        tipoDocumento.setIdTipoDocumento(tipoDocumentoAdministrador);
        administrador.setTipoDocumentoAdministrador(tipoDocumento);

        administrador.setDocumentoAdministrador(documentoAdministrador);
        administrador.setCorreoAdministrador(correoAdministrador);
        administrador.setContraseñaAdministrador(contraseñaAdministrador);

        rol.setIdRol(rolAdministrador);
        administrador.setRolAdministrador(rol);

        String mensaje = "";
        switch (accion) {
            case "Registrar":
                mensaje = "Administrador registrado";
                administrador.Insertar();
                request.getRequestDispatcher("WEB-INF/RegistrarAdministrador.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "Modificar":
                mensaje = "Administrado editado";
                administrador.Modificar();
                request.getRequestDispatcher("WEB-INF/ListarAdministrador.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "Eliminar":
                mensaje = "Administrador eliminado";
                administrador.Eliminar();
                request.getRequestDispatcher("WEB-INF/ListarAdministrador.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "IniciarSesion":
                boolean verificar = administrador.IniciarSesion();
                if (verificar) {
                    HttpSession sessionAdministrador = request.getSession();
                    Administrador administradorIniciado = Administrador.getAdminitradorIniciado();
//                    
                    sessionAdministrador.setAttribute("idAdministrador", administradorIniciado.getIdAdministrador());
                    sessionAdministrador.setAttribute("nombreAdministrador", administradorIniciado.getNombreAdministrador());
                    sessionAdministrador.setAttribute("tipoDocumentoAdministrador", administradorIniciado.getTipoDocumentoAdministrador());
                    sessionAdministrador.setAttribute("documentoAdministrador", administradorIniciado.getDocumentoAdministrador());
                    sessionAdministrador.setAttribute("rolAdministrador", administradorIniciado.getRolAdministrador());

                    request.getRequestDispatcher("WEB-INF/Administrador.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("WEB-INF/IniciarSesionAdministrador.jsp").forward(request, response);
                }
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
