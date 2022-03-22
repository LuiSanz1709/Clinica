/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SL03483514
 */
public class Conexion {
     private static Connection cnx = null;
   public static Connection obtener() throws SQLException, ClassNotFoundException {
      if (cnx == null) {
         try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             //String connectionURL="jdbc:sqlserver://N023520\\MSSQLSERVER2:1433;username=sa;password=Luisan17-;databaseName=clinica";
             String connectionURL="jdbc:sqlserver://DESKTOP-BOVSE76\\MSSQLSERVER:1433;username=sa;password=Luisan17-;databaseName=clinica";
             //String connectionURL="jdbc:sqlserver://localhost\\MSSQLSERVER:1433;username=sa;password=Luisan17-;databaseName=clinica";
             //String connectionUrl = "jdbc:sqlserver://localhost:1433;username=sa;password=Luisan17-;databaseName=clinica";

             cnx = DriverManager.getConnection(connectionURL);
             System.out.println("Nos Conectamos");
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
      return cnx;
   
}



   public static void cerrar() throws SQLException {
      if (cnx != null) {
         cnx.close();
      }
   }
}
