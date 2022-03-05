/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SL03483514
 */
public class Usuario {
    
   private final Integer id;
   private String Usuario;
   private String Password;
   private Integer rol;
   
   public Usuario() {
      this.id = null;
      this.Usuario = null;
      this.Password = null;
      this.rol=null;
   }
   public  Usuario(Integer id, String Usuario, String Password,Integer rol) {
      this.id = id;
      this.Usuario = Usuario;
      this.Password = Password;
      this.rol=rol;
   }
   
   public void setUsuario(String Usuario, String Password,Integer rol) {
       
      this.Usuario = Usuario;
      this.Password = Password;
      this.rol=rol;
     
   }
   
   public Integer getID() {
      return id;
   }
   
   public String getUsuario() {
      return Usuario;
   }
   
   public String getPassword() {
      return Password;
   }
   
    public Integer getRol() {
      return rol;
   }
   
 
  
   @Override
   public String toString() {
      return "Usuario{" + "id=" + id + ", Usuario=" + Usuario + ", Password=" + Password +  '}';
   } 

 
}
