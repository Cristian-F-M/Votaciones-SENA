/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Aprendiz;
import modelos.Candidato;

/**
 *
 * @author pirul
 */
@WebServlet(name = "ContralodorCandidato", urlPatterns = {"/ContralodorCandidato"})
public class ContralodorCandidato extends HttpServlet {

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
//            out.println("<title>Servlet ContralodorCandidato</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ContralodorCandidato at " + request.getContextPath() + "</h1>");
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

        String id = request.getParameter("fIdCandidato");
        String idApren = request.getParameter("fIdAprendiz");
        String descripcionCandidato = request.getParameter("fDescripcionCandidato");
        String fotoCandidato = request.getParameter("fFotoCandidato");
        String accion = request.getParameter("CRUD");

        int idCandidato = 0;
        try {
            idCandidato = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
        }

        int idAprendiz = 0;
        try {
            idAprendiz = Integer.parseInt(idApren);
        } catch (NumberFormatException ex) {
        }

        Candidato candidato = new Candidato();
        Aprendiz aprendiz = new Aprendiz();

        candidato.setIdCandidato(idCandidato);

        aprendiz.setIdAprendiz(idAprendiz);
        candidato.setDescripcionCandidato(descripcionCandidato);
        candidato.setFotoCandidato(fotoCandidato);
        System.out.println("accion ---" + accion);
        String mensaje;
        switch (accion) {
            case "Registrar":
                mensaje = "Candidato Registrado";
                candidato.Insertar();
                request.getRequestDispatcher("/WEB-INF/ListarCandidato.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "Modificar":
                mensaje = "Candidato Modificado";
                candidato.Modificar();
                request.getRequestDispatcher("/WEB-INF/ListarCandidato.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "Eliminar":
                mensaje = "Candidato Eliminado";
                candidato.Eliminar();
                request.getRequestDispatcher("/WEB-INF/ListarCandidato.jsp?mensaje=" + mensaje).forward(request, response);
                break;

            case "infoCandidato":
                int idInfoCandidato = Integer.parseInt(request.getParameter("idCandidato"));
                System.out.println("idCandidato --- " + idInfoCandidato);
                candidato = candidato.infoCandidato(idInfoCandidato);

                Gson gson = new Gson();
                String json = gson.toJson(candidato);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().write(json);
                break;

            default:
                System.out.println("No hay opción en el controlador");
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
