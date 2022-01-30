/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;
import Connection.Conexion;
import Modelos.Articulo;
import Modelos.DetalleVenta;
import Modelos.Paciente;
import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author SL03483514
 */
public class Servicios {
    private final String tabla = "Usuario";
   //*******************************************************  LOGIN  ********************************************************************************************************************************* 
   public Usuario login(Connection conexion,String usuario)  throws SQLException{
         try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM " + this.tabla + " WHERE usuario = ?" );
         consulta.setString(1, usuario);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Usuario u= new Usuario();
             u.setUsuario(resultado.getString("usuario"),resultado.getString("password"));
            // System.out.println(u.toString());
           return u;
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return null;
        
    }
  //*******************************************************  USUARIO  ********************************************************************************************************************************* 
   
   public void guardar(Connection conexion, Usuario usuario) throws SQLException{
      try{
         PreparedStatement consulta;
         if(usuario.getID() == null){
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(usuario, password) VALUES(?, ?)");
            consulta.setString(1, usuario.getUsuario());
            consulta.setString(2, usuario.getPassword());
         }else{
            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET titulo = ?, descripcion = ?, nivel_de_prioridad = ? WHERE id_tarea = ?");
            consulta.setString(1, usuario.getUsuario());
            consulta.setString(2, usuario.getPassword());
            consulta.setInt(4, usuario.getID());
         }
         System.out.print(consulta.executeUpdate());
         
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
   public Usuario recuperarPorId(Connection conexion, int id) throws SQLException {
      Usuario usuario = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT titulo, descripcion, nivel_de_prioridad FROM " + this.tabla + " WHERE id_tarea = ?" );
         consulta.setInt(1, id);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            usuario = new Usuario(id, resultado.getString("Usuario"), resultado.getString("Password"));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return usuario;
   }
   
   public void eliminar(Connection conexion, Usuario usuario) throws SQLException{
      try{
         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_tarea = ?");
         consulta.setInt(1, usuario.getID());
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
   public DefaultTableModel recuperarTodas(Connection conexion) throws SQLException{
      DefaultTableModel pacientes=new DefaultTableModel();
      pacientes.addColumn("id");
      pacientes.addColumn("usuario");
      pacientes.addColumn("password");
           
      //List<Usuario> usuarios = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM " + this.tabla);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[3];
             d[0]=resultado.getInt("id");
             d[1]=resultado.getString("usuario");
             d[2]= resultado.getString("password");
             pacientes.addRow(d);
             /*
             Object d[] = new Object[3];
             for (int i=0;i<3;i++){
                 d[i]=resultado.getString(i+1);
             }*/
            //usuarios.add(new Usuario(resultado.getInt("id"), resultado.getString("usuario"), resultado.getString("password")));
            //System.out.println(d[0]+" "+d[1]+"");
            //pacientes.addRow(usuarios[1]);
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return pacientes;
   }
   
     public DefaultTableModel getTable(){
      DefaultTableModel tabla=new DefaultTableModel();
      tabla.addColumn("id");
      tabla.addColumn("usuario");
      tabla.addColumn("password");
      return tabla;
   }
//*******************************************************  PACIENTES  ********************************************************************************************************************************* 
   
   
     public DefaultTableModel GetPacientes(Connection conexion) throws SQLException{
      DefaultTableModel pacientes=new DefaultTableModel();
      pacientes.addColumn("id");
      pacientes.addColumn("nombre");
      pacientes.addColumn("telefono");
      pacientes.addColumn("edad");
      pacientes.addColumn("direccion");
           
      //List<Usuario> usuarios = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Paciente");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[5];
             d[0]=resultado.getInt("id");
             d[1]=resultado.getString("nombre");
             d[2]= resultado.getString("telefono");
             d[3]= resultado.getInt("edad");
             d[4]= resultado.getString("direccion");
             pacientes.addRow(d);
             /*
             Object d[] = new Object[3];
             for (int i=0;i<3;i++){
                 d[i]=resultado.getString(i+1);
             }*/
            //usuarios.add(new Usuario(resultado.getInt("id"), resultado.getString("usuario"), resultado.getString("password")));
            //System.out.println(d[0]+" "+d[1]+"");
            //pacientes.addRow(usuarios[1]);
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return pacientes;
   }
       public void AddPaciente(Connection conexion, Paciente pac) throws SQLException{
      try{
         PreparedStatement consulta;
      
            consulta = conexion.prepareStatement("INSERT INTO Paciente (nombre, telefono, edad, direccion) VALUES(?, ?, ?, ?)");
            consulta.setString(1, pac.getPaciente());
            consulta.setString(2, pac.getTelefono());
            consulta.setInt(3, pac.getEdad());
            consulta.setString(4,pac.getDireccion());
        
         System.out.print(consulta.executeUpdate());
         
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
  
     
     //*******************************************************  ARTICULOS  ********************************************************************************************************************************* 
   
     
 public DefaultTableModel GetArticulos(Connection conexion) throws SQLException{
      DefaultTableModel articulos=new DefaultTableModel();
      articulos.addColumn("id");
      articulos.addColumn("Articulo");
      articulos.addColumn("ref");
      articulos.addColumn("precio");
      articulos.addColumn("descripcion");
           
      //List<Usuario> usuarios = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Articulo");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[5];
             d[0]=resultado.getInt("id");
             d[1]=resultado.getString("Articulo");
             d[2]= resultado.getString("ref");
             d[3]= resultado.getDouble("precio");
             d[4]= resultado.getString("descripcion");
             articulos.addRow(d);
             /*
             Object d[] = new Object[3];
             for (int i=0;i<3;i++){
                 d[i]=resultado.getString(i+1);
             }*/
            //usuarios.add(new Usuario(resultado.getInt("id"), resultado.getString("usuario"), resultado.getString("password")));
            //System.out.println(d[0]+" "+d[1]+"");
            //pacientes.addRow(usuarios[1]);
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return articulos;
   }
     
   
   public Articulo getArticulo(String a,Connection conexion) throws SQLException{
       Articulo art= null;
       PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Articulo WHERE ref = ?" );
       consulta.setString(1, a);
       ResultSet resultado = consulta.executeQuery();
       while(resultado.next()){
            art = new Articulo(resultado.getInt("id"), resultado.getString("Articulo"), resultado.getString("ref"),resultado.getDouble("precio"),resultado.getString("descripcion"));
       }
       return art;
   }
   
 
   
   public void addArticulo(Connection conexion, Articulo a) throws SQLException{
       try{
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("INSERT INTO Articulo (articulo, ref, precio, descripcion) VALUES(?, ?, ?, ?)");
         consulta.setString(1, a.getArticulo());
         consulta.setString(2, a.getRef());
         consulta.setDouble(3, a.getPrecio());
         consulta.setString(4,a.getDescripcion());
        
         System.out.print(consulta.executeUpdate());
         
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   



  
  
  
  
}

            