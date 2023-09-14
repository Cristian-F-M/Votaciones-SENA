/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pirul
 */
public class TipoDocumento {

    private int idTipoDocumento;
    private String descripcionTipoDocumento;
    private int paginacion = 10;

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getDescripcionTipoDocumento() {
        return descripcionTipoDocumento;
    }

    public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
        this.descripcionTipoDocumento = descripcionTipoDocumento;
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
        final TipoDocumento other = (TipoDocumento) obj;
        return this.idTipoDocumento == other.idTipoDocumento;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" + "descripcionTipoDocumento=" + descripcionTipoDocumento + '}';
    }

    public ArrayList Listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        ArrayList tiposDocumento = new ArrayList();
        String sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idTipodocumento";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idTipoDocumento LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setIdTipoDocumento(rs.getInt("idTipoDocumento"));
                tipoDocumento.setDescripcionTipoDocumento(rs.getString("descripcionTipoDocumento"));
                tiposDocumento.add(tipoDocumento);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar tiposDocumento --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
        return tiposDocumento;
    }

    public void Insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            AutoIncrement();
            int filas = st.executeUpdate("INSERT INTO tipoDocumento VALUES(NULL,'" + this.getDescripcionTipoDocumento() + "')");
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Tipo de documento Insertado", "Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar tipoDocumento --- " + ex.getLocalizedMessage());
        }

        conexion.Desconectar();
    }

    public void Modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        try {
            int filas = st.executeUpdate("UPDATE tipoDocumento SET descripcionRol ='" + getDescripcionTipoDocumento() + "' WHERE idTipoDocumento= " + getIdTipoDocumento());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Tipo de documento Modificado","Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al modificar TipoDocumento --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
    }

    public void Eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("DELETE FROM tipoDocumento WHERE idRol =" + getIdTipoDocumento());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Tipo de documento Eliminado", "Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al eliminar tipoDocumento --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        int cantidadBloques = 0;

        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idTipoDocumento)/" + this.paginacion + ") AS cantidad FROM tipoDocumento");
            if (rs.next()) {
                cantidadBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener cantidad de tiposDocumento --- " + ex.getLocalizedMessage());
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
        }
    }
}
