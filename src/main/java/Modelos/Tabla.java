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
public class Tabla {
   private DefaultTableModel tabla;
   
   public Tabla(){
       tabla=null;
   }
    
    
    public void addColumn(Object col){
        tabla.addColumn(col);
        System.out.println(tabla);
    }
    
    
}
