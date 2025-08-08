/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopbillingsystem;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class AddItem extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public AddItem() {
        initComponents();
        con = new NewClass().connect();
        tableload();
    }

//*****************************************************************************************************************    
//*****************************************************************************************************************    
    public void reset() {

        massege.setText("");
        namebox.setText("");
        unitbox.setText("");
        pricebox.setText("");
        detailsbox.setText("");
        codebox.setText("");
        tableload();

    }

    public void insert() {
        massege.setText("");
        String code = codebox.getText();
        String name = namebox.getText();
        String unit = unitbox.getText();
        String price = pricebox.getText();
        String details = detailsbox.getText();

        try {
            if (!name.equals("") && !unit.equals("")) {
                int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
                if (dialog == 0) {
                    pst = con.prepareStatement("INSERT INTO item VALUES ('" + code + "','" + name + "','" + unit + "','" + price + "','" + details + "')");
                    pst.execute();

                    PreparedStatement pst1 = con.prepareStatement("INSERT INTO stock VALUES ('" + code + "','" + 0 + "','"+0+"')");
                    pst1.execute();

                    reset();
                    codebox.requestFocus();

                }
            } else {
                massege.setText("Something Went Wrong !");
            }
        } catch (Exception e) {
            massege.setText("Something Went Wrong !");
            //System.out.println(e);
        }

    }

    public void tableload() {
        try {
            String q = "SELECT * FROM item";
            pst = con.prepareStatement(q);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {

        }
    }

    public void search(JTextField o1, JTextField o2, String y) {

        o1.setText("");
        String x = o2.getText();

        try {

            pst = con.prepareStatement("SELECT * FROM item WHERE " + y + " LIKE '%" + x + "%'");
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            massege.setText("Something Went Wrong !");
        }

    }

    public void update() {

        massege.setText("");
        String code = codebox.getText();
        String name = namebox.getText();
        String unit = unitbox.getText();
        String price = pricebox.getText();
        String details = detailsbox.getText();

        try {
            if (!name.equals("") && !unit.equals("")) {
                int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
                if (dialog == 0) {
                    pst = con.prepareStatement("UPDATE item SET Name='" + name + "', Unit='" + unit + "',Price='" + price + "',Details='" + details + "' WHERE Code='" + code + "'");
                    pst.execute();
                    reset();
                }

            } else {
                massege.setText("Something Went Wrong !");
            }
        } catch (Exception e) {
            massege.setText("Something Went Wrong !");
        }

    }

    public void delete() {

        String code = codebox.getText();

        try {

            int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
            if (dialog == 0) {

                pst = con.prepareStatement("DELETE FROM item WHERE Code='" + code + "'");
                pst.execute();

                PreparedStatement pst1 = con.prepareStatement("DELETE FROM stock WHERE Code='" + code + "'");
                pst1.execute();

                reset();

            }

        } catch (Exception e) {
            massege.setText("Something Went Wrong !");
        }

    }

//*****************************************************************************************************************    
//*****************************************************************************************************************      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        namebox = new javax.swing.JTextField();
        unitbox = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pricebox = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        detailsbox = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        searchcode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        searchname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        massege = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        insertb = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        codebox = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 76, -1, -1));

        namebox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameboxKeyPressed(evt);
            }
        });
        getContentPane().add(namebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 190, 30));

        unitbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unitboxKeyPressed(evt);
            }
        });
        getContentPane().add(unitbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 190, 30));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Unit");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 60, 30));

        pricebox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                priceboxKeyPressed(evt);
            }
        });
        getContentPane().add(pricebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 190, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Price");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 60, 30));

        detailsbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                detailsboxKeyPressed(evt);
            }
        });
        getContentPane().add(detailsbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 190, 30));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Details");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 60, 30));

        table.setBackground(new java.awt.Color(102, 102, 102));
        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table.setForeground(new java.awt.Color(255, 255, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Unit", "Price", "Details"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 560, 320));

        searchcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchcodeKeyPressed(evt);
            }
        });
        getContentPane().add(searchcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 220, 30));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Code");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 60, 30));

        searchname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchnameKeyPressed(evt);
            }
        });
        getContentPane().add(searchname, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 220, 30));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Name");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 60, 30));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 60, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Code");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 60, 30));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 80, 20));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 170, 20, 70));

        massege.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(massege, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 240, 30));

        jButton5.setBackground(new java.awt.Color(51, 51, 255));
        jButton5.setText("SALES");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 80, 30));

        jButton12.setBackground(new java.awt.Color(51, 51, 255));
        jButton12.setText("REFRESH");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 90, 30));

        jButton13.setBackground(new java.awt.Color(51, 51, 255));
        jButton13.setText("STOCK");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 80, 30));

        jButton2.setBackground(new java.awt.Color(51, 51, 255));
        jButton2.setText("SEARCH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, -1, 30));

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, -1, 30));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        insertb.setBackground(new java.awt.Color(51, 51, 255));
        insertb.setText("INSERT");
        insertb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertbActionPerformed(evt);
            }
        });
        insertb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                insertbKeyPressed(evt);
            }
        });
        jPanel1.add(insertb, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 90, 30));

        jButton10.setBackground(new java.awt.Color(51, 51, 255));
        jButton10.setText("UPDATE");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 80, 30));

        jButton11.setBackground(new java.awt.Color(51, 51, 255));
        jButton11.setText("DELETE");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 80, 30));

        codebox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codeboxKeyPressed(evt);
            }
        });
        jPanel1.add(codebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 190, 30));

        jButton6.setBackground(new java.awt.Color(102, 255, 102));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("×");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("ADD ITEMS");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 140, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 380, 460));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 560, 140));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        reset();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        update();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void insertbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertbActionPerformed

        insert();
    }//GEN-LAST:event_insertbActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        int r = table.getSelectedRow();

        String code = table.getValueAt(r, 0).toString();
        String name = table.getValueAt(r, 1).toString();
        String unit = table.getValueAt(r, 2).toString();
        String price = table.getValueAt(r, 3).toString();
        String details = table.getValueAt(r, 4).toString();

        codebox.setText(code);
        namebox.setText(name);
        unitbox.setText(unit);
        pricebox.setText(price);
        detailsbox.setText(details);


    }//GEN-LAST:event_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        search(searchname, searchcode, "Code");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        search(searchcode, searchname, "Name");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchcodeKeyPressed

        if (evt.getKeyCode() == 10) {
            search(searchname, searchcode, "Code");
        }

    }//GEN-LAST:event_searchcodeKeyPressed

    private void searchnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchnameKeyPressed

        if (evt.getKeyCode() == 10) {
            search(searchcode, searchname, "Name");
        }
    }//GEN-LAST:event_searchnameKeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        delete();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void codeboxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeboxKeyPressed

        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 40) {
            namebox.requestFocus();
        }

    }//GEN-LAST:event_codeboxKeyPressed

    private void nameboxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameboxKeyPressed

        int key = evt.getKeyCode();

        if (key == 38) {
            codebox.requestFocus();
        } else if (key == 10 || key == 40) {
            unitbox.requestFocus();
        }

    }//GEN-LAST:event_nameboxKeyPressed

    private void unitboxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitboxKeyPressed

        int key = evt.getKeyCode();

        if (key == 38) {
            namebox.requestFocus();
        } else if (key == 10 || key == 40) {
            pricebox.requestFocus();
        }

    }//GEN-LAST:event_unitboxKeyPressed

    private void priceboxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceboxKeyPressed

        int key = evt.getKeyCode();

        if (key == 38) {
            unitbox.requestFocus();
        } else if (key == 10 || key == 40) {
            detailsbox.requestFocus();
        }

    }//GEN-LAST:event_priceboxKeyPressed

    private void detailsboxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_detailsboxKeyPressed

        int key = evt.getKeyCode();

        if (key == 38) {
            pricebox.requestFocus();
        } else if (key == 10) {
            insertb.requestFocus();
        }

    }//GEN-LAST:event_detailsboxKeyPressed

    private void insertbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_insertbKeyPressed

        int key = evt.getKeyCode();

        if (key == 10) {
            insert();
        }

    }//GEN-LAST:event_insertbKeyPressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        new Bill().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new Bill().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codebox;
    public static javax.swing.JTextField detailsbox;
    private javax.swing.JButton insertb;
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    public static javax.swing.JButton jButton12;
    public static javax.swing.JButton jButton13;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel massege;
    public static javax.swing.JTextField namebox;
    public static javax.swing.JTextField pricebox;
    public static javax.swing.JTextField searchcode;
    public static javax.swing.JTextField searchname;
    private javax.swing.JTable table;
    public static javax.swing.JTextField unitbox;
    // End of variables declaration//GEN-END:variables
}
