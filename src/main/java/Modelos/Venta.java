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
public class Venta {
    private final Integer id;
    private Integer id_paciente;
    private Integer id_usuario;
    private double importeTotal;
    
       public Venta(){
        this.id=null;
        this.id_paciente=null;
        this.id_usuario=null;
        this.importeTotal=0.0;
       }
       
        public void setVenta( Integer id_paciente, Integer id_usuario,double importeTotal) {
        this.id_paciente=id_paciente;
        this.id_usuario=id_usuario;
        this.importeTotal=importeTotal;
        }
            
        public Integer getPaciente(){
           return this.id_paciente;
        }
        
        public Integer getUsuario(){
            return this.id_usuario;
        }
        
        public double getImporte(){
            return this .importeTotal;
        }
        

}
