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
import Modelos.Venta;
import Sistema.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author SL03483514
 */
public class Servicios {
    private final String tabla = "Usuario";
    JButton boton1 = new JButton("Editar");
     JButton boton2 = new JButton("Eliminar");
     ArrayList<DetalleVenta> arts = new ArrayList<DetalleVenta>();
     Usuario u=new Usuario();
    public static int  p;
     double total;
     
     
     public void limpiar(){
   // u.usuario()
     p=0;
     total=0;
     
     }
   //*******************************************************  LOGIN  ********************************************************************************************************************************* 
   public Usuario login(Connection conexion,String usuario)  throws SQLException{
         try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM " + this.tabla + " WHERE usuario = ?" );
         consulta.setString(1, usuario);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             u= new Usuario(resultado.getInt("id"),resultado.getString("usuario"),resultado.getString("password"));
            // System.out.println(u.toString());
             System.out.println(u);
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
   
   
   
   public DefaultTableModel recuperarUsu(Connection conexion, String usu) throws SQLException {
      DefaultTableModel usua=new DefaultTableModel();
      usua.addColumn("usuario");
      usua.addColumn("Editar");
       usua.addColumn("Eliminar");
      
      
      
      Usuario usuario = null;
   
       // boton.setSize(100,45);
       // boton.setVisible(true);
      try{
       
         PreparedStatement consulta = conexion.prepareStatement("SELECT top(5) * FROM Usuario WHERE usuario LIKE ?");
         consulta.setString(1, "%"+usu+"%");
         
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[3];
             d[0]=resultado.getString("usuario");
             d[1]=boton1;
             d[2]=boton2;
             usua.addRow(d);
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
      return usua;
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
       pacientes.addColumn("Editar"); 
       pacientes.addColumn("Eliminar");
           
      //List<Usuario> usuarios = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM " + this.tabla);
         
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[3];
             d[0]=resultado.getString("usuario");
             d[1]=boton1;
             d[2]=boton2;
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
  
       public DefaultTableModel recuperarPac(Connection conexion, String pac) throws SQLException {
      DefaultTableModel pacs=new DefaultTableModel();
       pacs.addColumn("id");
      pacs.addColumn("nombre");
      pacs.addColumn("telefono");
      pacs.addColumn("edad");
      pacs.addColumn("direccion");
      pacs.addColumn("Editar");
       pacs.addColumn("Eliminar");
      
      
      
    
   
       // boton.setSize(100,45);
       // boton.setVisible(true);
      try{
       
         PreparedStatement consulta = conexion.prepareStatement("SELECT top(5) * FROM Paciente WHERE nombre LIKE ?");
         consulta.setString(1, "%"+pac+"%");
         
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[7];
             d[0]=resultado.getInt("id");
             d[1]=resultado.getString("nombre");
             d[2]= resultado.getString("telefono");
             d[3]= resultado.getInt("edad");
             d[4]= resultado.getString("direccion");
             d[5]=boton1;
             d[6]=boton2;
             pacs.addRow(d);
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
      return pacs;
   }
     
     //*******************************************************  ARTICULOS  ********************************************************************************************************************************* 
   
       
           public DefaultTableModel recuperarArt(Connection conexion, String art) throws SQLException {
      DefaultTableModel articulos=new DefaultTableModel();
       articulos.addColumn("id");
      articulos.addColumn("Articulo");
      articulos.addColumn("ref");
      articulos.addColumn("precio");
      articulos.addColumn("descripcion");
      
        try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Articulo WHERE ref LIKE ?");
         consulta.setString(1, "%"+art+"%");
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
     
   
   public Articulo getArticulo(String a,Connection conexion) {
  
           
       
       DetalleVenta dv=new DetalleVenta();
       Articulo art= null;
       PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Articulo WHERE ref = ?" );
        
       consulta.setString(1, a);
       ResultSet resultado = consulta.executeQuery();
       while(resultado.next()){
            dv.setDetalleVentaTem(resultado.getInt("id"),resultado.getDouble("precio"),1);
            art = new Articulo(resultado.getInt("id"), resultado.getString("Articulo"), resultado.getString("ref"),resultado.getDouble("precio"),resultado.getString("descripcion"));
            if(verificarArt(resultado.getInt("id"))){
                 art = new Articulo(0, "", "",0.0,"");
                return art;
            }
            arts.add(dv);
          
       }
         } catch (SQLException ex) {
             System.out.println("dzx v");
              art = new Articulo(-1, "", "",0.0,"");
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
             System.out.println("dzx");
              art = new Articulo(-1, "", "",0.0,"");
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return art;
       
   }
   
      public boolean getArticulo2(int id,double precio){
       DetalleVenta dv=new DetalleVenta();
            dv.setDetalleVentaTem(id,precio,1);
             if(verificarArt(id)){
                return true;
            }
            arts.add(dv);
            return false;
          
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
   
      
   public Integer addVenta(Connection conexion) throws SQLException{
       int idDevuelto = 0;
       if (arts.isEmpty()){
           return 0;
           
       }
       try{
         
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("INSERT INTO Venta (id_paciente, id_usuario) VALUES(?, ?); ",Statement.RETURN_GENERATED_KEYS);
         consulta.setInt(1,p);// p.getID());
         consulta.setInt(2, u.getID());
        
         System.out.println(consulta);
         System.out.println(consulta.executeUpdate());
         ResultSet result = consulta.getGeneratedKeys();  

        if (result.next()) {
         idDevuelto = result.getInt(1);
          }
       p=0;
        
         
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
       addDetalleVenta(conexion,idDevuelto);
       return idDevuelto;
   }
   
   
         
        public void addDetalleVenta(Connection conexion,Integer id) throws SQLException{
      
       try{
           for(DetalleVenta dv : arts)
            {
               PreparedStatement consulta;
               consulta = conexion.prepareStatement("INSERT INTO Detalle_Venta (id_venta, id_articulo,importe,cantidad) VALUES(?,?,?,?); ");
               consulta.setInt(1,id);// p.getID());
               consulta.setInt(2, dv.getIdArticulo());
               consulta.setDouble(3,dv.getImporte());
              consulta.setInt(4,dv.getCantidad());
              
               System.out.println(consulta.executeUpdate());//consulta.executeUpdate()
              
            }
           arts.clear();
    
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
        
           
        public boolean verificarArt(int id) {
         boolean b=false;
         if(arts.isEmpty()){
               System.out.println("vacio s");
             return false;
             
         }
         for(DetalleVenta dv : arts)
            {
                if(id==dv.getIdArticulo()){
                     System.out.println("repetido s"+id+"   "+dv.getIdArticulo());//consulta.executeUpdate()
                     b= true;
                }else{
                    System.out.println("no repetido s"+id+"   "+dv.getIdArticulo());
                }
            }
         return b;
      
   }
        
        public void clean(){
                   arts.clear();
                   p=0;
                   
        }
        
}

            