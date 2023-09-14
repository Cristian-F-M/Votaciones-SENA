/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pirul
 */
public class Rol {

    private int idRol;
    private String descripcionRol;
    private int paginacion = 10;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
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
        final Rol other = (Rol) obj;
        return this.idRol == other.idRol;
    }

    @Override
    public String toString() {
        return "Rol{" + "descripcionRol=" + descripcionRol + '}';
    }

    public ArrayList Listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        ArrayList roles = new ArrayList();
        String sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idRol";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idRol LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setDescripcionRol(rs.getString("descripcionRol"));
                roles.add(rol);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar roles --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
        return roles;
    }

    public void Insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            AutoIncrement();
            int filas = st.executeUpdate("INSERT INTO Rol VALUES(NULL,'" + this.getDescripcionRol() + "')");
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Rol Insertado", "Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar rol --- " + ex.getLocalizedMessage());
        }

        conexion.Desconectar();
    }

    public void Modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        try {
            int filas = st.executeUpdate("UPDATE Rol SET descripcionRol ='" + getDescripcionRol() + "' WHERE idRol= " + getIdRol());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Rol Modificado", "Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al modificar Rol --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
    }

    public void Eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("DELETE FROM Rol WHERE idRol =" + getIdRol());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Rol Eliminado", "Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al eliminar Rol --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        int cantidadBloques = 0;

        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idRol)/" + this.paginacion + ") AS cantidad FROM Rol");
            if (rs.next()) {
                cantidadBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener cantidad de roles --- " + ex.getLocalizedMessage());
        }

        return cantidadBloques;
    }

//    public void InsertarAuditoria(String accion, String usuario) {
//        Conexion conexion = new Conexion();
//        Statement st = conexion.Conectar();
//
//        try {
//            st.executeUpdate("INSERT INTO auditoria VALUES(null, " + accion + ", " + usuario + ", NOW())");
//        } catch (SQLException ex) {
//            System.err.println("Error al insertar auditoria --- " + ex.getLocalizedMessage());
//        }
//    }
    public void AutoIncrement() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            st.executeUpdate("ALTER TABLE " + this.getClass().getSimpleName() + " AUTO_INCREMENT = 0");
        } catch (SQLException ex) {
            System.err.println("Error al auto incrementar auditoria --- " + ex.getLocalizedMessage());
        }
    }

}
