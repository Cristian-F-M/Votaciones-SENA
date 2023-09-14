/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Date;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pirul
 */
public class Votacion {

    private int idVotacion;
    private Date fechaInicioVotacion;
    private Date fechaFinVotacion;
    private int cantVotosVotacion;
    private int ganadorVotacion;
    private int paginacion = 10;

    public int getIdVotacion() {
        return idVotacion;
    }

    public void setIdVotacion(int idVotacion) {
        this.idVotacion = idVotacion;
    }

    public Date getFechaInicioVotacion() {
        return fechaInicioVotacion;
    }

    public void setFechaInicioVotacion(Date fechaInicioVotacion) {
        this.fechaInicioVotacion = fechaInicioVotacion;
    }

    public Date getFechaFinVotacion() {
        return fechaFinVotacion;
    }

    public void setFechaFinVotacion(Date fechaFinVotacion) {
        this.fechaFinVotacion = fechaFinVotacion;
    }

    public int getCantVotosVotacion() {
        return cantVotosVotacion;
    }

    public void setCantVotosVotacion(int cantVotosVotacion) {
        this.cantVotosVotacion = cantVotosVotacion;
    }

    public int getGanadorVotacion() {
        return ganadorVotacion;
    }

    public void setGanadorVotacion(int ganadorVotacion) {
        this.ganadorVotacion = ganadorVotacion;
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
        final Votacion other = (Votacion) obj;
        return this.idVotacion == other.idVotacion;
    }

    @Override
    public String toString() {
        return "Votacion{" + "fechaInicioVotacion=" + fechaInicioVotacion + '}';
    }

    public ArrayList Listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        ArrayList votaciones = new ArrayList();
        Votacion votacion;
        String sql = "SELECT * FROM " + this.getClass().getSimpleName() + "ORDER BY idVotacion";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idVotacion LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                votacion = new Votacion();

                votacion.setIdVotacion(rs.getInt("idVotacion"));
                votacion.setFechaInicioVotacion(rs.getDate("fechaInicioVotacion"));
                votacion.setFechaFinVotacion(rs.getDate("fechaFinVotacion"));
                votacion.setCantVotosVotacion(rs.getInt("cantVotosVotacion"));
                votacion.setGanadorVotacion(rs.getInt("ganadorVotacion"));
                votaciones.add(votacion);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar votaciones --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
        return votaciones;
    }

    public void Insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            AutoIncrement();
            int filas = st.executeUpdate("INSERT INTO " + this.getClass().getSimpleName() + "VALUES(NULL, " + getFechaInicioVotacion() + ", " + getFechaFinVotacion() + ", '" + getCantVotosVotacion() + "', '" + getGanadorVotacion() + "')");
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Votacion Insertada", "Administrador/Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar votacion --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
    }

    public void Modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("UPDATE votacion SET fechaInicioVotacion = " + getFechaInicioVotacion() + ", fechaFinVotacion = " + getFechaInicioVotacion() + ", cantVotosVotacion = " + getCantVotosVotacion() + ", ganadorVotacion = " + getGanadorVotacion() + " WHERE idVotacion = " + getIdVotacion());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Votacion Modificada", "Administrador/Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al modificar votacion ---" + ex.getLocalizedMessage());
        }

        conexion.Desconectar();
    }

    public void Eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("DELETE FROM " + this.getClass().getSimpleName() + " WHERE idVotacion =" + getIdVotacion());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Votacion eliminada", "Administrador/Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al eliminar votacion --- " + ex.getLocalizedMessage());
        }
        conexion.Desconectar();
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
