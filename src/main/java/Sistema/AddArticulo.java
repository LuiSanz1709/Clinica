/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Connection.Conexion;
import Modelos.Articulo;
import Modelos.DetalleVenta;
import Modelos.Paciente;
import Servicios.RenderTabla;
import Servicios.Servicios;
import static Sistema.AddPaciente.TPaciente;
import static Sistema.Principal.c;
import static Sistema.Principal.da;
import static Sistema.Principal.jTable3;
import static Sistema.Principal.s;
import static Sistema.Principal.t;
import static Sistema.Principal.tot;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author SL03483514
 */
public class AddArticulo extends javax.swing.JFrame {

    /**
     * Creates new form AddArticulo
     */
    public AddArticulo() {
        initComponents();
        JArticulo.setDefaultRenderer(Object.class,new RenderTabla());
         this.reload();
         
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JArticulo = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        buscarart = new javax.swing.JTextField();
        buscarfilt = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        JArticulo = new javax.swing.JTable(){

            public boolean isCellEditable(int row, int column) { return false; }
        };
        JArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        JArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JArticuloMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JArticulo);

        jPanel1.setBackground(new java.awt.Color(249, 249, 235));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Articulo"));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel3.setText("Precio:");

        jLabel1.setText("Articulo:");

        jLabel2.setText("Referencia:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel4.setText("Descripcion:");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );

        buscarart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarartKeyReleased(evt);
            }
        });

        buscarfilt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Articulo", "Referencia", "Precio", "Descripcion" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buscarart, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarfilt, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarfilt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         if((jTextField2.getText().equals("") || jTextField1.getText().equals("") ||  jTextField3.getText().equals("") || jTextArea1.getText().equals("") )){
            showMessageDialog(null, "campos vacios");
        }else{
        Articulo a=new Articulo();
        a.setArticulo(jTextField1.getText(),(jTextField3.getText()), Double.parseDouble(jTextField2.getText()),jTextArea1.getText());
        
       
        try {
           int id = Principal.s.addArticulo(Principal.c.obtener() , a);
            showMessageDialog(null, "Agregado con Exito");
            //this.setVisible(false);
            
            /*****************************************************************************************/
            
                boolean val=Principal.s.getArticulo2(id,
                                                     Double.parseDouble(jTextField2.getText()));
                if(val){
                      showMessageDialog(null,"repetido");
                }else{
                 Principal.jTable3.setModel(this.AddArt(id,jTextField1.getText(),
                                                        Double.parseDouble(jTextField2.getText()), Principal.da));
                
                this.setVisible(false);
                }
            
            /******************************************************************************************/
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_jButton1ActionPerformed
 public DefaultTableModel AddArt(int id,String articulo,double precio,DefaultTableModel art){
           
             Object d[] = new Object[5];
             d[0]=id;
             d[1]=articulo;
             d[2]=precio;
             d[3]=1;
             d[4]=precio*1;
             art.addRow(d);
              Principal.t=Principal.t+precio;
             Principal.tot.setText(Principal.t+" ");
             
        return art;
  }
    private void JArticuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JArticuloMouseClicked
        // TODO add your handling code here:
        
           int col= JArticulo.getSelectedColumn();
        int Fila = JArticulo.getSelectedRow();
        switch (col){
            case 5: 
                showMessageDialog(null,"Desea Editar? "+JArticulo.getValueAt(Fila, 0).toString());
                break;
            case 6:
                showMessageDialog(null,"Desea Eliminar? "+JArticulo.getValueAt(Fila, 0).toString());
                break;
            default:
                
                //agregar dv falta agregar a arts*************************************************************************************************
             
                boolean val=Principal.s.getArticulo2(Integer.parseInt(JArticulo.getValueAt(Fila, 0).toString()),
                    Double.parseDouble(JArticulo.getValueAt(Fila, 3).toString()));
                 if(val){
                      //   
                         showMessageDialog(null,"Articulo repetido puede modificar la cantidad en la misma tabla");
                     
                            
                         
                 }else{
                 Principal.jTable3.setModel(this.AddArt(Integer.parseInt(JArticulo.getValueAt(Fila, 0).toString()),(JArticulo.getValueAt(Fila, 1).toString()),
                 Double.parseDouble(JArticulo.getValueAt(Fila, 3).toString()), Principal.da));
                 //Agregar DV
            TableColumn columna = Principal.jTable3.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            Principal.jTable3.doLayout();    
                         
                 //DefaultTableModel dtmEjemplo = new DefaultTableModel(0,
                                                           //  0);
                 //Principal.jTable3.setModel(da);=new JTable(dtmEjemplo){
                 /*public boolean isCellEditable(int rowIndex, int vColIndex) {
                     return false;
                 }};*/
                 
                 }
                 this.setVisible(false);
        } 
    }//GEN-LAST:event_JArticuloMouseClicked

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
         int key = evt.getKeyChar();
        boolean numeros=key >=48 && key <=57;
        boolean punto= key ==46;
        if (!(numeros || punto )){
            evt.consume();
        }
        
    }//GEN-LAST:event_jTextField2KeyTyped

    private void buscarartKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarartKeyReleased
        // TODO add your handling code here:
         String bx="null";
        if (buscarart.getText().isEmpty()){
            reload();
        }else{
            try {
                switch (buscarfilt.getSelectedIndex()){
                    case 0:
                    bx="Articulo";
                    break;
                    case 1:

                    bx="ref";
                    break;
                    case 2:

                    bx="precio";
                    break;
                    case 3:
                    bx="descripcion";
                    break;
                    default:
                }
                System.out.println(bx);
                JArticulo.setDefaultRenderer(Object.class,new RenderTabla());
                JArticulo.setModel(Principal.s.recuperarArt(Principal.c.obtener(),buscarart.getText(),bx));
                TableColumn columna = JArticulo.getColumnModel().getColumn(0);
                columna.setMaxWidth(0);
                columna.setMinWidth(0);
                columna.setPreferredWidth(0);
                 TableColumn columna2 = JArticulo.getColumnModel().getColumn(5);
            TableColumn columna3 = JArticulo.getColumnModel().getColumn(6);
            columna2.setMaxWidth(0);
            columna2.setMinWidth(0);
            columna2.setPreferredWidth(0);
            columna3.setMaxWidth(0);
            columna3.setMinWidth(0);
            columna3.setPreferredWidth(0);
                JArticulo.doLayout();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_buscarartKeyReleased
  public void reload(){
         try {
          
            JArticulo.setModel(Principal.s.GetArticulos(Principal.c.obtener()));
            TableColumn columna = JArticulo.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            TableColumn columna2 = JArticulo.getColumnModel().getColumn(5);
            TableColumn columna3 = JArticulo.getColumnModel().getColumn(6);
            columna2.setMaxWidth(0);
            columna2.setMinWidth(0);
            columna2.setPreferredWidth(0);
            columna3.setMaxWidth(0);
            columna3.setMinWidth(0);
            columna3.setPreferredWidth(0);
            JArticulo.doLayout();
            
            } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddArticulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddArticulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddArticulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddArticulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddArticulo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable JArticulo;
    private javax.swing.JTextField buscarart;
    private javax.swing.JComboBox<String> buscarfilt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
