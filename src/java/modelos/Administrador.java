/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author pirul
 */
public class Administrador {

    private int idAdministrador;
    private String nombreAdministrador;
    private int tipoDocumentoAdministrador;
    private String documentoAdministrador;
    private String correoAdministrador;
    private String contraseñaAdministrador;
    private int rolAdministrador;
    private int paginacion;

    public Administrador() {
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public int getTipoDocumentoAdministrador() {
        return tipoDocumentoAdministrador;
    }

    public void setTipoDocumentoAdministrador(int tipoDocumentoAdministrador) {
        this.tipoDocumentoAdministrador = tipoDocumentoAdministrador;
    }

    public String getDocumentoAdministrador() {
        return documentoAdministrador;
    }

    public void setDocumentoAdministrador(String documentoAdministrador) {
        this.documentoAdministrador = documentoAdministrador;
    }

    public String getCorreoAdministrador() {
        return correoAdministrador;
    }

    public void setCorreoAdministrador(String correoAdministrador) {
        this.correoAdministrador = correoAdministrador;
    }

    public String getContraseñaAdministrador() {
        return contraseñaAdministrador;
    }

    public void setContraseñaAdministrador(String contraseñaAdministrador) {
        String contraseñaHash = BCrypt.hashpw(contraseñaAdministrador, BCrypt.gensalt());
        this.contraseñaAdministrador = contraseñaHash;
    }

    public int getRolAdministrador() {
        return rolAdministrador;
    }

    public void setRolAdministrador(int rolAdministrador) {
        this.rolAdministrador = rolAdministrador;
    }

    public int getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(int paginacion) {
        this.paginacion = paginacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Administrador other = (Administrador) obj;
        return this.idAdministrador == other.idAdministrador;
    }

    @Override
    public String toString() {
        return "Administrador{" + "nombreAdministrador=" + nombreAdministrador + '}';
    }

    public ArrayList Listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        ArrayList administradores = new ArrayList();
        Administrador administrador;

        String sql = "SELECT * FROM administradores ORDER BY idAdministrador";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM administrador ORDER BY idAdministrador LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                administrador = new Administrador();
                administrador.setIdAdministrador(rs.getInt("idAdministrador"));
                administrador.setNombreAdministrador(rs.getString("nombreAdministrador"));
                administrador.setTipoDocumentoAdministrador(rs.getInt("tipoDocumentoAdministrador"));
                administrador.setDocumentoAdministrador(rs.getString("documentoAdministrador"));
                administrador.setCorreoAdministrador(rs.getString("correoAdministrador"));
                administrador.setContraseñaAdministrador(rs.getString("contraseñaAdministrador"));
                administrador.setRolAdministrador(rs.getInt("rolAdministrador"));
                administradores.add(administrador);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar administradores --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }
        return administradores;
    }

    public void Insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            AutoIncrement();
            int filas = st.executeUpdate("INSERT INTO administrador VALUES(" + getIdAdministrador() + ",'" + getNombreAdministrador() + "', " + getTipoDocumentoAdministrador() + ", '" + getDocumentoAdministrador() + "', '" + getCorreoAdministrador() + "', '" + getContraseñaAdministrador() + "', " + getRolAdministrador() + ")");
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Administrador Insertado", "Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar administrador --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }
    }

    public void Modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("UPDATE administrador SET nombreAdministrador = '" + getNombreAdministrador() + "', tipoDocumentoAdministrador = " + getTipoDocumentoAdministrador() + ", documentoAdministrador = " + getDocumentoAdministrador() + ", correoAdministrador = " + getCorreoAdministrador() + ", contraseñaAdministrador = " + getContraseñaAdministrador() + ", rolAdministrador = " + getRolAdministrador() + "WHERE idAdministrador =" + getIdAdministrador());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Administrador Modificado", "Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al modificar administrador --- " + ex.getLocalizedMessage());
        }
    }

    public void Eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("DELETE FROM administrador WHERE idAdministrador =" + getIdAdministrador());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Administrador Eliminado", "Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al eliminar administrador --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }

    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        int cantidadBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idAdministrador)/" + this.paginacion + ") AS cantidad FROM administrador");
            if (rs.next()) {
                cantidadBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener cantidad paginas adminitrador ---" + ex.getLocalizedMessage());
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

}
