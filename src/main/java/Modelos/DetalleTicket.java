/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;


/**
 *
 * @author luis
 */
public class DetalleTicket {
    private Integer id;
    private String Articulo;
    private double Precio;
    private int cantidad;
    private double subtotal;
    
   public DetalleTicket() {
    this.id=null;
    this.Articulo=null;
    this.Precio=0;
    this.cantidad=0;
    this.subtotal=0;
   }
   
    
   public DetalleTicket(Integer id, String Articulo,double Precio,int cantidad,double subtotal) {
    this.id=id;
    this.Articulo=Articulo;
    this.Precio=Precio;
    this.cantidad=cantidad;
    this.subtotal=subtotal;
   }
   
   public int getID(){
       
       return this.id;
   }
   
   public String getArticulo(){
       return this.Articulo;
   }
   
   public Double getPrecio(){
       return this.Precio;
   }
   
   public int getCantidad(){
       return this.cantidad;
   }
   
   public double getSubtotal(){
       return this.subtotal;
   }
   
   
   
   
    
}
