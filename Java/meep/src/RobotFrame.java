

import java.awt.event.WindowAdapter;
import javax.media.opengl.GLCanvas;

/*
 * RobotFrame.java
 *
 * This program illustrates how to create and manipulate  a three part robot 
 * arm. The code structure is designed based on a schene graph hierarchy.
 */
/**
 *
 * @author  gorr
 */
public class RobotFrame extends javax.swing.JFrame {

    RobotListener robot;
    GLCanvas canvas;

    /** Creates new form RobotFrame */
    public RobotFrame() {
        initComponents();
        canvas = new GLCanvas();
        robot = new RobotListener(canvas);
        canvas.addGLEventListener(robot);
        canvas.addKeyListener(robot);
        this.setSize(640, 480);
        this.getContentPane().add(canvas, java.awt.BorderLayout.CENTER);
        this.pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rotateBaseButton = new javax.swing.JButton();
        rotBottom = new javax.swing.JButton();
        rotMidButton = new javax.swing.JButton();
        rotTopButton = new javax.swing.JButton();
        dirButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rotateBaseButton.setText("rotBase");
        rotateBaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateBaseButtonActionPerformed(evt);
            }
        });
        jPanel1.add(rotateBaseButton);

        rotBottom.setText("rotBottom");
        rotBottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotBottomActionPerformed(evt);
            }
        });
        jPanel1.add(rotBottom);

        rotMidButton.setText("rotMid");
        rotMidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotMidButtonActionPerformed(evt);
            }
        });
        jPanel1.add(rotMidButton);

        rotTopButton.setText("rotTop");
        rotTopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotTopButtonActionPerformed(evt);
            }
        });
        jPanel1.add(rotTopButton);

        dirButton.setText("+");
        dirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dirButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dirButton);

        resetButton.setText("reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        jPanel1.add(resetButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rotTopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotTopButtonActionPerformed
        robot.changeTop(); //robot.rotBottom += robot.dAngle;
        canvas.display();
    }//GEN-LAST:event_rotTopButtonActionPerformed

    private void dirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dirButtonActionPerformed
        robot.dAngle *= -1;
        if (robot.dAngle > 0) {
            dirButton.setText("+");
        } else {
            dirButton.setText("-");
        }
    }//GEN-LAST:event_dirButtonActionPerformed

    private void rotMidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotMidButtonActionPerformed
        robot.changeMid();
        canvas.display();
    }//GEN-LAST:event_rotMidButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        robot.rotBase = 0;
        robot.rotBottom = 0;
        robot.rotMid = 0;
        robot.rotTop = 0;
        robot.dAngle = Math.abs(robot.dAngle);
        dirButton.setText("+");
        canvas.display();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void rotBottomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotBottomActionPerformed
        robot.changeBottom();
        canvas.display();
    }//GEN-LAST:event_rotBottomActionPerformed

    private void rotateBaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateBaseButtonActionPerformed
        robot.rotBase += robot.dAngle;
        canvas.display();
    }//GEN-LAST:event_rotateBaseButtonActionPerformed

    /**
     * @param args the comrotBottome arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RobotFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dirButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton rotBottom;
    private javax.swing.JButton rotMidButton;
    private javax.swing.JButton rotTopButton;
    private javax.swing.JButton rotateBaseButton;
    // End of variables declaration//GEN-END:variables
}
