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
   
   public Usuario() {
      this.id = null;
      this.Usuario = null;
      this.Password = null;
   }
   public Usuario(Integer id, String Usuario, String Password) {
      this.id = id;
      this.Usuario = Usuario;
      this.Password = Password;
   }
   
   public void setUsuario(String Usuario, String Password) {
       
      this.Usuario = Usuario;
      this.Password = Password;
     
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
   
 
  
   @Override
   public String toString() {
      return "Usuario{" + "id=" + id + ", Usuario=" + Usuario + ", Password=" + Password +  '}';
   } 

 
}
