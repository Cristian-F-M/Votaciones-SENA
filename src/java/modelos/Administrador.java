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
    private TipoDocumento tipoDocumentoAdministrador;
    private String documentoAdministrador;
    private String correoAdministrador;
    private Rol rolAdministrador;
    private String contraseñaAdministrador;
    private static Administrador adminitradorIniciado;

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

    public TipoDocumento getTipoDocumentoAdministrador() {
        return tipoDocumentoAdministrador;
    }

    public void setTipoDocumentoAdministrador(TipoDocumento tipoDocumentoAdministrador) {
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
        this.contraseñaAdministrador = contraseñaAdministrador;
    }

    public Rol getRolAdministrador() {
        return rolAdministrador;
    }

    public void setRolAdministrador(Rol rolAdministrador) {
        this.rolAdministrador = rolAdministrador;
    }

    public int getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(int paginacion) {
        this.paginacion = paginacion;
    }

    public static Administrador getAdminitradorIniciado() {
        return adminitradorIniciado;
    }

    public static void setAdminitradorIniciado(Administrador adminitradorIniciado) {
        Administrador.adminitradorIniciado = adminitradorIniciado;
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

        String sql = "SELECT * FROM administrador INNER JOIN tipoDocumento "
                + "on tipoDocumentoAdministrador = idTipoDocumento INNER JOIN rol on rolAdministrador = idRol  ORDER BY idAdministrador";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM administrador ORDER BY idAdministrador LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                administrador = new Administrador();
                TipoDocumento tipoDocumento = new TipoDocumento();
                Rol rol = new Rol();

                administrador.setIdAdministrador(rs.getInt("idAdministrador"));
                administrador.setNombreAdministrador(rs.getString("nombreAdministrador"));

                tipoDocumento.setIdTipoDocumento(rs.getInt("tipoDocumentoAdministrador"));
                tipoDocumento.setDescripcionTipoDocumento(rs.getString("descripcionTipoDocumento"));
                administrador.setTipoDocumentoAdministrador(tipoDocumento);

                administrador.setDocumentoAdministrador(rs.getString("documentoAdministrador"));
                administrador.setCorreoAdministrador(rs.getString("correoAdministrador"));
                administrador.setContraseñaAdministrador(rs.getString("contraseñaAdministrador"));

                rol.setIdRol(rs.getInt("idRol"));
                rol.setDescripcionRol(rs.getString("descripcionRol"));
                administrador.setRolAdministrador(rol);

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
            
            String contraseñaAdministradorHash = BCrypt.hashpw(getContraseñaAdministrador(), BCrypt.gensalt());

            AutoIncrement();
            String sql = "INSERT INTO administrador VALUES(" + getIdAdministrador() + ",'" + getNombreAdministrador() + "', " + getTipoDocumentoAdministrador().getIdTipoDocumento() + ", '" + getDocumentoAdministrador() + "', '" + getCorreoAdministrador() + "', '" + contraseñaAdministradorHash + "', " + getRolAdministrador().getIdRol() + ")";
           
            int filas = st.executeUpdate(sql);
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
            String sql = "UPDATE administrador SET nombreAdministrador = '" + getNombreAdministrador() + "', "
                    + "tipoDocumentoAdministrador = " + getTipoDocumentoAdministrador().getIdTipoDocumento() + ", "
                    + "documentoAdministrador = '" + getDocumentoAdministrador() + "', "
                    + "correoAdministrador = '" + getCorreoAdministrador() + "', "
                    + "rolAdministrador = " + getRolAdministrador().getIdRol() + " WHERE idAdministrador =" + getIdAdministrador();
            int filas = st.executeUpdate(sql);
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

    public boolean IniciarSesion() {

        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        String sql = "SELECT * FROM " + this.getClass().getSimpleName() + " INNER JOIN tipoDocumento ON tipoDocumentoAdministrador = idTipoDocumento INNER JOIN rol ON rolAdministrador = idRol WHERE tipoDocumentoAdministrador = " + getTipoDocumentoAdministrador().getIdTipoDocumento() + " AND documentoAdministrador = '" + getDocumentoAdministrador() + "'";
        try {
//            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                String contraseñaAdmin = rs.getString("contraseñaAdministrador");
                
                if (BCrypt.checkpw(getContraseñaAdministrador(), contraseñaAdmin)) {
                    Administrador administrador = new Administrador();
                    TipoDocumento tipoDocumento = new TipoDocumento();
                    Rol rol = new Rol();

                    administrador.setIdAdministrador(rs.getInt("idAdministrador"));
                    administrador.setNombreAdministrador(rs.getString("nombreAdministrador"));

                    tipoDocumento.setIdTipoDocumento(rs.getInt("tipoDocumentoAdministrador"));
                    tipoDocumento.setDescripcionTipoDocumento(rs.getString("descripcionTipoDocumento"));
                    administrador.setTipoDocumentoAdministrador(tipoDocumento);

                    administrador.setDocumentoAdministrador(rs.getString("documentoAdministrador"));
                    administrador.setCorreoAdministrador(rs.getString("correoAdministrador"));

                    rol.setIdRol(rs.getInt("idRol"));
                    rol.setDescripcionRol(rs.getString("descripcionRol"));
                    administrador.setRolAdministrador(rol);

                    administrador.setContraseñaAdministrador(rs.getString("contraseñaAdministrador"));

                    Administrador.setAdminitradorIniciado(administrador);
                    return true;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Error al iniciar session administrador --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }
        return false;
    }

    public Administrador infoAdministrador(int idAdministrador) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        Administrador administrador;
        String sql = "SELECT idAdministrador, nombreAdministrador, idTipoDocumento, descripcionTipoDocumento, documentoAdministrador, "
                + "correoAdministrador, idRol, descripcionRol FROM administrador INNER JOIN tipoDocumento on tipoDocumentoAdministrador = "
                + "idTipoDocumento INNER JOIN rol on rolAdministrador = idRol WHERE idAdministrador = " + idAdministrador + "  ORDER BY idAdministrador";
        try {
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                administrador = new Administrador();
                TipoDocumento tipoDocumento = new TipoDocumento();
                Rol rol = new Rol();

                administrador.setIdAdministrador(rs.getInt("idAdministrador"));
                administrador.setNombreAdministrador(rs.getString("nombreAdministrador"));

                tipoDocumento.setIdTipoDocumento(rs.getInt("idTipoDocumento"));
                tipoDocumento.setDescripcionTipoDocumento(rs.getString("descripcionTipoDocumento"));
                administrador.setTipoDocumentoAdministrador(tipoDocumento);

                administrador.setDocumentoAdministrador(rs.getString("documentoAdministrador"));
                administrador.setCorreoAdministrador(rs.getString("correoAdministrador"));

                rol.setIdRol(rs.getInt("idRol"));
                rol.setDescripcionRol(rs.getString("descripcionRol"));

                administrador.setRolAdministrador(rol);

                return administrador;
            }

        } catch (SQLException ex) {
            System.err.println("Error al listar la información del administrador --- " + ex.getLocalizedMessage());
        } finally {
            conexion.Desconectar();
        }

        administrador = new Administrador();
        administrador.setNombreAdministrador("No hay información del administrador");
        return administrador;
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

    public boolean VerificarDocumentoAdministrador(String documentoAdministrador) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        String sql = "SELECT idAdministrador, documentoAdministrador FROM administrador WHERE documentoAdministrador = '" + documentoAdministrador + "'";
        try {
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Error al verificar documento administrador --- " + ex);
        } finally {
            conexion.Desconectar();
        }

        return false;
    }

    public boolean VerificarCorreoAdministrador(String documentoAdministrador) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        String sql = "SELECT idAdministrador, correoAdministrador FROM administrador WHERE correAdministrador = " + getCorreoAdministrador();

        try {
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            System.err.println("Error al verificar correo administrador --- " + ex);
        } finally {
            conexion.Desconectar();
        }

        return false;
    }
}
