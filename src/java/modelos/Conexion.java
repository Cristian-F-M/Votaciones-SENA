/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author pirul
 */
public class Conexion {
    
    Connection conexion = null;
    
    
    public Statement Conectar(){
        Statement st = null;
        
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/BDProyecto");
            conexion = ds.getConnection("proyecto", "1234");
            st = conexion.createStatement();
        }catch(NamingException ex){
                System.err.println("Error al iniciar contexto ---" + ex.getLocalizedMessage());
        }catch(SQLException ex){
            System.err.println("Error al conectarse a la base de datos --- " + ex.getLocalizedMessage());
        }
        return st;
    }
    
    public void Desconectar(){
        try{
            conexion.close();
        }catch(SQLException ex){
            System.err.println("Error al desconectarse de la base de datos --- " + ex.getLocalizedMessage());
        }
    }
}
