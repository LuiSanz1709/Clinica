/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author SL03483514
 */
public class Paciente {
   private final Integer id;
   private String nombre;
   private String telefono;
   private Integer edad;
   private String direccion;
   
    public Paciente() {
      this.id = null;
      this.nombre = null;
      this.telefono = null;
      this.edad = null;
      this.direccion = null;
   }
    
    public Paciente(Integer id, String nombre, String telefono,Integer edad,String direccion) {
      this.id = id;
      this.nombre = nombre;
      this.telefono = telefono;
      this.edad= edad;
      this.direccion=direccion;
   }
   
  public void setPaciente( String nombre, String telefono,Integer edad,String direccion) {
 
      this.nombre = nombre;
      this.telefono = telefono;
      this.edad= edad;
      this.direccion=direccion;
   }
  
  
     
   public Integer getID() {
      return id;
   }

   public String getTelefono() {
      return telefono;
   }
   
      
   public Integer getEdad() {
      return edad;
   }
   
   public String getDireccion() {
      return direccion;
   }

    public String getPaciente() {
       return nombre; }
   
  
   
   
   
}
