/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;
import Connection.Conexion;
import Modelos.Articulo;
import Modelos.DetalleTicket;
import Modelos.DetalleVenta;
import Modelos.Paciente;
import Modelos.Ticket2;
import Modelos.Usuario;
import Modelos.Venta;
import Sistema.Principal;
import static Sistema.Principal.da;
import static Sistema.Principal.pac;
import static Sistema.Principal.paciente;
import static Sistema.Principal.s;
import static Sistema.Principal.tiva;
import static Sistema.Principal.tot;
import static Sistema.Principal.tsubtot;
import static Sistema.Principal.u;
import Ticket.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author SL03483514
 */
public class Servicios {
    private final String tabla = "Usuario";
    JButton boton1 = new JButton("Editar");
     JButton boton2 = new JButton("Eliminar");
     
     JButton boton3 = new JButton("Cancelar");
     ArrayList<DetalleVenta> arts = new ArrayList<DetalleVenta>();
    public static ArrayList<String[]> corte = new ArrayList<String[]>();
     public static ArrayList<String[]> corte2 = new ArrayList<String[]>();
     Usuario u=new Usuario();
    public static int  p;
     double total;
     public static double totalcorteEfe,totalcorteTC,totalcorteTD;
     
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
             u= new Usuario(resultado.getInt("id"),resultado.getString("usuario"),resultado.getString("password"),resultado.getInt("rol"));
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
         Usuario u =new Usuario();
         u=Repetidos(conexion,usuario);
         if(u.getID() == null){
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(usuario, password, rol) VALUES(?, ?, ?)");
            consulta.setString(1, usuario.getUsuario());
            consulta.setString(2, usuario.getPassword());
            consulta.setInt(3, usuario.getRol());
            System.out.print(consulta.executeUpdate());
            showMessageDialog(null, "Agregado con Exito");
         }else{
             showMessageDialog(null, "Ya existe un Usuario creado con ese Nombre");
         }
         
      }catch(SQLException ex){
          
            showMessageDialog(null, "Error al Agregar Usuario");
         throw new SQLException(ex);
      }
   }
   
   
   
   public DefaultTableModel recuperarUsu(Connection conexion, String usu) throws SQLException {
      DefaultTableModel usua=new DefaultTableModel();
      
      usua.addColumn("id");
      usua.addColumn("usuario");
      usua.addColumn("Rol");
      usua.addColumn("Editar");
       usua.addColumn("Eliminar");
      
      try{
       
         PreparedStatement consulta = conexion.prepareStatement("SELECT top(5) * FROM Usuario WHERE usuario LIKE ?");
         consulta.setString(1, "%"+usu+"%");
         
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[5];
             
             d[0]=resultado.getString("id");
             d[1]=resultado.getString("usuario");
             if(resultado.getInt("rol")==0){
             d[2]="Recepcionista";
             }else{
             d[2]="Administrador";
             }
             d[3]=boton1;
             d[4]=boton2;
             usua.addRow(d);
             
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return usua;
   }
   
   public void eliminarUsuario(Connection conexion, int id) throws SQLException{
      try{
         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id = ?");
         consulta.setInt(1, id);
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
   public DefaultTableModel recuperarDV(Connection conexion,int id) throws SQLException{
        DefaultTableModel dv=new DefaultTableModel();
      dv.addColumn("id");
      dv.addColumn("Articulo");
      dv.addColumn("Importe");
      dv.addColumn("Cantidad"); 
      dv.addColumn("Subtotal");
      
      
         PreparedStatement consulta = conexion.prepareStatement("select \n" +
"id,\n" +
"(select articulo from Articulo where id=id_Articulo)as articulo,\n" +
"importe,\n" +
"cantidad,\n" +
"(importe*cantidad) as subtotal\n" +
"from Detalle_Venta where id_venta="+id);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[5];
             d[0]=resultado.getString("id");
             d[1]=resultado.getString("articulo");  
             d[2]=resultado.getString("importe");   
             d[3]=resultado.getString("cantidad");  
             d[4]=resultado.getString("subtotal"); 
             dv.addRow(d);
             
       
       }
         
       return dv;
   } 
   
   
   public DefaultTableModel recuperarCortes(Connection conexion) throws SQLException{
   DefaultTableModel ventas=new DefaultTableModel();
      
      ventas.addColumn("id");
      ventas.addColumn("fecha");
      ventas.addColumn("Usuario");
      ventas.addColumn("total");
    PreparedStatement consulta = conexion.prepareStatement("select * from corte");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[4];
             d[0]=resultado.getString("id");
             d[1]=resultado.getString("fecha");  
             d[2]=resultado.getString("usuario");  
             d[3]=resultado.getString("total"); 
             
             ventas.addRow(d);
       
       }return ventas;
   
   
   }
   
   public void cortexarticulo(Connection conexion , int id) throws SQLException{
       corte2 = new ArrayList<String[]>();
        PreparedStatement consulta;
         consulta = conexion.prepareStatement("select a.Articulo as art,a.precio, sum(dv.cantidad) as cantidad ,sum(importe*cantidad) as total\n" +
            "from Detalle_Venta dv\n" +
            "inner join Venta as v on v.id=dv.id_venta and v.estatus<>0\n" +
            "inner join Articulo a on a.id=dv.id_Articulo\n" +
            "where id_venta in (select id from venta where EXISTS (select top(1) * from corte_caja WHERE venta.id_corte_caja="+id+")  )\n" +
            "group by a.Articulo,a.precio order by a.Articulo");
        
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             //acomodar 
             String[] e={resultado.getString("art"),resultado.getString("cantidad"),resultado.getString("total")};
             corte2.add(e);
            // corte.add(e);
           //  bandera=true;
         }
         
        
       
   }
   
   
   public DefaultTableModel recuperarVentas(Connection conexion) throws SQLException{
   DefaultTableModel ventas=new DefaultTableModel();
      
      ventas.addColumn("id");
      ventas.addColumn("Paciente");
      ventas.addColumn("Telefono");
      ventas.addColumn("Usuario");
      ventas.addColumn("Fecha"); 
      ventas.addColumn("Forma de pago");
      ventas.addColumn("Estatus");
      ventas.addColumn("Cancelar");
       
         PreparedStatement consulta = conexion.prepareStatement("select TOP (100)\n" +
            "id,(select nombre from Paciente where id=id_Paciente) as Paciente,\n" +
            "(select telefono from Paciente where id=id_Paciente) as Telefono,\n" +
            "(select usuario from Usuario where id=id_Usuario) as Usuario,\n" +
            "fecha,estatus,\n" +
            "(select  forma_de_pago from pago where id=id_forma_pago) as forma_pago\n" +
            "from Venta order by id desc");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[8];
             d[0]=resultado.getString("id");
             d[1]=resultado.getString("Paciente");  
             d[2]=resultado.getString("Telefono");  
             d[3]=resultado.getString("Usuario");   
             d[4]=resultado.getString("fecha");  
             
             d[5]=resultado.getString("forma_pago");  
             if(resultado.getBoolean("estatus")==false){
                       d[6]="CANCELADA"; 
             }else{
             d[6]="OK"; }
             
             d[7]=boton3; 
             
             ventas.addRow(d);
       
       }return ventas;
   }
   
   
      
   public DefaultTableModel recuperarVentasFilt(Connection conexion,String campo,String valor) throws SQLException{
   DefaultTableModel ventas=new DefaultTableModel();
      
      ventas.addColumn("id");
      ventas.addColumn("Paciente");
      ventas.addColumn("Telefono");
      ventas.addColumn("Usuario");
      ventas.addColumn("Fecha"); 
      ventas.addColumn("Forma de pago");
       
         PreparedStatement consulta = conexion.prepareStatement("select top(50)\n" +
            " Venta.id,\n" +
            "pac.nombre as Paciente,   \n" +
            "pac.telefono as Telefono,\n" +
            " usu.usuario  as Usuario,\n" +
            " fecha,\n" +
            "(select  forma_de_pago from pago where id=id_forma_pago) as forma_pago\n" +
            "from Venta\n" +
            "inner join Paciente as pac on pac.id=id_Paciente\n" +
            "inner join Usuario as usu on usu.id=id_Usuario\n" +
            "where "+campo+" like  '%"+valor+"%'");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[6];
             d[0]=resultado.getString("id");
             d[1]=resultado.getString("Paciente");  
             d[2]=resultado.getString("Telefono");  
             
             d[3]=resultado.getString("Usuario");   
             d[4]=resultado.getString("fecha");  
             d[5]=resultado.getString("forma_pago"); 
             ventas.addRow(d);
       
       }return ventas;
   }
   
   
   public DefaultTableModel recuperarVentasC(Connection conexion,int id) throws SQLException{
       DefaultTableModel ventas=new DefaultTableModel();
      
      ventas.addColumn("id");
      ventas.addColumn("Paciente");
      ventas.addColumn("Telefono");
      
      ventas.addColumn("Usuario");
      ventas.addColumn("Fecha"); 
      ventas.addColumn("Forma de pago");
       
         PreparedStatement consulta = conexion.prepareStatement("select TOP (15)\n" +
            "id,(select nombre from Paciente where id=id_Paciente) as Paciente,\n" +   
            "(select telefono from Paciente where id=id_Paciente) as Telefono,\n" +
            "(select usuario from Usuario where id=id_Usuario) as Usuario,\n" +
            "fecha,\n" +
            "(select  forma_de_pago from pago where id=id_forma_pago) as forma_pago\n" +
            "from Venta where id_corte_caja="+id);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[6];
             d[0]=resultado.getString("id");
             d[1]=resultado.getString("Paciente");  
             
             d[2]=resultado.getString("Telefono");  
             d[3]=resultado.getString("Usuario");   
             d[4]=resultado.getString("fecha");  
             d[5]=resultado.getString("forma_pago"); 
             ventas.addRow(d);
       
       }return ventas;
       
   }
   
   public DefaultTableModel recuperarTodas(Connection conexion) throws SQLException{
      DefaultTableModel pacientes=new DefaultTableModel();
      pacientes.addColumn("id");
      pacientes.addColumn("usuario");
      pacientes.addColumn("Rol");
       pacientes.addColumn("Editar"); 
       pacientes.addColumn("Eliminar");
           
      //List<Usuario> usuarios = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT top(15) * FROM " + this.tabla);
         
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[5];
             
             d[0]=resultado.getString("id");
             d[1]=resultado.getString("usuario");  
             if(resultado.getInt("rol")==0){
             d[2]="Recepcionista";
             }else{
             d[2]="Administrador";
             }
            
             d[3]=boton1;
             d[4]=boton2;
             pacientes.addRow(d);
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
     
     
     public Usuario getUsuario(Integer id,Connection conexion){
         Usuario usu = new Usuario();
         
         PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Usuario WHERE id = ?");
            consulta.setInt(1 , id);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                usu = new Usuario(resultado.getInt("id"),resultado.getString("usuario"),resultado.getString("password") ,resultado.getInt("rol"));
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     return usu;
     }
//*******************************************************  PACIENTES  ********************************************************************************************************************************* 
     public int elinimarCatalogo(Connection conexion,String tabla,int id){
         
         PreparedStatement consulta;
        try {
         consulta = conexion.prepareStatement("DELETE FROM " + tabla + " WHERE id = ?"); 
         consulta.setInt(1, id);
         consulta.executeUpdate();
         return 0;
        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
      
     }
     
     
     public Paciente getPaciente(Connection conexion,Integer id){
         Paciente p = new Paciente();
         
         PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Paciente WHERE id = ?");
            consulta.setInt(1 , id);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                p = new Paciente(resultado.getInt("id"),resultado.getString("nombre"),resultado.getString("telefono") ,resultado.getInt("edad"),resultado.getString("direccion") );
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     return p;
     }
   
     public DefaultTableModel GetPacientes(Connection conexion) throws SQLException{
      DefaultTableModel pacientes=new DefaultTableModel();
      pacientes.addColumn("id");
      pacientes.addColumn("nombre");
      pacientes.addColumn("telefono");
      pacientes.addColumn("edad");
      pacientes.addColumn("direccion");
      pacientes.addColumn("Editar");
       pacientes.addColumn("Eliminar");
           
      //List<Usuario> usuarios = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Paciente");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[7];
             d[0]=resultado.getInt("id");
             d[1]=resultado.getString("nombre");
             d[2]= resultado.getString("telefono");
             d[3]= resultado.getInt("edad");
             d[4]= resultado.getString("direccion");
             d[5]= boton1;
             d[6]= boton2;
             
             
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
    public int AddPaciente(Connection conexion, Paciente pac) throws SQLException{
           int id=0;
      try{
         PreparedStatement consulta;
         Paciente p =new Paciente();
         p=RepetidosPac(conexion,pac);
        if(p.getID() == null){
            consulta = conexion.prepareStatement("INSERT INTO Paciente (nombre, telefono, edad, direccion) VALUES(?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);         
            consulta.setString(1, pac.getPaciente());
            consulta.setString(2, pac.getTelefono());
            consulta.setInt(3, pac.getEdad());
            consulta.setString(4,pac.getDireccion());
            System.out.print(consulta.executeUpdate());
            ResultSet result = consulta.getGeneratedKeys(); 
            if (result.next()) {
                 id = result.getInt(1);
            }
          }else{
             showMessageDialog(null, "Ya existe un Paciente creado con ese Nombre");
         }    
        
       return id;
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
    
   public int ChangeEstatus(Connection conexion,int id) throws SQLException{
       try{
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("UPDATE Venta SET estatus = 0 WHERE id = "+id+"; ");
             System.out.print(consulta.executeUpdate());
            return 0;
      }catch(SQLException ex){
         throw new SQLException(ex);
         //return 1;
      }
   }
   
       
  public int UpdatePaciente(Connection conexion, Paciente pac,int id) throws SQLException{
      try{
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("UPDATE Paciente SET nombre = ?, telefono = ?, edad = ?, direccion = ? WHERE id = "+id+"; ");
         
            consulta.setString(1, pac.getPaciente());
            consulta.setString(2, pac.getTelefono());
            consulta.setInt(3, pac.getEdad());
            consulta.setString(4,pac.getDireccion());
        
         System.out.print(consulta.executeUpdate());
         return 0;
      }catch(SQLException ex){
         throw new SQLException(ex);
         //return 1;
      }
   }
       
  
       public DefaultTableModel recuperarPac(Connection conexion, String pac,String bx) throws SQLException {
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
       
         PreparedStatement consulta = conexion.prepareStatement("SELECT top(5) * FROM Paciente WHERE "+bx+" LIKE ?");
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
   
       public Articulo getArticulo(Connection conexion,int id){
           Articulo art = new Articulo();
         
         PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM Articulo WHERE id = ?");
            consulta.setInt(1 , id);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                art = new Articulo( resultado.getInt("id"),resultado.getString("articulo"),resultado.getString("ref") ,resultado.getDouble("precio"),resultado.getString("descripcion") );
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     return art;
       }
       
       
           public DefaultTableModel recuperarArt(Connection conexion, String art, String bx) throws SQLException {
      DefaultTableModel articulos=new DefaultTableModel();
       articulos.addColumn("id");
      articulos.addColumn("Articulo");
      articulos.addColumn("ref");
      articulos.addColumn("precio");
      articulos.addColumn("descripcion");
      articulos.addColumn("Editar");
       articulos.addColumn("Eliminar");
      
        try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Articulo WHERE "+bx+" LIKE ?");
         consulta.setString(1, "%"+art+"%");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[7];
             d[0]=resultado.getInt("id");
             d[1]=resultado.getString("Articulo");
             d[2]= resultado.getString("ref");
             d[3]= resultado.getDouble("precio");
             d[4]= resultado.getString("descripcion");
             d[5]= boton1;
             d[6]= boton2;
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
      articulos.addColumn("Editar");
       articulos.addColumn("Eliminar");
           
      //List<Usuario> usuarios = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM Articulo");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Object d[] = new Object[7];
             d[0]=resultado.getInt("id");
             d[1]=resultado.getString("Articulo");
             d[2]= resultado.getString("ref");
             d[3]= resultado.getDouble("precio");
             d[4]= resultado.getString("descripcion");
             d[5]= boton1;
             d[6]= boton2;
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
     
 public int getIdCorte(Connection conexion){
     int id=0;
     
        try {
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("SELECT TOP 1 * FROM corte_caja ORDER BY id DESC " );
            ResultSet resultado = consulta.executeQuery();
       while(resultado.next()){
            id=resultado.getInt("id");
        }
        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       
     
     return id;
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
 
   
   public int  addArticulo(Connection conexion, Articulo a) throws SQLException{
       int id=0;
       try{
         Articulo p =new Articulo();
         p=RepetidosArt(conexion,a);
           if(p.getID()==null){
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("INSERT INTO Articulo (articulo, ref, precio, descripcion) VALUES(?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
         consulta.setString(1, a.getArticulo());
         consulta.setString(2, a.getRef());
         consulta.setDouble(3, a.getPrecio());
         consulta.setString(4,a.getDescripcion());
         System.out.println(consulta);
         System.out.println(consulta.executeUpdate());
         ResultSet result = consulta.getGeneratedKeys(); 
         
            if (result.next()) {
                 id = result.getInt(1);
            }
          }else{
             showMessageDialog(null, "Ya existe un Articulo creado con ese Nombre");
         }   
           
         return id;
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
       
         
   }
   
    public int updateUsu(Connection conexion, String usu,int rol,int id) throws SQLException{
       try{
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("UPDATE Usuario SET usuario = ?, rol = ? WHERE id = "+id+"; ");
         consulta.setString(1, usu);
         consulta.setInt(2, rol);
         System.out.print(consulta.executeUpdate());
         return 0;
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
       
   }
   
    public int updateUsu(Connection conexion, String pass,int id) throws SQLException{
       try{
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("UPDATE Usuario SET password = ? WHERE id = "+id+"; ");
         consulta.setString(1, pass);
         System.out.print(consulta.executeUpdate());
         return 0;
      }catch(SQLException ex){
         throw new SQLException(ex); 
      }
   }
   
    public void updateArt(Connection conexion, Articulo a,int id) throws SQLException{
       try{
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("UPDATE Articulo SET articulo = ?, ref = ?, precio = ?, descripcion = ? WHERE id = "+id+"; ");
         consulta.setString(1, a.getArticulo());
         consulta.setString(2, a.getRef());
         consulta.setDouble(3, a.getPrecio());
         consulta.setString(4,a.getDescripcion());
         System.out.print(consulta.executeUpdate());
         //return 1;
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   
   public String getFecha(){
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));
       return dtf.format(LocalDateTime.now());
   }
      
   public boolean valdv(){
       if (arts.isEmpty()){
           return true;
        }
       return false;
   }
   
   public Integer addVenta(Connection conexion,int fp) throws SQLException{
       int idDevuelto = 0;
       if (arts.isEmpty()){
           return 0;
           
       }
       try{
          int cc=getIdCorte(conexion);
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));
         PreparedStatement consulta;
         consulta = conexion.prepareStatement("INSERT INTO Venta (id_paciente, id_usuario,fecha,id_forma_pago,id_corte_caja,estatus) VALUES(?, ?,?,?,?,1); ",Statement.RETURN_GENERATED_KEYS);
         consulta.setInt(1,p);// p.getID());
         consulta.setInt(2, u.getID());
         consulta.setString(3,getFecha());
         consulta.setInt(4,fp);
         consulta.setInt(5,cc);
        
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
   
   
   public int cantidad(int id,int cantidad){
       int act=0;
       DetalleVenta d=new DetalleVenta();
       
           for(DetalleVenta dv : arts)
            {   
                
                  System.out.print(dv.getCantidad());
              if (dv.getIdArticulo()==id){
                  d.setDetalleVenta(dv.getIdVenta(), Integer.parseInt(dv.getIdArticulo().toString()), Double.parseDouble(dv.getImporte().toString()), cantidad);
                  arts.set(act, d);
                  
                  System.out.print(dv.getCantidad());
              }
               act=act+1;
               
                  System.out.print(dv.getCantidad()+"este es");
            }
           
           for(DetalleVenta dv : arts)
            {   
           
            
                  System.out.print(dv.getCantidad()+"este es EL BUENO");
            }
           
           
        return act;
       
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
   
    public boolean ValidarCorte(Connection conexion,int id) throws SQLException{
        corte = new ArrayList<String[]>();
        boolean bandera = false;
        PreparedStatement consulta;
       // consulta = conexion.prepareStatement("select sum(importe) as total from Detalle_Venta where id_venta in (select id from venta where EXISTS (select top(1) * "
         //       + "from corte_caja WHERE venta.id_corte_caja="+id+"))");
        
        
        
        
         consulta = conexion.prepareStatement("select p.forma_de_pago as pago, sum(importe*cantidad) as total\n" +
"                from Detalle_Venta dv\n" +
"                inner join Venta as v on v.id=dv.id_venta and v.estatus<>0\n" +
"                inner join pago as p on p.id=v.id_forma_pago\n" +
"                where id_venta in (select id from venta where EXISTS (select top(1) * from corte_caja WHERE venta.id_corte_caja="+id+")  )\n" +
"				group by p.forma_de_pago order by p.forma_de_pago");
        
        
        
        ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             //acomodar 
             String[] e={resultado.getString("pago"),resultado.getString("total")};
             corte.add(e);
             bandera=true;
         }
         
        return bandera;
    }    
        
  
    
        
             
    public int generaCorte(Connection conexion,int id) throws SQLException{
      int idc=0;
       try{
          int res=getIdCorte(conexion); 
          boolean a=ValidarCorte(conexion,res);
          cortexarticulo(conexion,res);
        if(a){  
            PreparedStatement consulta;
        consulta = conexion.prepareStatement("INSERT INTO corte_caja (fecha, total,id_usuario) VALUES(?,?,?); ",Statement.RETURN_GENERATED_KEYS);
        consulta.setString(1,getFecha());// p.getID());
        consulta.setDouble(2, 0);
        consulta.setInt(3,id);
        System.out.println(consulta.executeUpdate());//consulta.executeUpdate()
        ResultSet result = consulta.getGeneratedKeys();
        TicketCorte();
        
        if (result.next()) {
            idc = result.getInt(1);
          }
        }else{
            idc=0;
            
        }
              
           return idc;
    
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
        /*********************************PAGO********************************/
       public ResultSet getPago(Connection conexion) {
        ResultSet resultado =null;
       PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("SELECT * FROM pago " );
            resultado = consulta.executeQuery();
             return resultado;
         } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return resultado;
       
   }
       
       /***********************************Ticket***************************************/
        public void ticket(Connection conexion) throws SQLException{
            double totalticket=0;
        ArrayList<DetalleTicket> dv2=new ArrayList();    
         Ticket2 t=null;
        PreparedStatement consulta = conexion.prepareStatement("select TOP (1)\n" +
            "id,(select nombre from Paciente where id=id_Paciente) as Paciente,\n" +
            "(select usuario from Usuario where id=id_Usuario) as Usuario,\n" +
            "(select telefono from Paciente where id=id_Paciente) as Telefono,\n" +
            "fecha,\n" +
            "(select  forma_de_pago from pago where id=id_forma_pago) as forma_pago\n" +
            "from Venta order by id desc");
         ResultSet resultado = consulta.executeQuery();
         
         while(resultado.next()){
            PreparedStatement consulta2 = conexion.prepareStatement("select \n" +
"id,\n" +
"(select articulo from Articulo where id=id_Articulo)as articulo,\n" +
"importe,\n" +
"cantidad,\n" +
"(importe*cantidad) as subtotal\n" +
"from Detalle_Venta where id_venta="+resultado.getInt("id"));
         ResultSet resultado2 = consulta2.executeQuery();
             
             while(resultado2.next()){
                    DetalleTicket dt=new DetalleTicket(resultado2.getInt("id"),resultado2.getString("Articulo"),resultado2.getDouble("importe"),
                            resultado2.getInt("cantidad"), resultado2.getDouble("subtotal")) ;
                    
                    
                    dv2.add(dt);
                 
             }
             
             t= new Ticket2(resultado.getInt("id"),
                     resultado.getString("Usuario"),
                     resultado.getString("Paciente"),
                     resultado.getString("Telefono"),
                     dv2,
                     0);
            
         }                
        Ticket ticket=new Ticket();
        
        ticket.AddCabecera("FABELA");
        ticket.AddCabecera(ticket.DarEspacio());
         ticket.AddCabecera("GRUPO MEDICO");
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera("Expedido el: "+s.getFecha());
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera("Plaza Casino Int.7 y 8");
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera("Calle, Lerdo esq. Durango");
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera("Col, Centro, 63000 Tepic, Nay.");
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera(ticket.DibujarLinea(29));
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera("PACIENTE: "+t.getPaciente());
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera("TELEFONO: "+t.getTelefono());
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera("Caja # 1 - Ticket #"+t.getID());
        ticket.AddSubCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera("LE ATENDIO: "+t.getUsuario());
        ticket.AddSubCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera(ticket.DibujarLinea(29));
        ticket.AddSubCabecera(ticket.DarEspacio());
        for(DetalleTicket dv : dv2)
            {         
                ticket.AddItem(dv.getID()+"", dv.getArticulo()+"", dv.getPrecio()+"", dv.getCantidad()+"");
                ticket.AddItem("",  ticket.DarEspacio(),"", "");
                totalticket=totalticket+dv.getSubtotal();
       
            }
        ticket.AddTotal(ticket.DibujarLinea(29),"");
        ticket.AddTotal("", ticket.DarEspacio());
        ticket.AddTotal("SUBTOTAL: ", "0.00");
        ticket.AddTotal("", ticket.DarEspacio());
        ticket.AddTotal("IVA:      ", "0.00");
        ticket.AddTotal("", ticket.DarEspacio());
        ticket.AddTotal("TOTAL:    ", totalticket+"");
        ticket.AddTotal("", ticket.DarEspacio());
        ticket.AddTotal("", ticket.DarEspacio());
        ticket.AddPieLinea(ticket.DibujarLinea(29));
        ticket.AddPieLinea(ticket.DarEspacio());
        ticket.AddPieLinea("Gracias por su visita");
        ticket.AddPieLinea(ticket.DarEspacio());
        ticket.ImprimirDocumento("LPT2",false);
        
            
        }
        
        
        
        public void TicketCorte(){
        Ticket ticket=new Ticket();
        ticket.AddCabecera("          FABELA");
        ticket.AddCabecera(ticket.DarEspacio());
         ticket.AddCabecera("      GRUPO MEDICO");
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera(ticket.DibujarLinea(29));
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddCabecera("Expedido el: "+s.getFecha());
        ticket.AddCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera("Caja # 1 - CORTE DE CAJA ");
        ticket.AddSubCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera("Usuario: "+u.getUsuario());
        ticket.AddSubCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera(ticket.DibujarLinea(29));
        ticket.AddSubCabecera(ticket.DarEspacio());
        
       
             for(String[] a : s.corte){
                ticket.AddItem(a[0], ":  $",a[1]," ");
                ticket.AddItem("",  ticket.DarEspacio(),"", "");
             }
        
        
        ticket.AddTotal(ticket.DibujarLinea(29),"");
        ticket.AddTotal("", ticket.DarEspacio());
        ticket.AddTotal("", ticket.DarEspacio());
        ticket.AddPieLinea(ticket.DibujarLinea(29));
        ticket.AddPieLinea(ticket.DarEspacio());
        ticket.AddPieLinea("Corte generado");
        ticket.AddPieLinea(ticket.DarEspacio());
        ticket.ImprimirDocumento("LPT2",false);
            
        }
        
        
        public Usuario Repetidos(Connection conexion,Usuario u) throws SQLException{
           
            PreparedStatement consulta = conexion.prepareStatement("SELECT top(1) * FROM Usuario WHERE usuario = ?");
              consulta.setString(1,u.getUsuario());
            ResultSet resultado = consulta.executeQuery(); 
            
            while(resultado.next()){ 
                 u=new Usuario(resultado.getInt("id"), resultado.getString("usuario"), resultado.getString("password"),resultado.getInt("rol") );
                 
             }
                  
                 return u;
        }
        
        
            
        public Paciente RepetidosPac(Connection conexion,Paciente u) throws SQLException{
           
            PreparedStatement consulta = conexion.prepareStatement("SELECT top(1) * FROM Paciente WHERE nombre = ?");
              consulta.setString(1,u.getPaciente());
            ResultSet resultado = consulta.executeQuery(); 
            
            while(resultado.next()){ 
                 u=new Paciente(resultado.getInt("id"));
                 
                 
             }
                  
                 return u;
        }
        
        
        
        public Articulo RepetidosArt(Connection conexion,Articulo u) throws SQLException{
           
            PreparedStatement consulta = conexion.prepareStatement("SELECT top(1) * FROM Articulo WHERE Articulo = ?");
              consulta.setString(1,u.getArticulo());
            ResultSet resultado = consulta.executeQuery(); 
            
            while(resultado.next()){ 
                 u=new Articulo(resultado.getInt("id"));
                 
                 
             }
                  
                 return u;
        }
        
        
       
        
}

            