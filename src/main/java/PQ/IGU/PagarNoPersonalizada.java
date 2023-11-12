/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PQ.IGU;

import PQ.LOGICA.ControladoraLogica;
import PQ.LOGICA.TarjetaNoPersonalizada;
import PQ.LOGICA.controladoraLogicaNoPersonalizadas;
import PQ.PERSISTENCIA.TarjetaNoPersonalizadaJpaController;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Elias Jaramillo
 */
public class PagarNoPersonalizada extends javax.swing.JFrame {

    controladoraLogicaNoPersonalizadas control = new controladoraLogicaNoPersonalizadas();

    /**
     * Creates new form Pagar
     */
    public PagarNoPersonalizada() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDocumentoUno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pagar");

        jLabel2.setText("ingresa tu numero de Tarjeta");

        txtDocumentoUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentoUnoActionPerformed(evt);
            }
        });
        txtDocumentoUno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoUnoKeyTyped(evt);
            }
        });

        jButton1.setText("PAGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REACARGAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(txtDocumentoUno, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jButton1)))))
                .addContainerGap(133, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(161, 161, 161))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(txtDocumentoUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton1)
                .addGap(94, 94, 94)
                .addComponent(jButton2)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDocumentoUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentoUnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoUnoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        RecargaUserNoPersonalizada userRecarga = new RecargaUserNoPersonalizada();
        userRecarga.setVisible(true);
        userRecarga.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (txtDocumentoUno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El Campo Del Documento Esta Vacio",
                    "Error Al Recargar",
                    JOptionPane.WARNING_MESSAGE);
        } else {

            Integer numeroTarjeta = Integer.valueOf(txtDocumentoUno.getText().trim());
            try {
                control.pagarNoPersonalizada(numeroTarjeta);
            } catch (Exception ex) {
                Logger.getLogger(PagarNoPersonalizada.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtDocumentoUno.setText(" ");

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDocumentoUnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoUnoKeyTyped

        int key = evt.getKeyChar();

        if (Character.isDigit(key) && txtDocumentoUno.getText().trim().length() < 15) {
            // INGRESO DE SOLO DIGITOS Y QUE SEAN MENOR A 15 DIGITOS
        } else if (key == 8) {
            // PERMITO TAMBIEN QUE PUEDA BORRAR NUMEROS
        } else {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Este campo solo acepta números y tiene un máximo de 15 dígitos.",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_txtDocumentoUnoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDocumentoUno;
    // End of variables declaration//GEN-END:variables
}
