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
public class Candidato {

    private int idCandidato;
    private String nombreCandidato;
    private String descripcionCandidato;
    private String fotoCandidato;
    private int paginacion = 10;

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNombreCandidato() {
        return nombreCandidato;
    }

    public void setNombreCandidato(String nombreCandidato) {
        this.nombreCandidato = nombreCandidato;
    }

    public String getDescripcionCandidato() {
        return descripcionCandidato;
    }

    public void setDescripcionCandidato(String descripcionCandidato) {
        this.descripcionCandidato = descripcionCandidato;
    }

    public String getFotoCandidato() {
        return fotoCandidato;
    }

    public void setFotoCandidato(String fotoCandidato) {
        this.fotoCandidato = fotoCandidato;
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
        final Candidato other = (Candidato) obj;
        return this.idCandidato == other.idCandidato;
    }

    @Override
    public String toString() {
        return "Candidato{" + "nombreCandidato=" + nombreCandidato + '}';
    }

    public ArrayList Listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        ArrayList candidatos = new ArrayList();
        Candidato candidato;
        String sql = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idCandidato";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionmin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM " + this.getClass().getSimpleName() + "ORDER BY idCAndidato LIMIT " + paginacionmin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                candidato = new Candidato();
                candidato.setIdCandidato(rs.getInt("idCandidato"));
                candidato.setNombreCandidato(rs.getString("nombreCandidato"));
                candidato.setDescripcionCandidato(rs.getString("descripcionCandidato"));
                candidato.setFotoCandidato(rs.getString("fotoCandidato"));
                candidatos.add(candidato);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar candidatos --- " + ex.getLocalizedMessage());
        }

        conexion.Desconectar();
        return candidatos;
    }

    public void Insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            AutoIncrement();
            int filas = st.executeUpdate("INSERT INTO " + this.getClass().getSimpleName() + " VALUES(NULL, '" + getNombreCandidato() + "', '" + getDescripcionCandidato() + "', '" + getFotoCandidato() + "')");
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Candidato Insertado", "Administrador/Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar candidato --- " + ex.getLocalizedMessage());
        }

        conexion.Desconectar();
    }

    public void Modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("UPDATE candidato SET nombreCandidato ='" + getNombreCandidato() + "', descripcionCandidato = '" + getDescripcionCandidato() + "', fotoCandidato = '" + getFotoCandidato() + "' WHERE idCandidato = " + getIdCandidato());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Candidato modificado", "Administrador/Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al modificar candidato --- " + ex.getLocalizedMessage());
        }

        conexion.Desconectar();
    }

    public void Eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            int filas = st.executeUpdate("DELETE FROM " + this.getClass().getSimpleName() + " WHERE idCandidato = " + getIdCandidato());
            if (filas > 0) {
                Auditoria auditoria = new Auditoria();
                auditoria.InsertarAuditoria("Candidato Eliminado", "Administrador/Owner");
            }
        } catch (SQLException ex) {
            System.err.println("Error al eliminar candidato --- " + ex.getLocalizedMessage());
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
