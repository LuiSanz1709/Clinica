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
public class Articulo {
    private final Integer id;
    private String articulo;
    private String ref;
    private double precio;
    private String descripcion;
    
    public Articulo(){
        this.id=null;
        this.articulo=null;
        this.ref=null;
        this.precio=0.0;
        this.descripcion=null;
    }
    
     public Articulo(Integer id, String articulo, String ref,Double precio,String descripcion) {
        this.id=id;
        this.articulo=articulo;
        this.ref=ref;
        this.precio=precio;
        this.descripcion=descripcion;
   }
    
    public void setArticulo( String articulo, String ref,double precio,String descripcion) {
 
        this.articulo=articulo;
        this.ref=ref;
        this.precio=precio;
        this.descripcion=descripcion;
   }
    
         
   public Integer getID() {
      return id;
   }
   
    public String getArticulo() {
      return articulo;
   }
    
      
   public String getRef() {
      return ref;
   }
   
      
   public Double getPrecio() {
      return precio;
   }
   
   public String getDescripcion() {
      return descripcion;
   }

            
    
}
