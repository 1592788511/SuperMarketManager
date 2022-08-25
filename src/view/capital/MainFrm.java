/*
 * Created by JFormDesigner on Sat Jun 26 14:02:15 CST 2021
 */

package view.capital;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import dao.UserDao;
import util.*;
import view.inner.*;

/**
 * @author liu
 */
public class MainFrm extends JFrame {

    private String userId = "null";
    private int role;
    public MainFrm() {
        initComponents();
    }

    public MainFrm(String id) {
        this.userId = id;
        initComponents();
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        aboutUsInner au = new aboutUsInner();
        au.setSize(1075,555);
        au.setLocation(500,250);

        au.setVisible(true);
        table.add(au);

    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(null,"是否退出系统");
        if(result == 0){
            dispose();
        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        goodsManagerInner gmi = new goodsManagerInner();
        gmi.setBounds(350,30,1220,850);
        gmi.setVisible(true);
        table.add(gmi);
    }


    private void button10ActionPerformed(ActionEvent e) {
        goodsTypeManagerInner gtm = new goodsTypeManagerInner();
        gtm.setSize(1100,800);
        gtm.setLocation(350,30);

        gtm.setVisible(true);
        table.add(gtm);
    }

    private void button3ActionPerformed(ActionEvent e) {
        orderManagerInner omi = new orderManagerInner();
        omi.setBounds(350,10,1220,940);
        omi.setVisible(true);
        table.add(omi);
    }

    private void button6ActionPerformed(ActionEvent e) {
        returnsManagerInner rmi = new returnsManagerInner();
        rmi.setBounds(350,10,860,750);
        rmi.setVisible(true);
        table.add(rmi);
    }

    private void button7ActionPerformed(ActionEvent e) {
        userManagerInner umi = new userManagerInner();
        umi.setBounds(350,10,1220,900);
        umi.setVisible(true);
        table.add(umi);
    }

    private void button5ActionPerformed(ActionEvent e) {
        saleManagerInner smi = new saleManagerInner();
        smi.setBounds(350,10,1220,760);
        smi.setVisible(true);
        table.add(smi);
    }

    private void button8ActionPerformed(ActionEvent e) {
        supplierManagerInner smi = new supplierManagerInner();
        smi.setBounds(350,10,1220,760);
        smi.setVisible(true);
        table.add(smi);
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        myInfoInner mii = new myInfoInner(userId);
        mii.setSize(350,410);
        mii.setLocation(600,250);

        mii.setVisible(true);
        table.add(mii);
    }

    /**
     * 用户权限设置
     */
    private void initRight(){
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = UserDao.list(conn,userId);
            while(rs.next()){
                this.role = Integer.valueOf(rs.getString("roleId"));
            }

            if(this.role == 3){
                this.button7.setEnabled(false);
            }
            else if(this.role == 4){
                this.button1.setEnabled(false);
                this.button2.setEnabled(false);
                this.button7.setEnabled(false);
                this.button8.setEnabled(false);

            }
            else if(this.role == 5){
                this.button1.setEnabled(false);
                this.button2.setEnabled(false);
                this.button6.setEnabled(false);
                this.button7.setEnabled(false);
                this.button8.setEnabled(false);
            }
            else if(this.role == 6){
                this.button3.setEnabled(false);
                this.button6.setEnabled(false);
                this.button5.setEnabled(false);
                this.button7.setEnabled(false);
                this.button8.setEnabled(false);

            }

        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }
    }



    private void initComponents() {


        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        table = new JDesktopPane();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem1 = new JMenuItem();
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        panel2 = new JPanel();
        button3 = new JButton();
        panel3 = new JPanel();
        button6 = new JButton();
        button5 = new JButton();
        panel4 = new JPanel();
        button7 = new JButton();
        button8 = new JButton();

        //======== this ========
        setTitle("Main");
        setBackground(Color.white);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== table ========
        {
            table.setBackground(Color.black);

            //======== menuBar1 ========
            {

                //======== menu1 ========
                {
                    menu1.setText(bundle.getString("MainFrm.menu1.text"));

                    //---- menuItem3 ----
                    menuItem3.setText(bundle.getString("MainFrm.menuItem3.text"));
                    menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                    menu1.add(menuItem3);

                    //---- menuItem2 ----
                    menuItem2.setText(bundle.getString("MainFrm.menuItem2.text"));
                    menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                    menu1.add(menuItem2);

                    //---- menuItem1 ----
                    menuItem1.setText(bundle.getString("MainFrm.menuItem1.text"));
                    menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                    menu1.add(menuItem1);
                }
                menuBar1.add(menu1);
            }
            table.add(menuBar1, JLayeredPane.DEFAULT_LAYER);
            menuBar1.setBounds(0, 0, 40, 23);

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder(bundle.getString("MainFrm.panel1.border")));
                panel1.setBackground(Color.black);

                //---- button1 ----
                button1.setText(bundle.getString("MainFrm.button1.text"));
                button1.setBackground(Color.black);
                button1.addActionListener(e -> button1ActionPerformed(e));

                //---- button2 ----
                button2.setText(bundle.getString("MainFrm.button2.text"));
                button2.addActionListener(e -> button10ActionPerformed(e));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(13, Short.MAX_VALUE))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
                            .addGap(211, 211, 211))
                );
            }
            table.add(panel1, JLayeredPane.DEFAULT_LAYER);
            panel1.setBounds(0, 30, 285, 130);

            //======== panel2 ========
            {
                panel2.setBorder(new TitledBorder(bundle.getString("MainFrm.panel2.border")));
                panel2.setBackground(Color.black);

                //---- button3 ----
                button3.setText(bundle.getString("MainFrm.button3.text"));
                button3.addActionListener(e -> button3ActionPerformed(e));

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(38, Short.MAX_VALUE))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                            .addContainerGap(17, Short.MAX_VALUE)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17))
                );
            }
            table.add(panel2, JLayeredPane.DEFAULT_LAYER);
            panel2.setBounds(0, 165, 285, 130);

            //======== panel3 ========
            {
                panel3.setBorder(new TitledBorder(bundle.getString("MainFrm.panel3.border")));
                panel3.setBackground(Color.black);

                //---- button6 ----
                button6.setText(bundle.getString("MainFrm.button6.text"));
                button6.addActionListener(e -> button6ActionPerformed(e));

                //---- button5 ----
                button5.setText(bundle.getString("MainFrm.button5.text"));
                button5.addActionListener(e -> button5ActionPerformed(e));

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(button6, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button5, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(13, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button6, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addComponent(button5, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }
            table.add(panel3, JLayeredPane.DEFAULT_LAYER);
            panel3.setBounds(0, 300, 285, 135);

            //======== panel4 ========
            {
                panel4.setBorder(new TitledBorder(bundle.getString("MainFrm.panel4.border")));
                panel4.setBackground(Color.black);

                //---- button7 ----
                button7.setText(bundle.getString("MainFrm.button7.text"));
                button7.addActionListener(e -> button7ActionPerformed(e));

                //---- button8 ----
                button8.setText(bundle.getString("MainFrm.button8.text"));
                button8.addActionListener(e -> button8ActionPerformed(e));

                GroupLayout panel4Layout = new GroupLayout(panel4);
                panel4.setLayout(panel4Layout);
                panel4Layout.setHorizontalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button7, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button8, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14))
                );
                panel4Layout.setVerticalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                            .addContainerGap(19, Short.MAX_VALUE)
                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button7, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                .addComponent(button8, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                            .addContainerGap())
                );
            }
            table.add(panel4, JLayeredPane.DEFAULT_LAYER);
            panel4.setBounds(0, 440, 285, 135);
        }
        contentPane.add(table, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        initRight();
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JDesktopPane table;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem3;
    private JMenuItem menuItem2;
    private JMenuItem menuItem1;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JPanel panel2;
    private JButton button3;
    private JPanel panel3;
    private JButton button6;
    private JButton button5;
    private JPanel panel4;
    private JButton button7;
    private JButton button8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
