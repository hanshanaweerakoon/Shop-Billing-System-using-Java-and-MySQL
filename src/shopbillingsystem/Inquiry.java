/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopbillingsystem;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class Inquiry extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    

    public Inquiry() {
        initComponents();
        con = new NewClass().connect();
        tableload("cashaccount", table2, t1, t2, b1, b2);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void debit(String accountName, String ref, String remark, Double rs) {

        try {
            String date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date());
            pst = con.prepareStatement("INSERT INTO " + accountName + " (DebitDate,DebitRef,DebitRemark,DebitRs) VALUES ('" + date + "','" + ref + "','" + remark + "','" + rs + "')");
            pst.execute();

        } catch (Exception e) {

        }
    }

    public void credit(String accountName, String ref, String remark, Double rs) {

        try {
            String date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date());
            pst = con.prepareStatement("INSERT INTO " + accountName + " (CreditDate,CreditRef,CreditRemark,CreditRs) VALUES ('" + date + "','" + ref + "','" + remark + "','" + rs + "')");
            pst.execute();

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

            String z = Integer.toString(Integer.parseInt(rs.getString("AUTO_INCREMENT")));
            return z;

        } catch (Exception e) {
            return "";
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void tableload(String tablename, JTable table, JTextField box1, JTextField box2, JTextField box3, JTextField box4) {
        try {
            String q = "SELECT * FROM " + tablename + "";
            pst = con.prepareStatement(q);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setEnabled(false);

            getTotal(table, box1, box2);

            balance(box1, box2, box3, box4);

        } catch (Exception e) {
            //System.out.println(e);
        }
    }

    /* public void getTotal(JTable table, JTextField total1, JTextField total2) {

        double sum1 = 0;
        double sum2 = 0;
        String s1,s2;
        for (int i = 0; i < table.getRowCount(); i++) {
            s1 = table.getValueAt(i, 3).toString();
            s2 = table.getValueAt(i, 7).toString();
            if (!s1.equals("")) {
                sum1 += Double.parseDouble(s1);
            }
            else if (!s2.equals("")) {
                sum2 += Double.parseDouble(s2);
            }
        }
        total1.setText(Double.toString(sum1));
        total1.setEditable(false);

        total2.setText(Double.toString(sum2));
        total2.setEditable(false);
        
    }*/
    public void getTotal(JTable table, JTextField total1, JTextField total2) {

        double sum1 = 0;
        double sum2 = 0;
        String s1, s2;
        for (int i = 0; i < table.getRowCount(); i++) {
            try {
                s1 = table.getValueAt(i, 3).toString();
                sum1 += Double.parseDouble(s1);
            } catch (Exception e) {
            }
            try {
                s2 = table.getValueAt(i, 7).toString();
                sum2 += Double.parseDouble(s2);
            } catch (Exception e) {
            }

        }
        total1.setText(Double.toString(sum1));
        total1.setEditable(false);

        total2.setText(Double.toString(sum2));
        total2.setEditable(false);

    }

    public void balance(JTextField box1, JTextField box2, JTextField box3, JTextField box4) {

        Double f1 = Double.parseDouble(box1.getText());
        Double f2 = Double.parseDouble(box2.getText());

        if (f1 >= f2) {

            box3.setText(Double.toString(f1 - f2));
            box4.setText("0");
        } else {

            box4.setText(Double.toString(f2 - f1));
            box3.setText("0");
        }
        box3.setEditable(false);
        box4.setEditable(false);

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
        jButton1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        b1 = new javax.swing.JTextField();
        t1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        b2 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        b3 = new javax.swing.JTextField();
        t3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        b4 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        check = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("×");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 255));
        jLabel23.setText("ACCOUNT INQUIRY");
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 240, 40));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Debit");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 50, 30));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Debit Date", "Debit Ref", "Debit Remark", "Debit Rs", "Credit Date", "Credit Ref", "Credit Remark", "Credit Rs"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 1030, 130));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Debit Date", "Debit Ref", "Debit Remark", "Debit Rs", "Credit Date", "Credit Ref", "Credit Remark", "Credit Rs"
            }
        ));
        jScrollPane2.setViewportView(table2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1030, 170));

        jButton2.setBackground(new java.awt.Color(51, 51, 255));
        jButton2.setText("SEARCH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 80, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CASH BOOK");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 90, 30));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Account Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 100, 30));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Credit");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 400, 50, 30));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Balance");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 70, 40));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Debit");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 40, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("________ ACCOUNT");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 150, 30));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Credit");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, 50, 30));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Sub total");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 70, 30));
        jPanel1.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 130, 30));
        jPanel1.add(t1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 130, 30));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Balance");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 70, 40));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Sub total");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 70, 30));
        jPanel1.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 300, 140, 30));
        jPanel1.add(t2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 270, 140, 30));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Balance");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 70, 40));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Sub total");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 70, 30));
        jPanel1.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 110, 30));
        jPanel1.add(t3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 110, 30));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Balance");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 590, 70, 40));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Sub total");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 560, 70, 30));
        jPanel1.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 590, 110, 30));
        jPanel1.add(t4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 560, 110, 30));

        check.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Account", "Purchase Account (Asst)", "Debtors Account (Asst)", "Capital Account (Equt)", "Drawings Account (Equt) ", "Creditors Account (Liblt)", "Income Account (Incm)", "Expenses Account (Expns)" }));
        jPanel1.add(check, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 200, 30));

        jButton3.setBackground(new java.awt.Color(51, 51, 255));
        jButton3.setText("STATEMENT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, 30));

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setText("PRINT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 630, -1, -1));

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setText("PRINT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 340, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String[] list = {"purchaseaccount", "debtorsaccount", "capitalaccount", "drawingsaccount", "creditorsaccount", "incomeaccount", "expensesaccount"};
        String[] l = {"PURCHASE", "DEBTORS", "CAPITAL", "DRAWINGS", "CREDITORS", "INCOME", "EXPENSES"};

        tableload(list[check.getSelectedIndex() - 1], table1, t3, t4, b3, b4);
        jLabel7.setText(l[check.getSelectedIndex() - 1] + " ACCOUNT");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            new statement().setVisible(true);
            jButton1.setVisible(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            table1.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Inquiry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            table2.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Inquiry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Inquiry.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inquiry.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inquiry.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inquiry.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inquiry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField b1;
    public static javax.swing.JTextField b2;
    private javax.swing.JTextField b3;
    private javax.swing.JTextField b4;
    private javax.swing.JComboBox<String> check;
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    // End of variables declaration//GEN-END:variables
}
