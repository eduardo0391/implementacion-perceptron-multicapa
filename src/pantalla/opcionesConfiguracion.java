/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * opcionesConfiguracion.java
 *
 * Created on 19-feb-2014, 2:34:12
 */
package pantalla;

import Clases.PerceptronMulticapa;

/**
 *
 * @author eduardo
 */
public class opcionesConfiguracion extends javax.swing.JFrame {

PerceptronMulticapa unModelo;
    /** Creates new form opcionesConfiguracion */
    public opcionesConfiguracion(PerceptronMulticapa Modelo) {
        unModelo=Modelo;
        String[] opcionesActivadas;
        opcionesActivadas= unModelo.getConfiguracion();
        initComponents();
        if (opcionesActivadas[0].equals("1"))
        opcionEdad.setSelected(true);
        else
        opcionEdad.setSelected(false);
        if(opcionesActivadas[1].equals("1"))
            opcionColegio.setSelected(true);
        else
            opcionColegio.setSelected(false);
        
        if(opcionesActivadas[2].equals("1"))
            opcionProcedencia.setSelected(true);
        else
            opcionProcedencia.setSelected(false);
        if(opcionesActivadas[3].equals("1"))
            opcionSituacionLaboral.setSelected(true);
        else
            opcionSituacionLaboral.setSelected(false);
        if(opcionesActivadas[4].equals("1"))
            opcionEstudioMadre.setSelected(true);
        else
            opcionEstudioMadre.setSelected(false);
        if(opcionesActivadas[5].equals("1"))
            opcionEstudioPadre.setSelected(true);
        else
            opcionEstudioPadre.setSelected(false);
    
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        opcionProcedencia = new javax.swing.JCheckBox();
        opcionColegio = new javax.swing.JCheckBox();
        opcionEdad = new javax.swing.JCheckBox();
        opcionSituacionLaboral = new javax.swing.JCheckBox();
        opcionEstudioMadre = new javax.swing.JCheckBox();
        opcionEstudioPadre = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Datos Censales");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        opcionProcedencia.setSelected(true);
        opcionProcedencia.setText("Procedencia");

        opcionColegio.setSelected(true);
        opcionColegio.setText("Colegio");

        opcionEdad.setSelected(true);
        opcionEdad.setText("Edad");

        opcionSituacionLaboral.setSelected(true);
        opcionSituacionLaboral.setText("Situacion Laboral");

        opcionEstudioMadre.setSelected(true);
        opcionEstudioMadre.setText("Estudio de la madre");

        opcionEstudioPadre.setSelected(true);
        opcionEstudioPadre.setText("Estudio del padre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opcionEdad)
                    .addComponent(opcionColegio)
                    .addComponent(opcionProcedencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opcionEstudioPadre)
                    .addComponent(opcionEstudioMadre)
                    .addComponent(opcionSituacionLaboral))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcionEdad)
                    .addComponent(opcionSituacionLaboral))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcionColegio)
                    .addComponent(opcionEstudioMadre))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcionProcedencia)
                    .addComponent(opcionEstudioPadre))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel2.setText("Elija los atributos que va a incluir en la carga");

        jButton1.setText("Aceptar");
        jButton1.setBorder(null);
        jButton1.setHideActionText(true);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton2)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] configuracionDatosCensales= new String[6];
        configuracionDatosCensales[0]="0";
        configuracionDatosCensales[1]="0";
        configuracionDatosCensales[2]="0";
        configuracionDatosCensales[3]="0";
        configuracionDatosCensales[4]="0";
        configuracionDatosCensales[5]="0";
        if(opcionEdad.isSelected())
           configuracionDatosCensales[0]="1";
        if(opcionColegio.isSelected())
           configuracionDatosCensales[1]="1";
        if(opcionProcedencia.isSelected())
           configuracionDatosCensales[2]="1";
        if(opcionSituacionLaboral.isSelected())
           configuracionDatosCensales[3]="1";
        if(opcionEstudioMadre.isSelected())
           configuracionDatosCensales[4]="1";
        if(opcionEstudioPadre.isSelected())
           configuracionDatosCensales[5]="1";
        unModelo.setConfiguracion(configuracionDatosCensales);
       
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox opcionColegio;
    private javax.swing.JCheckBox opcionEdad;
    private javax.swing.JCheckBox opcionEstudioMadre;
    private javax.swing.JCheckBox opcionEstudioPadre;
    private javax.swing.JCheckBox opcionProcedencia;
    private javax.swing.JCheckBox opcionSituacionLaboral;
    // End of variables declaration//GEN-END:variables
}
