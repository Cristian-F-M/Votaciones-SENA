/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author pirul
 */
public class Aprendiz {

    private Integer idAprendiz;
    private String nombreAprendiz;
    private int tipoDocumentoAprendiz;
    private String documentoAprendiz;
    private String correoAprendiz;
    private String contraseñaAprendiz;
    private int candidato;
    private static Aprendiz aprendizIniciado;
    private int paginacion = 10;

    public Integer getIdAprendiz() {
        return idAprendiz;
    }

    public void setIdAprendiz(int idAprendiz) {
        this.idAprendiz = idAprendiz;
    }

    public String getNombreAprendiz() {
        return nombreAprendiz;
    }

    public void setNombreAprendiz(String nombreAprendiz) {
        this.nombreAprendiz = nombreAprendiz;
    }

    public int getTipoDocumentoAprendiz() {
        return tipoDocumentoAprendiz;
    }

    public void setTipoDocumentoAprendiz(int tipoDocumentoAprendiz) {
        this.tipoDocumentoAprendiz = tipoDocumentoAprendiz;
    }

    public String getDocumentoAprendiz() {
        return documentoAprendiz;
    }

    public void setDocumentoAprendiz(String documentoAprendiz) {
        this.documentoAprendiz = documentoAprendiz;
    }

    public String getCorreoAprendiz() {
        return correoAprendiz;
    }

    public void setCorreoAprendiz(String correoAprendiz) {
        this.correoAprendiz = correoAprendiz;
    }

    public String getContraseñaAprendiz() {
        return contraseñaAprendiz;
    }

    public void setContraseñaAprendiz(String contraseñaAprendiz) {
        this.contraseñaAprendiz = contraseñaAprendiz;
    }

    public int getCandidato() {
        return candidato;
    }

    public void setCandidato(int candidato) {
        this.candidato = candidato;
    }

    public static Aprendiz getAprendizIniciado() {
        return aprendizIniciado;
    }

    public static void setAprendizIniciado(Aprendiz aprendizIniciado) {
        Aprendiz.aprendizIniciado = aprendizIniciado;
    }

    public int getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(int paginacion) {
        this.paginacion = paginacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aprendiz other = (Aprendiz) obj;
        return Objects.equals(this.idAprendiz, other.idAprendiz);
    }

    @Override
    public String toString() {
        return "Aprendiz{" + "nombreAprendiz=" + nombreAprendiz + '}';
    }

    public ArrayList Listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        ArrayList aprendices = new ArrayList();
        String sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idAprendiz";

        if (pagina < 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idPelicula LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Aprendiz aprendiz = new Aprendiz();
                aprendiz.setIdAprendiz(rs.getInt("idAprendiz"));
                aprendiz.setNombreAprendiz(rs.getString("nombreAprendiz"));
                aprendiz.setTipoDocumentoAprendiz(rs.getInt("tipoDocumentoAprendiz"));
                aprendiz.setDocumentoAprendiz(rs.getString("documentoAprendiz"));
                aprendiz.setCorreoAprendiz(rs.getString("correoAprendiz"));
                aprendiz.setContraseñaAprendiz(rs.getString("contraseñaAprendiz"));
                aprendices.add(aprendiz);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar aprendices --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }

        return aprendices;
    }

    public void Insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        String contraseñaHashAprendiz = BCrypt.hashpw(contraseñaAprendiz, BCrypt.gensalt());
        try {
            AutoIncrement();
            int filas = st.executeUpdate("INSERT INTO Aprendiz VALUES(NULL,'" + this.getNombreAprendiz() + "'," + this.getTipoDocumentoAprendiz() + ", '" + this.getDocumentoAprendiz() + "', '" + this.getCorreoAprendiz() + "','" + contraseñaHashAprendiz + "', null)");
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Aprendiz Insertado", "Aprendiz");
            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar Aprendiz --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }

    }

    public void Modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        try {
            int filas = st.executeUpdate("UPDATE Aprendiz SET nombreAprendiz ='" + getNombreAprendiz() + "', tipoDocumentoAprendiz  ='" + getTipoDocumentoAprendiz() + "', documentoAprendiz ='" + getDocumentoAprendiz() + "', contraseñaAprendiz ='" + getContraseñaAprendiz() + "' WHERE idAprendiz = " + getIdAprendiz());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Aprendiz modificado", getNombreAprendiz());
            }
        } catch (SQLException ex) {
            System.err.println("Error al modificar Aprendiz --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }
    }

    public void Eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("DELETE FROM Aprendiz WHERE idAprendiz =" + getIdAprendiz());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Aprendiz Eliminado", "Administrador");
            }
        } catch (SQLException ex) {
            System.err.println("Error al eliminar Aprendiz --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }
    }

    public boolean IniciarSesion() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        String sql = "SELECT * FROM aprendiz WHERE tipoDocumentoAprendiz = " + getTipoDocumentoAprendiz() + " AND documentoAprendiz = '" + getDocumentoAprendiz() + "'";
        try {
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String contraseña = rs.getString("contraseñaAprendiz");

//Prueba de SQL-injection
//                System.out.println("true: " + rs.getString("nombreAprendiz"));                
//                Aprendiz aprendiz = new Aprendiz();
//                aprendiz.setIdAprendiz(rs.getInt("idAprendiz"));
//                aprendiz.setNombreAprendiz(rs.getString("nombreAprendiz"));
//                Aprendiz.setAprendizIniciado(aprendiz);
//                return true;
//Fin de prueba de SQL-injection
                if (BCrypt.checkpw(getContraseñaAprendiz(), contraseña)) {
                    Aprendiz aprendiz = new Aprendiz();
                    aprendiz.setIdAprendiz(rs.getInt("idAprendiz"));
                    aprendiz.setNombreAprendiz(rs.getString("nombreAprendiz"));
                    aprendiz.setTipoDocumentoAprendiz(rs.getInt("tipoDocumentoAprendiz"));
                    aprendiz.setDocumentoAprendiz(rs.getString("documentoAprendiz"));
                    aprendiz.setCorreoAprendiz(rs.getString("correoAprendiz"));
                    aprendiz.setCandidato(rs.getInt("candidato"));
                    Aprendiz.setAprendizIniciado(aprendiz);
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al iniciar Sesion --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }
        return false;
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        int cantidadBloques = 0;

        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idAprendiz)/" + this.paginacion + ") AS cantidad FROM Aprendiz");
            if (rs.next()) {
                cantidadBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener cantidad de peliculas --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }

        return cantidadBloques;
    }

    public void AutoIncrement() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            st.executeUpdate("ALTER TABLE " + this.getClass().getSimpleName() + " AUTO_INCREMENT = 0");
        } catch (SQLException ex) {
            System.err.println("Error al auto incrementar auditoria --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }
    }

    public boolean verificarDocumento() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        String sql = "SELECT * FROM aprendiz WHERE documentoAprendiz LIKE '" + getDocumentoAprendiz() + "'";
        try {
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Error al verificar documento --- " + ex);
        } finally {
            conexion.Desconectar();
        }

        return false;
    }

    public boolean verificarCorreo() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        String sql = "SELECT * FROM aprendiz WHERE correoAprendiz LIKE '" + getCorreoAprendiz() + "'";
        try {
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Error al verificar documento --- " + ex);
        } finally {
            conexion.Desconectar();
        }

        return false;
    }

    public void Votar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        String sql = "UPDATE aprendiz SET candidato = " + getCandidato() + " WHERE idAprendiz = " + aprendizIniciado.getIdAprendiz();

        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error al votar --- " + ex);
        } finally {
            conexion.Desconectar();
        }
    }

    public int verificarVoto(int idAprendiz) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        String sql = "SELECT * FROM aprendiz WHERE idAprendiz = " + idAprendiz;

        try {
            ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);

            if (rs.next()) {
                System.out.println(rs.getInt("candidato"));
                return rs.getInt("candidato");
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar el voto --- " + ex);
        } finally {
            conexion.Desconectar();
        }

        return 0;

    }

}
