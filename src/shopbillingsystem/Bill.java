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

public class Bill extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList<String> list1 = new ArrayList();
    ArrayList<String> list2 = new ArrayList();
    

    public Bill() {
        initComponents();
        con = new NewClass().connect();
        administor(jButton18, jButton14, jButton17);

    }

    public void administor(JButton... list) {

        try {
            pst = con.prepareStatement("SELECT User FROM details ");
            rs = pst.executeQuery();
            rs.next();
            String user = rs.getString("User");

            pst = con.prepareStatement("SELECT Administration FROM login WHERE username ='" + user + "'");
            rs = pst.executeQuery();
            rs.next();
            String a = rs.getString("Administration");

            if (a.equals("0")) {
                for (JButton i : list) {
                    i.setVisible(false);
                }
            }

        } catch (Exception e) {
        }
    }

//*****************************************************************************************************************    
//*****************************************************************************************************************     
    public void tableload1() {
        try {
            String q = "SELECT * FROM stockinvoice";
            pst = con.prepareStatement(q);
            rs = pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {

        }
    }

    public void invoiceHeader() {
        String x = String.format("%-30s%15s%15s", "Product", "Qty.", "Price");
        //String date = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
        area2.setText("                   WELCOME TO " + new Transaction().bussinessName() + " STORES\n\nInvoice No. : " + new Inquiry().autoIncrement("stockhistory") + " \nDlvr. Name : " + dlvrname.getText()
                + "\n======================================= \n" + x + "\n=======================================");

    }

    public String productName(String code) {
        try {
            pst = con.prepareStatement("SELECT Name FROM item WHERE Code='" + code + "'");
            rs = pst.executeQuery();
            rs.next();
            return rs.getString("Name").toString();
        } catch (Exception e) {
            return "";
        }

    }

    public String productUnit(String code) {
        try {
            pst = con.prepareStatement("SELECT Unit FROM item WHERE Code='" + code + "'");
            rs = pst.executeQuery();
            rs.next();
            return rs.getString("Unit").toString();
        } catch (Exception e) {
            return "";
        }

    }

    public void add1() {

        String code = code1.getText();
        String name = productName(code1.getText());
        String qty = qty1.getText();
        String unitprice = unitprice1.getText();
        String price = price1.getText();

        try {
            int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
            if (dialog == 0) {
                pst = con.prepareStatement("INSERT INTO stockinvoice VALUES ('" + code + "','" + name + "','" + qty + "','" + unitprice + "','" + price + "')");
                pst.execute();
                list1.add(code);

                code1.setText("");
                qty1.setText("");
                unit1.setText("");
                unitprice1.setText("");
                price1.setText("");

                tableload1();
                code1.requestFocus();
            }
        } catch (Exception e) {
        }

    }

    public void delete1() {

        try {

            pst = con.prepareStatement("DELETE FROM stockinvoice");
            pst.execute();
            tableload1();
            list1.clear();

        } catch (Exception e) {
        }

    }

    public void invoiceBody(String x1, String x2, String x3) {
        String x = String.format("\n%-" + Integer.toString(37 - x1.length()) + "s%" + Integer.toString(19 - x2.length()) + "s%" + Integer.toString(20 - x3.length()) + "s", x1, x2, x3);
        area2.setText(area2.getText() + x);

    }

    public void final1() {

        invoiceHeader();
        Double sum = 0.0;
        for (String code : list1) {

            try {

                pst = con.prepareStatement("SELECT * FROM stockinvoice WHERE Code='" + code + "'");
                rs = pst.executeQuery();
                rs.next();
                String name = rs.getString("Name");
                String qty = rs.getString("Qty.");
                String price = rs.getString("Price");
                String unitprice = rs.getString("Unit Price");

                sum += Double.parseDouble(price);

                invoiceBody(name, qty, price);

                pst = con.prepareStatement("SELECT Stock FROM stock WHERE Code='" + code + "'");
                rs = pst.executeQuery();
                rs.next();
                Double s = Double.parseDouble(rs.getString("Stock"));
                String q = Double.toString(s + Double.parseDouble(qty));
                pst = con.prepareStatement("UPDATE stock SET Stock='" + q + "',UnitPrice='"+unitprice+"' WHERE Code='" + code + "'");
                pst.execute();

            } catch (Exception e) {
            }

        }
        String date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date());
        area2.setText(area2.getText() + "\n=======================================\n                                                     Total = "
                + sum + "\n=======================================\n\n" + date + "\n\n              COME AGAIN, THANK YOU !");
        area2.setEditable(false);

        try {
            String cash;
            String auto = new Inquiry().autoIncrement("stockhistory");
            if (checkbox.isSelected()) {
                cash = "1";
                //debit(purchase acc)-cedit(cash acc)
                new Inquiry().debit("purchaseaccount", auto, dlvrname.getText(), sum);
                new Inquiry().credit("cashaccount", auto, dlvrname.getText(), sum);
            } else {
                cash = "0";
                //debit(purchase acc)-credit(creditors acc)
                new Inquiry().debit("purchaseaccount", auto, dlvrname.getText(), sum);
                new Inquiry().credit("creditorsaccount", auto, dlvrname.getText(), sum);
            }
            pst = con.prepareStatement("INSERT INTO stockhistory (DateTime,Name,Total,Cash,UpdatedINO,Remark) VALUES ('" + date + "','" + dlvrname.getText() + "','" + sum + "','" + cash + "','" + "" + "','" + "" + "')");
            pst.execute();

        } catch (Exception e) {
            //System.out.println(e);
        }

        delete1();
        dlvrname.setText("");
        invoiceno.setText("");
        checkbox.setSelected(false);

    }

    public void print1() {

        try {

            area2.print();

        } catch (Exception e) {
        }

    }

//*****************************************************************************************************************    
//*****************************************************************************************************************
    public Double productUnitPrice(String code) {
        try {
            pst = con.prepareStatement("SELECT Price FROM item WHERE Code='" + code + "'");
            rs = pst.executeQuery();
            rs.next();
            return Double.parseDouble(rs.getString("Price").toString());
        } catch (Exception e) {
            return 0.0;
        }

    }

    public void add2() {

        String code = code2.getText();
        String name = productName(code2.getText());
        String qty = qty2.getText();
        String unitprice = unitprice2.getText();
        String price = price2.getText();

        try {
            int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
            if (dialog == 0) {
                pst = con.prepareStatement("INSERT INTO bills VALUES ('" + code + "','" + name + "','" + qty + "','" + unitprice + "','" + price + "')");
                pst.execute();
                list2.add(code);

                code2.setText("");
                qty2.setText("");
                unit2.setText("");
                unitprice2.setText("");
                price2.setText("");

                tableload2();
                code2.requestFocus();
            }
        } catch (Exception e) {
        }

    }

    public void tableload2() {
        try {
            String q = "SELECT * FROM bills";
            pst = con.prepareStatement(q);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {

        }
    }

    public void print2() {

        try {

            area1.print();

        } catch (Exception e) {
        }

    }

    public void delete2() {

        try {

            pst = con.prepareStatement("DELETE FROM bills");
            pst.execute();
            tableload2();
            list2.clear();

        } catch (Exception e) {
        }

    }

    public void billHeader() {
        String x = String.format("%-20s%10s%10s%10s", "Product", "Qty.", "Unit price", "Price");
        area1.setText("                   WELCOME TO " + new Transaction().bussinessName() + " STORES\n\nBill No. : " + new Inquiry().autoIncrement("billhistory") + " \nCost. Name : " + costname.getText()
                + "\n======================================= \n" + x + "\n=======================================");

    }

    public void billBody(String x1, String x2, String x3, String x4) {
        String x = String.format("\n%-" + Integer.toString(27 - x1.length()) + "s%" + Integer.toString(14 - x2.length()) + "s%" + Integer.toString(20 - x3.length()) + "s%" + Integer.toString(15 - x4.length()) + "s", x1, x2, x3, x4);
        area1.setText(area1.getText() + x);

    }

    public void final2() {

        billHeader();
        Double sum= 0.0;
        Double income= 0.0;
        
        for (String code : list2) {

            try {
                pst = con.prepareStatement("SELECT * FROM bills WHERE Code='" + code + "'");
                rs = pst.executeQuery();
                rs.next();
                String name = rs.getString("Name");
                String qty = rs.getString("Qty.");
                String unitprice = rs.getString("Unit Price");
                String price = rs.getString("Price");

                ////////////////////////////////////////////////////////////////////////////////
                pst = con.prepareStatement("SELECT Stock,UnitPrice FROM stock WHERE Code='" + code + "'");
                rs = pst.executeQuery();
                rs.next();
                Double s = Double.parseDouble(rs.getString("Stock"));
                Double a = s - Double.parseDouble(qty);
                Double unitp = Double.parseDouble(rs.getString("UnitPrice"));
                if (a >= 0.0) {

                    sum += Double.parseDouble(price);
                    
                    income+=((Double.parseDouble(unitprice)-unitp)*Double.parseDouble(qty));

                    billBody(name, qty, unitprice, price);

                    String q = Double.toString(a);
                    pst = con.prepareStatement("UPDATE stock SET Stock='" + q + "' WHERE Code='" + code + "'");
                    pst.execute();

                }
                ////////////////////////////////////////////////////////////////////////////////

            } catch (Exception e) {
            }

        }
        String date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date());
        area1.setText(area1.getText() + "\n=======================================\n                                                     Total = "
                + sum + "\n=======================================\n\n" + date + "\n\n              COME AGAIN, THANK YOU !");
        area1.setEditable(false);

        try {
            String cash;
            String auto = new Inquiry().autoIncrement("billhistory");
            if (jCheckBox1.isSelected()) {
                
                cash = "0";
                //debit(debtors acc)-credit(purchase acc)-credit(income acc)

                new Inquiry().debit("debtorsaccount", auto, costname.getText(), sum);
                new Inquiry().credit("purchaseaccount", auto, costname.getText(), sum-income);
                new Inquiry().credit("incomeaccount", auto, costname.getText(), income);

            } else {
                cash = "1";
                //debit(cash acc)-credit(purchase acc)-credit(income acc)
                
                new Inquiry().debit("cashaccount", auto, costname.getText(), sum);
                new Inquiry().credit("purchaseaccount", auto, costname.getText(), sum-income);
                new Inquiry().credit("incomeaccount", auto, costname.getText(), income);

            }
            pst = con.prepareStatement("INSERT INTO billhistory (DateTime,Name,Total,Cash,UpdatedINO,Remark) VALUES ('" + date + "','" + costname.getText() + "','" + sum + "','" + cash + "','" + "" + "','" + "" + "')");
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

        delete2();
        costname.setText("");
        billno.setText("");
        jCheckBox1.setSelected(false);

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

        jLabel1 = new javax.swing.JLabel();
        code2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        qty2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        unit2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        price2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        costname = new javax.swing.JTextField();
        billno = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        area1 = new javax.swing.JTextArea();
        unitprice2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        qty1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        unit1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        price1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dlvrname = new javax.swing.JTextField();
        invoiceno = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        area2 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        code1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        unitprice1 = new javax.swing.JTextField();
        checkbox = new javax.swing.JCheckBox();
        jButton13 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Code");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, 30));

        code2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                code2KeyPressed(evt);
            }
        });
        getContentPane().add(code2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 140, 30));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Qty.");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 40, 30));

        qty2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qty2KeyPressed(evt);
            }
        });
        getContentPane().add(qty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 130, 30));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Unit Price (Rs.)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 100, 30));

        unit2.setForeground(new java.awt.Color(255, 255, 255));
        unit2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(unit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 30, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Price (Rs.)");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 60, 30));

        price2.setForeground(new java.awt.Color(255, 255, 255));
        price2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, 120, 30));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cust. Name");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, 30));

        costname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                costnameKeyPressed(evt);
            }
        });
        getContentPane().add(costname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 310, 30));

        billno.setForeground(new java.awt.Color(255, 255, 255));
        billno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(billno, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 120, 30));

        table1.setBackground(new java.awt.Color(102, 102, 102));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Qty.", "Unit Price", "Price"
            }
        ));
        jScrollPane1.setViewportView(table1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 680, 210));

        area1.setColumns(20);
        area1.setRows(5);
        jScrollPane2.setViewportView(area1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, 300, 340));

        unitprice2.setForeground(new java.awt.Color(255, 255, 255));
        unitprice2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(unitprice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 120, 30));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Code");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 40, 30));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Qty.");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 40, 30));

        qty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qty1KeyPressed(evt);
            }
        });
        getContentPane().add(qty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 130, 30));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Unit Price (Rs.)");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 90, 30));

        unit1.setForeground(new java.awt.Color(255, 255, 255));
        unit1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(unit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 480, 30, 30));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Price (Rs.)");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, 60, 30));

        price1.setForeground(new java.awt.Color(255, 255, 255));
        price1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 120, 30));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Dlvr. Name");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 80, 30));

        dlvrname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dlvrnameKeyPressed(evt);
            }
        });
        getContentPane().add(dlvrname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 310, 30));

        invoiceno.setForeground(new java.awt.Color(255, 255, 255));
        invoiceno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(invoiceno, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, 120, 30));

        area2.setColumns(20);
        area2.setRows(5);
        jScrollPane4.setViewportView(area2);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 420, 300, 260));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 240, 10, 100));

        code1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                code1KeyPressed(evt);
            }
        });
        getContentPane().add(code1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 140, 30));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton4.setText("×");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, -30, 40, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 255));
        jLabel23.setText("SALES BILLS");
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 150, 30));

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("×");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, -1));

        jButton5.setBackground(new java.awt.Color(51, 51, 255));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("TRANSACTION");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, -1, 30));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Bill No.");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 50, 30));

        jButton12.setBackground(new java.awt.Color(51, 51, 255));
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("CALCULATOR");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, 190, 30));

        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Debt");
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 60, 30));

        jButton6.setBackground(new java.awt.Color(51, 51, 255));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("PRINT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 80, 30));

        jButton8.setBackground(new java.awt.Color(51, 51, 255));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("CLEAR ALL");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(788, 190, 100, 30));

        jButton2.setBackground(new java.awt.Color(51, 51, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ADD");
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
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 80, 30));

        jButton3.setBackground(new java.awt.Color(51, 51, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("FINAL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, 100, 30));

        jButton14.setBackground(new java.awt.Color(51, 51, 255));
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("REPORTS");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 90, 30));

        jButton15.setBackground(new java.awt.Color(102, 255, 102));
        jButton15.setForeground(new java.awt.Color(0, 0, 0));
        jButton15.setText("LOGOUT");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jButton16.setBackground(new java.awt.Color(51, 51, 255));
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("ITEMS");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 70, 30));

        jButton17.setBackground(new java.awt.Color(51, 51, 255));
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("INQUIRY");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 310, 90, 30));

        jButton18.setBackground(new java.awt.Color(102, 255, 102));
        jButton18.setForeground(new java.awt.Color(0, 0, 0));
        jButton18.setText("SETTING");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1220, 380));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table2.setBackground(new java.awt.Color(102, 102, 102));
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Qty.", "Unit price", "Price"
            }
        ));
        jScrollPane3.setViewportView(table2);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 680, 150));

        jButton7.setBackground(new java.awt.Color(255, 51, 51));
        jButton7.setText("ADD");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 70, 30));

        jButton9.setBackground(new java.awt.Color(255, 51, 51));
        jButton9.setText("PRINT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 70, 30));

        jButton10.setBackground(new java.awt.Color(255, 51, 51));
        jButton10.setText("FINAL");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 90, 30));

        jButton11.setBackground(new java.awt.Color(255, 51, 51));
        jButton11.setText("CLEAR ALL");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("STOCK INVOICES");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 210, 30));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Invoice No.");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 70, 30));

        unitprice1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unitprice1KeyPressed(evt);
            }
        });
        jPanel3.add(unitprice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 140, 30));

        checkbox.setForeground(new java.awt.Color(255, 255, 255));
        checkbox.setText("Cash");
        jPanel3.add(checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 90, 30));

        jButton13.setBackground(new java.awt.Color(255, 51, 51));
        jButton13.setText("CALCULATOR");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 170, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 1220, 290));
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 690, 90, 10));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Bill No.");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 50, 30));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Invoice No.");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, 70, 30));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Invoice No.");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, 70, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        add1();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void code1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_code1KeyPressed
        if (evt.getKeyCode() == 10) {
            unit1.setText(productUnit(code1.getText()));
            qty1.requestFocus();
        }
    }//GEN-LAST:event_code1KeyPressed

    private void unitprice1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitprice1KeyPressed

        if (evt.getKeyCode() == 10) {

            double price = Double.parseDouble(qty1.getText()) * Double.parseDouble(unitprice1.getText());
            price1.setText(Double.toString(price));
            jButton7.requestFocus();
        }

    }//GEN-LAST:event_unitprice1KeyPressed

    private void qty1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qty1KeyPressed
        if (evt.getKeyCode() == 10) {
            unitprice1.requestFocus();
        }
    }//GEN-LAST:event_qty1KeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        int key = evt.getKeyCode();
        if (key == 10) {
            add1();
        } else if (key == 39) {

            jButton10.requestFocus();

        }
    }//GEN-LAST:event_jButton7KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            print1();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            delete1();
            area2.setText("");
            dlvrname.setText("");
            invoiceno.setText("");
            checkbox.setSelected(false);

            code1.setText("");
            qty1.setText("");
            unit1.setText("");
            unitprice1.setText("");
            price1.setText("");

        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            final1();
            print1();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void dlvrnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dlvrnameKeyPressed
        if (evt.getKeyCode() == 10) {

            try {

                PreparedStatement pst2 = con.prepareStatement("SET @@SESSION.information_schema_stats_expiry=0");
                pst2.execute();

                pst = con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME='stockhistory'");
                rs = pst.executeQuery();
                rs.next();

                //String z = Integer.toString(Integer.parseInt(rs.getString("AUTO_INCREMENT")) - 1);
                String z = rs.getString("AUTO_INCREMENT");
                invoiceno.setText(z);

            } catch (Exception e) {
            }

            code1.requestFocus();

        }
    }//GEN-LAST:event_dlvrnameKeyPressed

    private void costnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costnameKeyPressed

        if (evt.getKeyCode() == 10) {

            try {

                PreparedStatement pst2 = con.prepareStatement("SET @@SESSION.information_schema_stats_expiry=0");
                pst2.execute();

                pst = con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME='billhistory'");
                rs = pst.executeQuery();
                rs.next();

                String z = rs.getString("AUTO_INCREMENT");
                billno.setText(z);

            } catch (Exception e) {
            }

            code2.requestFocus();

        }


    }//GEN-LAST:event_costnameKeyPressed

    private void code2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_code2KeyPressed
        if (evt.getKeyCode() == 10) {
            unit2.setText(productUnit(code2.getText()));
            qty2.requestFocus();
        }
    }//GEN-LAST:event_code2KeyPressed

    private void qty2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qty2KeyPressed

        if (evt.getKeyCode() == 10) {

            unitprice2.setText(Double.toString(productUnitPrice(code2.getText())));
            String price = Double.toString(productUnitPrice(code2.getText()) * Double.parseDouble(qty2.getText()));
            price2.setText(price);
            jButton2.requestFocus();

        }

    }//GEN-LAST:event_qty2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        add2();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        if (evt.getKeyCode() == 10) {
            add2();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            print2();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            delete2();
            area1.setText("");
            costname.setText("");
            billno.setText("");
            jCheckBox1.setSelected(false);

            code2.setText("");
            qty2.setText("");
            unit2.setText("");
            unitprice2.setText("");
            price2.setText("");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            final2();
            print2();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        new calc().setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        new calc().setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        new AddItem().setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
         new Inquiry().setVisible(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        new DailyReport().setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new Transaction().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "ARE YOU SURE ?");
        if (dialog == 0) {
            new Login().setVisible(true);
            this.dispose();
            /*c1.setVisible(false);
            c2.setVisible(false);
            c3.setVisible(false);
            c4.setVisible(false);
            c5.setVisible(false);
            c6.setVisible(false);*/
            
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        new Setting().setVisible(true);
    }//GEN-LAST:event_jButton18ActionPerformed

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
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area1;
    private javax.swing.JTextArea area2;
    private javax.swing.JLabel billno;
    private javax.swing.JCheckBox checkbox;
    private javax.swing.JTextField code1;
    private javax.swing.JTextField code2;
    private javax.swing.JTextField costname;
    private javax.swing.JTextField dlvrname;
    private javax.swing.JLabel invoiceno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    public static javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel price1;
    private javax.swing.JLabel price2;
    private javax.swing.JTextField qty1;
    private javax.swing.JTextField qty2;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JLabel unit1;
    private javax.swing.JLabel unit2;
    private javax.swing.JTextField unitprice1;
    private javax.swing.JLabel unitprice2;
    // End of variables declaration//GEN-END:variables
}
