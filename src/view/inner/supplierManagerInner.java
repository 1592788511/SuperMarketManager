/*
 * Created by JFormDesigner on Sun Jul 04 16:09:32 CST 2021
 */

package view.inner;

import java.awt.event.*;
import javax.swing.border.*;
import dao.returnsDao;
import moudel.returns;
import util.DBUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;
import javax.xml.parsers.DocumentBuilder;

import dao.*;
import moudel.*;
import util.*;

/**
 * @author liu
 */
public class supplierManagerInner extends JInternalFrame {
    public supplierManagerInner() {
        initComponents();
    }


    private void fillTable(supplier supplier){
        DefaultTableModel dtm = (DefaultTableModel) supplierTable.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = supplierDao.list(conn,supplier);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("address"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("number"));
                v.add(rs.getString("name"));
                v.add(rs.getString("remarks"));
                dtm.addRow(v);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,null,null);
        }


    }

    private void button1ActionPerformed(ActionEvent e) {
        addSupplierActionPerformed(e);
    }

    /**
     * 供应商添加事件处理
     * @param e
     */
    private void addSupplierActionPerformed(ActionEvent e){
        Connection conn = null;
        supplier supplier = null;
        String address = this.addAddressTxt.getText();
        String contact = this.addContactTxt.getText();
        String number = this.addNumberTxt.getText();
        String name = this.addNameTxt.getText();
        String remarks = this.addRemarkTxt.getText();

        if(stringUtil.isEmpty(address)){
            JOptionPane.showMessageDialog(null,"请输入地址！");
            return;
        }
        if(stringUtil.isEmpty(contact)){
            JOptionPane.showMessageDialog(null,"请输入联系方式！");
            return;
        }
        if(stringUtil.isEmpty(number)){
            JOptionPane.showMessageDialog(null,"请输入联系号码！");
            return;
        }
        if(stringUtil.isEmpty(name)){
            JOptionPane.showMessageDialog(null,"请输入名称！");
            return;
        }

        try{
            conn = DBUtil.getConnection();
            supplier = new supplier(address, contact, name, number, remarks);
            int n = supplierDao.add(conn,supplier);
            if(n == 1){
                JOptionPane.showMessageDialog(null,"供应商添加成功！");
                this.fillTable(new supplier());

            }
            else{
                JOptionPane.showMessageDialog(null,"供应商添加失败！");
            }

            }catch (Exception ex){
            ex.printStackTrace();
        } finally{
            DBUtil.close(conn,null,null);
        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        updateSupplierActionPerformed(e);
    }

    /**
     * 更新供应商事件处理
     * @param e
     */
    private void updateSupplierActionPerformed(ActionEvent e){
        Connection conn = null;
        supplier supplier = null;

        String id = this.updateIdTxt.getText();
        String address = this.updateAddressTxt.getText();
        String contact = this.updateContactTxt.getText();
        String number = this.updateNumberTxt.getText();
        String name = this.updateNameTxt.getText();
        String remarks = this.updateRemarkTxt.getText();


        if(stringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择一个表单！");
            return;
        }
        if(stringUtil.isEmpty(address)){
            JOptionPane.showMessageDialog(null,"请输入地址！");
            return;
        }
        if(stringUtil.isEmpty(contact)){
            JOptionPane.showMessageDialog(null,"请输入联系方式！");
            return;
        }
        if(stringUtil.isEmpty(number)){
            JOptionPane.showMessageDialog(null,"请输入联系号码！");
            return;
        }
        if(stringUtil.isEmpty(name)){
            JOptionPane.showMessageDialog(null,"请输入名称！");
            return;
        }

        try{
            conn = DBUtil.getConnection();
            supplier = new supplier(Integer.valueOf(id), address, contact, name, number, remarks);
            int n = supplierDao.update(conn,supplier);
            if(n == 1){
                JOptionPane.showMessageDialog(null,"更新供应商信息成功！");
                this.fillTable(new supplier());
            }
            else{
                JOptionPane.showMessageDialog(null,"更新供应商信息失败！");

            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }

    }

    /**
     * 表格点击事件处理
     * @param e
     */
    private void supplierTableMousePressed(MouseEvent e) {
        int row = supplierTable.getSelectedRow();
        this.updateIdTxt.setText((String)supplierTable.getValueAt(row,0));
        this.deleteIdTxt.setText((String)supplierTable.getValueAt(row,0));
        this.updateAddressTxt.setText((String)supplierTable.getValueAt(row,1));
        this.updateContactTxt.setText((String)supplierTable.getValueAt(row,2));
        this.updateNumberTxt.setText((String)supplierTable.getValueAt(row,3));
        this.updateNameTxt.setText((String)supplierTable.getValueAt(row,4));
        this.updateRemarkTxt.setText((String)supplierTable.getValueAt(row,5));


    }

    private void button3ActionPerformed(ActionEvent e) {
        deleteSupplierActionPerformed(e);
    }

    /**
     * 删除供应商事件处理
     * @param e
     */
    private void deleteSupplierActionPerformed(ActionEvent e){
        Connection conn = null;
        String deleteId = this.deleteIdTxt.getText();
        if(stringUtil.isEmpty(deleteId)){
            JOptionPane.showMessageDialog(null,"请选择一个表单！");
            return;
        }


        try{
            conn = DBUtil.getConnection();
            int n = supplierDao.delete(conn,Integer.valueOf(deleteId));
            if(n == 1){
                JOptionPane.showMessageDialog(null,"删除供应商信息成功！");
                this.fillTable(new supplier());
            }
            else{
                JOptionPane.showMessageDialog(null,"删除供应商信息失败！");

            }

        }catch(Exception ex){
            ex.printStackTrace();
        } finally{
            DBUtil.close(conn,null,null);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        scrollPane1 = new JScrollPane();
        supplierTable = new JTable();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        addAddressTxt = new JTextField();
        addContactTxt = new JTextField();
        addNumberTxt = new JTextField();
        addNameTxt = new JTextField();
        addRemarkTxt = new JTextField();
        button1 = new JButton();
        panel2 = new JPanel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        updateAddressTxt = new JTextField();
        updateContactTxt = new JTextField();
        updateNumberTxt = new JTextField();
        updateNameTxt = new JTextField();
        updateRemarkTxt = new JTextField();
        label11 = new JLabel();
        updateIdTxt = new JTextField();
        button2 = new JButton();
        panel3 = new JPanel();
        label12 = new JLabel();
        deleteIdTxt = new JTextField();
        button3 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle(bundle.getString("supplierManagerInner.this.title"));
        setIconifiable(true);
        setClosable(true);
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- supplierTable ----
            supplierTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u5730\u5740", "\u8054\u7cfb\u65b9\u5f0f", "\u8054\u7cfb\u53f7\u7801", "\u540d\u79f0", "\u5907\u6ce8"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            supplierTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    supplierTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(supplierTable);
        }

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(bundle.getString("supplierManagerInner.panel1.border")));

            //---- label1 ----
            label1.setText(bundle.getString("supplierManagerInner.label1.text"));

            //---- label2 ----
            label2.setText(bundle.getString("supplierManagerInner.label2.text"));

            //---- label3 ----
            label3.setText(bundle.getString("supplierManagerInner.label3.text"));

            //---- label4 ----
            label4.setText(bundle.getString("supplierManagerInner.label4.text"));

            //---- label5 ----
            label5.setText(bundle.getString("supplierManagerInner.label5.text"));

            //---- button1 ----
            button1.setText(bundle.getString("supplierManagerInner.button1.text"));
            button1.addActionListener(e -> button1ActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label3)
                            .addComponent(label2)
                            .addComponent(label4, GroupLayout.Alignment.TRAILING)
                            .addComponent(label5, GroupLayout.Alignment.TRAILING)
                            .addComponent(label1, GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(addContactTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNumberTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNameTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                            .addComponent(addRemarkTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                            .addComponent(addAddressTxt, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(button1)
                        .addContainerGap(11, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label1)
                                    .addComponent(addAddressTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(addContactTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(addNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label5)
                                    .addComponent(addRemarkTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 46, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(bundle.getString("supplierManagerInner.panel2.border")));

            //---- label6 ----
            label6.setText(bundle.getString("supplierManagerInner.label6.text"));

            //---- label7 ----
            label7.setText(bundle.getString("supplierManagerInner.label7.text"));

            //---- label8 ----
            label8.setText(bundle.getString("supplierManagerInner.label8.text"));

            //---- label9 ----
            label9.setText(bundle.getString("supplierManagerInner.label9.text"));

            //---- label10 ----
            label10.setText(bundle.getString("supplierManagerInner.label10.text"));

            //---- label11 ----
            label11.setText(bundle.getString("supplierManagerInner.label11.text"));

            //---- updateIdTxt ----
            updateIdTxt.setEditable(false);

            //---- button2 ----
            button2.setText(bundle.getString("supplierManagerInner.button2.text"));
            button2.addActionListener(e -> button2ActionPerformed(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(label8)
                                .addComponent(label7)
                                .addComponent(label9, GroupLayout.Alignment.TRAILING)
                                .addComponent(label10, GroupLayout.Alignment.TRAILING)
                                .addComponent(label6, GroupLayout.Alignment.TRAILING))
                            .addComponent(label11, GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(updateContactTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateNumberTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateNameTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateRemarkTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateAddressTxt, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
                            .addComponent(updateIdTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(button2)
                        .addContainerGap(46, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addContainerGap(12, Short.MAX_VALUE)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(updateIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label11))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addComponent(label6)
                                    .addComponent(updateAddressTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label7)
                                    .addComponent(updateContactTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label8)
                                    .addComponent(updateNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label9, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label10)
                                    .addComponent(updateRemarkTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(button2, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                        .addContainerGap())
            );
        }

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder(bundle.getString("supplierManagerInner.panel3.border")));

            //---- label12 ----
            label12.setText(bundle.getString("supplierManagerInner.label12.text"));

            //---- deleteIdTxt ----
            deleteIdTxt.setEditable(false);

            //---- button3 ----
            button3.setText(bundle.getString("supplierManagerInner.button3.text"));
            button3.addActionListener(e -> button3ActionPerformed(e));

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(button3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addComponent(label12)
                                .addGap(4, 4, 4)
                                .addComponent(deleteIdTxt, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(92, Short.MAX_VALUE))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(label12))
                            .addComponent(deleteIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(38, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.fillTable(new supplier());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable supplierTable;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField addAddressTxt;
    private JTextField addContactTxt;
    private JTextField addNumberTxt;
    private JTextField addNameTxt;
    private JTextField addRemarkTxt;
    private JButton button1;
    private JPanel panel2;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JTextField updateAddressTxt;
    private JTextField updateContactTxt;
    private JTextField updateNumberTxt;
    private JTextField updateNameTxt;
    private JTextField updateRemarkTxt;
    private JLabel label11;
    private JTextField updateIdTxt;
    private JButton button2;
    private JPanel panel3;
    private JLabel label12;
    private JTextField deleteIdTxt;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
