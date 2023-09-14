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
public class Auditoria {

    private int idAuditoria;
    private String detalleAuditoria;
    private String nombreAuditoria;
    private String fechaAuditoria;
    private int paginacion = 10;

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getDetalleAuditoria() {
        return detalleAuditoria;
    }

    public void setDetalleAuditoria(String detalleAuditoria) {
        this.detalleAuditoria = detalleAuditoria;
    }

    public String getNombreAuditoria() {
        return nombreAuditoria;
    }

    public void setNombreAuditoria(String nombreAuditoria) {
        this.nombreAuditoria = nombreAuditoria;
    }

    public String getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(String fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public int getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(int paginacion) {
        this.paginacion = paginacion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Auditoria other = (Auditoria) obj;
        return this.idAuditoria == other.idAuditoria;
    }

    @Override
    public String toString() {
        return "Auditoria{" + "detalleAuditoria=" + detalleAuditoria + '}';
    }

    public ArrayList Listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        ArrayList auditorias = new ArrayList();
        Auditoria auditoria;
        String sql = "SELECT * FROM " + this.getClass().getSimpleName() + "ORDER BY idAuditoria";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idAuditoria LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                auditoria = new Auditoria();

                auditoria.setIdAuditoria(rs.getInt("idAuditoria"));
                auditoria.setDetalleAuditoria(rs.getString("detalleAuditoria"));
                auditoria.setNombreAuditoria(rs.getString("nombreUsuarioAuditoria"));
                auditoria.setFechaAuditoria(rs.getString("fechaAuditoria"));
                auditorias.add(auditoria);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar auditorias --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
        return auditorias;
    }

    public void InsertarAuditoria(String accion, String usuario) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            st.executeUpdate("INSERT INTO auditoria VALUES(null, '" + accion + "', '" + usuario + "', NOW())");
        } catch (SQLException ex) {
            System.err.println("Error al insertar auditoria --- " + ex.getLocalizedMessage());
        }
    }

}
