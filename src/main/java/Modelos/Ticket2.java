/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class Ticket2 {
   private int id;
   private String Usuario;
   private String Paciente;
   private String Telefono;
   private ArrayList<DetalleTicket> dv;
   private double total;
   
   public Ticket2(){
       this.id=0;
       this.Usuario=null;
       this.Paciente=null;
       this.dv=null;
       this.Telefono=null;
       this.total=0;
   }
   
     public Ticket2(int id,String Usuario,String Paciente,String Telefono,ArrayList<DetalleTicket> dv,double total) {
      this.id=id;
       this.Usuario=Usuario;
       this.Paciente=Paciente;
       this.dv=dv;
       this.Telefono=Telefono;
       this.total=total;
   }
   
     public int getID(){
         return this.id;
     }
     
     public String getUsuario(){
         return this.Usuario;
     }
     
     public String getPaciente(){
         return this.Paciente;
     }
     
     public String getTelefono(){
         return this.Telefono;
     }
     
     public ArrayList<DetalleTicket> getDV(){
         return this.dv;
     }
     
     public double getTotal(){
         return this.total;
     }
 
   
   
}
