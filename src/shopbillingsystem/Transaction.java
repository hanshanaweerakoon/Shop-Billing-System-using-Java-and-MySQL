/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopbillingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class Transaction extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Transaction() {
        initComponents();
        con = new NewClass().connect();
    }

//*****************************************************************************************************************    
//*****************************************************************************************************************     
    public void reset(JTextField x1, JTextField x2, JTextField x3, JTextField x4, JTextArea x5) {
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            x1.setText("");
            x2.setText("");
            x3.setText("");
            x4.setText("");
            x5.setText("");
        }
    }

    public void print(JTextArea x) {
        try {
            x.print();

        } catch (Exception e) {
        }
    }

    public void Header(JTextArea area, JTextField field, String tablename) {
        String x = String.format("%-30s%15s%15s", "Date", "Price", "Remark");
        area.setText("                   WELCOME TO " + bussinessName() + " STORES\n\nInvoice No. : " + autoIncrement(tablename) + " \nName : " + field.getText()
                + "\n======================================= \n" + x + "\n=======================================");

    }

    public void Body(String x1, String x2, String x3, JTextArea area) {
        String x = String.format("\n%-" + Integer.toString(37 - x1.length()) + "s%" + Integer.toString(19 - x2.length()) + "s%" + Integer.toString(20 - x3.length()) + "s", x1, x2, x3);
        area.setText(area.getText() + x);

        String date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date());
        area.setText(area.getText() + "\n=======================================\n=======================================\n\n" + date + "\n\n              COME AGAIN, THANK YOU !");
        area.setEditable(false);
    }

    public void add(JTextField e1, JTextField e2, JTextField e3, JTextField e4, JTextArea area, String tableName) {

        String updated = e1.getText();
        String name = e2.getText();
        String price = e3.getText();
        String remark = e4.getText();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String auto = new Inquiry().autoIncrement(tableName);
        try {

            int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
            if (dialog == 0) {

                pst = con.prepareStatement("INSERT INTO " + tableName + " (DateTime,Name,Total,Cash,UpdatedINO,Remark) VALUES ('" + date + "','" + name + "','" + price + "','" + "1" + "','" + updated + "','" + remark + "')");
                pst.execute();
                if (!updated.equals("")) {

                    pst = con.prepareStatement("UPDATE " + tableName + " SET Cash='" + "1" + "' WHERE Code='" + updated + "'");
                    pst.execute();

                    if (tableName.equals("billhistory")) {
                        //debit(cash acc)-credit(debtors acc)
                        new Inquiry().debit("cashaccount", auto, name, Double.parseDouble(price));
                        new Inquiry().credit("debtorsaccount", auto, name, Double.parseDouble(price));
                    } else {

                        //debit(creditors acc)-credit(cash acc)
                        new Inquiry().debit("creditorsaccount", auto, name, Double.parseDouble(price));
                        new Inquiry().credit("cashaccount", auto, name, Double.parseDouble(price));

                    }
                } else {
                    if (tableName.equals("billhistory")) {
                        if ("capital".equals(remark)) {
                            //debit(cash acc)-credit(capital acc)
                            new Inquiry().debit("cashaccount", auto, remark, Double.parseDouble(price));
                            new Inquiry().credit("capitalaccount", auto, remark, Double.parseDouble(price));

                        } else {
                            //debit(cash acc)-credit(income acc)
                            new Inquiry().debit("cashaccount", auto, remark, Double.parseDouble(price));
                            new Inquiry().credit("incomeaccount", auto, remark, Double.parseDouble(price));

                        }
                    }else{
                    
                        
                        
                        if ("drawings".equals(remark)) {
                            //debit(drawings acc)-credit(cash acc)
                            new Inquiry().debit("drawingsaccount", auto, remark, Double.parseDouble(price));
                            new Inquiry().credit("cashaccount", auto, remark, Double.parseDouble(price));

                        } else {
                            //debit(expenses acc)-credit(cash acc)
                            new Inquiry().debit("expensesaccount", auto, remark, Double.parseDouble(price));
                            new Inquiry().credit("cashaccount", auto, remark, Double.parseDouble(price));

                        }
                        
                        
                        
                        
                        
                        
                    
                    }
                }

                Header(area, e2, tableName);
                Body(date, price, remark, area);

                print(area);

                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
            }

        } catch (Exception e) {
        }

    }

    public String autoIncrement(String tableName) {
        try {

            PreparedStatement pst2 = con.prepareStatement("SET @@SESSION.information_schema_stats_expiry=0");
            pst2.execute();

            pst = con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME='" + tableName + "'");
            rs = pst.executeQuery();
            rs.next();

            String z = Integer.toString(Integer.parseInt(rs.getString("AUTO_INCREMENT")) - 1);
            return z;

        } catch (Exception e) {
            return "";
        }

    }

    public String bussinessName() {

        try {
            pst = con.prepareStatement("SELECT Name FROM details");
            rs = pst.executeQuery();
            rs.next();
            return rs.getString("Name");

        } catch (Exception e) {
            return "";
        }

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
        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        t3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        area1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        t4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        f1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        f3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        f2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        area2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        f4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 255));
        jLabel23.setText("DEPOSIT");
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 6, -1, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Invoice / Bill No.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, 30));

        t1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t1KeyPressed(evt);
            }
        });
        jPanel1.add(t1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 280, 30));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Price");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 100, 30));

        t3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t3KeyReleased(evt);
            }
        });
        jPanel1.add(t3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 280, 30));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Remark");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 100, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, 30));

        t2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t2KeyPressed(evt);
            }
        });
        jPanel1.add(t2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 280, 30));

        area1.setColumns(20);
        area1.setRows(5);
        jScrollPane1.setViewportView(area1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 320, 240));

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("NEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 140, 30));

        jButton2.setBackground(new java.awt.Color(51, 51, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ENTER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, 30));

        jButton3.setBackground(new java.awt.Color(51, 51, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("PRINT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, 30));

        jButton7.setBackground(new java.awt.Color(102, 255, 102));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("×");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, -1));

        t4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t4KeyPressed(evt);
            }
        });
        jPanel1.add(t4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 280, 80));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 51, 51));
        jLabel24.setText("WITHDRAW");
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 6, -1, 30));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Invoice / Bill No.");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, 30));

        f1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                f1KeyPressed(evt);
            }
        });
        jPanel2.add(f1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 280, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Price");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 100, 30));

        f3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                f3KeyPressed(evt);
            }
        });
        jPanel2.add(f3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 280, 30));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Remark");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 100, 30));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Name");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, 30));

        f2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                f2KeyPressed(evt);
            }
        });
        jPanel2.add(f2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 280, 30));

        area2.setColumns(20);
        area2.setRows(5);
        jScrollPane3.setViewportView(area2);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 320, 240));

        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setText("NEW");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 140, 30));

        jButton5.setBackground(new java.awt.Color(255, 51, 51));
        jButton5.setText("ENTER");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton5KeyPressed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, 30));

        jButton6.setBackground(new java.awt.Color(255, 51, 51));
        jButton6.setText("PRINT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, 30));

        f4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                f4KeyPressed(evt);
            }
        });
        jPanel2.add(f4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 280, 90));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reset(t1, t2, t3, t4, area1);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        reset(f1, f2, f3, f4, area2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            print(area1);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            print(area2);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        add(t1, t2, t3, t4, area1, "billhistory");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void t1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1KeyPressed
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 40) {
            t2.requestFocus();
        }
    }//GEN-LAST:event_t1KeyPressed

    private void t2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2KeyPressed
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 40) {
            t3.requestFocus();
        } else if (evt.getKeyCode() == 38) {
            t1.requestFocus();
        }
    }//GEN-LAST:event_t2KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        if (evt.getKeyCode() == 10) {
            add(t1, t2, t3, t4, area1, "billhistory");
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void t3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyPressed
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 40) {
            t4.requestFocus();
        } else if (evt.getKeyCode() == 38) {
            t2.requestFocus();
        }
    }//GEN-LAST:event_t3KeyPressed

    private void t3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyReleased

    }//GEN-LAST:event_t3KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        add(f1, f2, f3, f4, area2, "stockhistory");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        if (evt.getKeyCode() == 10) {
            add(f1, f2, f3, f4, area2, "stockhistory");
        }
    }//GEN-LAST:event_jButton5KeyPressed

    private void f1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f1KeyPressed
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 40) {
            f2.requestFocus();
        }
    }//GEN-LAST:event_f1KeyPressed

    private void f2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f2KeyPressed
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 40) {
            f3.requestFocus();
        } else if (evt.getKeyCode() == 38) {
            f1.requestFocus();
        }
    }//GEN-LAST:event_f2KeyPressed

    private void f3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f3KeyPressed
        if (evt.getKeyCode() == 10 || evt.getKeyCode() == 40) {
            f4.requestFocus();
        } else if (evt.getKeyCode() == 38) {
            f2.requestFocus();
        }
    }//GEN-LAST:event_f3KeyPressed

    private void t4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyPressed
        if (evt.getKeyCode() == 38) {
            t3.requestFocus();
        } else if (evt.getKeyCode() == 10) {
            jButton2.requestFocus();
        }
    }//GEN-LAST:event_t4KeyPressed

    private void f4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f4KeyPressed
        if (evt.getKeyCode() == 38) {
            f3.requestFocus();
        } else if (evt.getKeyCode() == 10) {
            jButton5.requestFocus();
        }
    }//GEN-LAST:event_f4KeyPressed

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
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area1;
    private javax.swing.JTextArea area2;
    private javax.swing.JTextField f1;
    private javax.swing.JTextField f2;
    private javax.swing.JTextField f3;
    private javax.swing.JTextField f4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    // End of variables declaration//GEN-END:variables
}
