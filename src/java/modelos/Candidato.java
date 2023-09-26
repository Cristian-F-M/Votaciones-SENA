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
    private Aprendiz aprendiz;
    private String descripcionCandidato;
    private String fotoCandidato;
    private int paginacion = 10;

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
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
        return aprendiz.getNombreAprendiz();
    }

    public ArrayList Listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        ArrayList candidatos = new ArrayList();
        Candidato candidato;
        String sql = "SELECT * FROM " + this.getClass().getSimpleName() + " INNER JOIN aprendiz ON aprendiz = idAprendiz ORDER BY idCandidato ";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionmin = paginacionMax - this.paginacion;
            sql = "SELECT * FROM " + this.getClass().getSimpleName() + "ORDER BY idCAndidato LIMIT " + paginacionmin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                candidato = new Candidato();
                aprendiz = new Aprendiz();

                candidato.setIdCandidato(rs.getInt("idCandidato"));
                aprendiz.setIdAprendiz(rs.getInt("idAprendiz"));
                aprendiz.setNombreAprendiz(rs.getString("nombreAprendiz"));
                aprendiz.setTipoDocumentoAprendiz(rs.getInt("tipoDocumentoAprendiz"));
                aprendiz.setDocumentoAprendiz(rs.getString("documentoAprendiz"));
                aprendiz.setCorreoAprendiz(rs.getString("correoAprendiz"));

                candidato.setAprendiz(aprendiz);
                candidato.setDescripcionCandidato(rs.getString("descripcionCandidato"));
                candidato.setFotoCandidato(rs.getString("fotoCandidato"));
                candidatos.add(candidato);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar candidatos --- " + ex.getLocalizedMessage());
        }

        if (candidatos.isEmpty()) {
            candidato = new Candidato();
            aprendiz = new Aprendiz();

            aprendiz.setIdAprendiz(0);
            aprendiz.setNombreAprendiz("no hay candidatos");
            candidato.setAprendiz(aprendiz);
            candidato.setFotoCandidato("archivos/images/Triste!.jpg");
            candidatos.add(candidato);
        }

        conexion.Desconectar();
        return candidatos;
    }

    public void Insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();

        try {
            AutoIncrement();
            int filas = st.executeUpdate("INSERT INTO " + this.getClass().getSimpleName() + " VALUES(NULL, '" + getAprendiz().getIdAprendiz() + "', '" + getDescripcionCandidato() + "', '" + getFotoCandidato() + "')");
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
            int filas = st.executeUpdate("UPDATE candidato SET descripcionCandidato = '" + getDescripcionCandidato() + "', fotoCandidato = '" + getFotoCandidato() + "' WHERE idCandidato = " + getIdCandidato());
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

    public Candidato infoCandidato(int idCandidato) {
        Conexion conexion = new Conexion();
        Statement st = conexion.Conectar();
        String sql = "SELECT * FROM candidato INNER JOIN aprendiz ON aprendiz = idAprendiz where idCandidato = " + idCandidato;
        try {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                Candidato candidato = new Candidato();
                Aprendiz candidatoAprendiz = new Aprendiz();

                candidato.setIdCandidato(rs.getInt("idCandidato"));

                candidatoAprendiz.setIdAprendiz(rs.getInt("idAprendiz"));
                candidatoAprendiz.setNombreAprendiz(rs.getString("nombreAprendiz"));
                candidatoAprendiz.setDocumentoAprendiz(rs.getString("documentoAprendiz"));
                candidatoAprendiz.setCorreoAprendiz(rs.getString("correoAprendiz"));

                candidato.setAprendiz(candidatoAprendiz);
                candidato.setDescripcionCandidato(rs.getString("descripcionCandidato"));
                candidato.setFotoCandidato(rs.getString("fotoCandidato"));
                return candidato;
            }

        } catch (SQLException ex) {
            System.err.println("Error al buscar aprendiz seleccionado --- " + ex.getLocalizedMessage());
        }

        Candidato candidato = new Candidato();

        candidato.setIdCandidato(0);
        candidato.setDescripcionCandidato("No hay información");
        candidato.setFotoCandidato("images/Triste!.jpg");

        return candidato;

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
