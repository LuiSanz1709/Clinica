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
public class DetalleVenta {
       private final Integer id;
    private Integer id_venta;
    private Integer id_Articulo;
    private double importe;
    private Integer cantidad;
    
       public DetalleVenta(){
        this.id=null;
        this.id_venta=null;
        this.id_Articulo=null;
        this.importe=0.0;
        this.cantidad=0;
    }
       
       
    public void setDetalleVenta(Integer id_venta, Integer id_Articulo,double importe, Integer cantidad){
        this.id_venta=id_venta;
        this.id_Articulo=id_Articulo;
        this.importe=importe;
        this.cantidad=cantidad;
    }
    
    
}
