/*
 * Created by JFormDesigner on Tue Jun 29 20:28:38 CST 2021
 */

package view.inner;

import java.awt.event.*;
import dao.goodsTypeDao;
import moudel.goodsType;
import util.DBUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import moudel.*;
import dao.*;
import util.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author liu
 */
public class orderManagerInner extends JInternalFrame {
    public orderManagerInner() {
        initComponents();
    }


    private void button2ActionPerformed(ActionEvent e) {
        orderAddActionPerformed(e);
        this.supplierJCB.setSelectedIndex(0);
    }

    private void button1ActionPerformed(ActionEvent e) {
        searchOrderActionPerformed(e);
    }

    private void button3ActionPerformed(ActionEvent e) {
        updateOrderActionPerformed(e);
    }

    private void button5ActionPerformed(ActionEvent e) {
        deleteOrderActionPerformed(e);
    }

    private void button4ActionPerformed(ActionEvent e) {
        updateResetValue();
    }
    private void button6ActionPerformed(ActionEvent e) {
        addResetValue();
    }



    /**
     * 更新添加表单
     */
    private void addResetValue(){
        this.orderCodeTxt.setText("");
        this.supplierJCB2.setSelectedIndex(0);
        this.orderStateTxt.setText("");
        this.orderRemarkTxt.setText("");
        this.orderPayDate.setText("");
        this.orderPayMoney.setText("");
        this.orderCreateDate.setText("");
    }


    /**
     * 重置更新表单
     */
    private void updateResetValue(){
        this.orderUpdateIdTxt.setText("");
        this.orderCodeUpdateTxt.setText("");
        this.supplierJCB3.setSelectedIndex(0);
        this.orderUpdateStateTxt.setText("");
        this.orderUpdateRemarkTxt.setText("");
        this.orderUpdatePayDateTxt.setText("");
        this.orderUpdatePayMoneyTxt.setText("");
        this.orderUpdateCreateDateTxt.setText("");
    }


    /**
     * 删除订单事件处理
     * @param e
     */
    private void deleteOrderActionPerformed(ActionEvent e){
        String deleteId = this.orderDeleteIdTxt.getText();
        Connection conn = null;

        try{
            conn = DBUtil.getConnection();
            int n = orderDao.delete(conn,Integer.valueOf(deleteId));

            if(n == 1){
                JOptionPane.showMessageDialog(null,"删除订单信息成功！");
            }
            else{
                JOptionPane.showMessageDialog(null,"删除订单信息失败！");
            }

        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }
    }

    /**
     * 表格行点击事件
     * @param e
     */

    private void orderTableMousePressed(MouseEvent e) {
        goodsTypeTableMousePressed(e);
    }
    private void goodsTypeTableMousePressed(MouseEvent e){
        int row = orderTable.getSelectedRow();
        orderUpdateIdTxt.setText((String)orderTable.getValueAt(row,0));
        orderDeleteIdTxt.setText((String)orderTable.getValueAt(row,0));
        orderCodeUpdateTxt.setText((String)orderTable.getValueAt(row,1));
        //orderSupplierUpdateTxt.setText((String)orderTable.getValueAt(row,0));
        orderUpdateStateTxt.setText((String)orderTable.getValueAt(row,3));
        orderUpdateRemarkTxt.setText((String)orderTable.getValueAt(row,4));
        orderUpdatePayMoneyTxt.setText((String)orderTable.getValueAt(row,5));
        orderUpdateCreateDateTxt.setText((String)orderTable.getValueAt(row,6));
        orderUpdatePayDateTxt.setText((String)orderTable.getValueAt(row,7));

        String orderSupplierName = (String)orderTable.getValueAt(row,2);

        int n = this.supplierJCB3.getItemCount();
        for(int i=0;i<n;i++){
            supplier item = (supplier) this.supplierJCB3.getItemAt(i);
            if(item.getName().equals(orderSupplierName)){
                this.supplierJCB3.setSelectedIndex(i);
            }
        }
    }


    private void updateOrderActionPerformed(ActionEvent e){
        String orderId = this.orderUpdateIdTxt.getText();
        String orderCode = this.orderCodeUpdateTxt.getText();
        String orderState = this.orderUpdateStateTxt.getText();
        String orderRemark = this.orderUpdateRemarkTxt.getText();
        String orderPayMoney = this.orderUpdatePayMoneyTxt.getText();
        String orderCreateDate = this.orderUpdateCreateDateTxt.getText();
        String orderPayDate = this.orderUpdatePayDateTxt.getText();

        supplier supplier = (supplier) this.supplierJCB3.getSelectedItem();
        int orderSupplierId = supplier.getId();

        if(stringUtil.isEmpty(orderCode)){
            JOptionPane.showMessageDialog(null,"请输入订单代码");
            return;
        }
        if(stringUtil.isEmpty(orderState)){
            JOptionPane.showMessageDialog(null,"请输入订单状态");
            return;
        }
        if(stringUtil.isEmpty(orderPayMoney)){
            JOptionPane.showMessageDialog(null,"请输入订单支付价格");
            return;
        }
        if(stringUtil.isEmpty(orderCreateDate)){
            JOptionPane.showMessageDialog(null,"请输入订单创建时间");
            return;
        }
        if(stringUtil.isEmpty(orderPayDate)){
            JOptionPane.showMessageDialog(null,"请输入订单支付时间");
            return;
        }

        if(orderSupplierId == -1){
            JOptionPane.showMessageDialog(null,"请选择订单供应商");
            return;
        }

        if(stringUtil.isEmpty(orderRemark)){
            orderRemark = "";
        }

        Connection conn = null;
        order updateOrder = new order(Integer.valueOf(orderId),orderCode,orderSupplierId,orderState,orderRemark,Float.valueOf(orderPayMoney),orderCreateDate,orderPayDate);

        try{
            conn = DBUtil.getConnection();
            int n = orderDao.update(conn,updateOrder);
            if(n == 1){
                JOptionPane.showMessageDialog(null,"订单信息更新成功！");
            }
            else {
                JOptionPane.showMessageDialog(null,"订单信息更新失败！");
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }


    }

    /**
     * 订单查询处理
     * @param e
     */
    private void searchOrderActionPerformed(ActionEvent e) {
        supplier supplier = (supplier) supplierJCB.getSelectedItem();

        int orderId = supplier.getId();

        order order = new order(orderId);

        fillTable(order);

    }

    /**
     * 订单添加事件处理
     * @param e
     */
    private void orderAddActionPerformed(ActionEvent e){
        Connection conn = null;

        String orderCode = this.orderCodeTxt.getText();
        String orderState = this.orderStateTxt.getText();
        String orderRemark = this.orderRemarkTxt.getText();
        String orderPayMoney = this.orderPayMoney.getText();
        String orderCreateDate = this.orderCreateDate.getText();
        String orderPayDate = this.orderPayDate.getText();

        supplier supplier = (supplier) this.supplierJCB2.getSelectedItem();
        int orderSupplierId = supplier.getId();

        if(stringUtil.isEmpty(orderCode)){
            JOptionPane.showMessageDialog(null,"请输入订单代码");
            return;
        }
        if(stringUtil.isEmpty(orderState)){
            JOptionPane.showMessageDialog(null,"请输入订单状态");
            return;
        }
        if(stringUtil.isEmpty(orderPayMoney)){
            JOptionPane.showMessageDialog(null,"请输入订单支付价格");
            return;
        }
        if(stringUtil.isEmpty(orderCreateDate)){
            JOptionPane.showMessageDialog(null,"请输入订单创建时间");
            return;
        }
        if(stringUtil.isEmpty(orderPayDate)){
            JOptionPane.showMessageDialog(null,"请输入订单支付时间");
            return;
        }

        if(orderSupplierId == -1){
            JOptionPane.showMessageDialog(null,"请选择订单供应商");
            return;
        }

        if(stringUtil.isEmpty(orderRemark)){
            orderRemark = "";
        }

        order addOrder = new order(orderCode,orderSupplierId,orderState,orderRemark,Float.valueOf(orderPayMoney),orderCreateDate,orderPayDate);

        try{
            conn = DBUtil.getConnection();
            int n = orderDao.add(conn,addOrder);
            if(n == 1){
                JOptionPane.showMessageDialog(null,"添加订单成功！");
            }
            else{
                JOptionPane.showMessageDialog(null,"添加订单失败！");
            }

        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }
    }

    /**
     *初始化下拉框
     */
    private void fillSupplier(){
        supplier supplier = null;
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = supplierDao.list(conn,new supplier());

            supplier = new supplier();
            supplier.setId(-1);
            supplier.setName("请选择...");

            this.supplierJCB.addItem(supplier);
            this.supplierJCB2.addItem(supplier);

            while(rs.next()){
                supplier = new supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));

                this.supplierJCB.addItem(supplier);
                this.supplierJCB2.addItem(supplier);
                this.supplierJCB3.addItem(supplier);
            }


        } catch(Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }

    }

    /**
     * 填充订单表格
     * @param order
     */
    private void fillTable(order order){
        DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = orderDao.list(conn,order);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("code"));
                v.add(rs.getString("s.name"));
                v.add(rs.getString("state"));
                v.add(rs.getString("remark"));
                v.add(rs.getString("paymoney"));
                v.add(rs.getString("createdate"));
                v.add(rs.getString("paydate"));
                dtm.addRow(v);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,null,null);
        }


    }










    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        panel2 = new JPanel();
        orderCodeTxt = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        orderStateTxt = new JTextField();
        label5 = new JLabel();
        orderRemarkTxt = new JTextField();
        label6 = new JLabel();
        orderPayMoney = new JTextField();
        label7 = new JLabel();
        orderCreateDate = new JTextField();
        label8 = new JLabel();
        orderPayDate = new JTextField();
        label9 = new JLabel();
        button2 = new JButton();
        supplierJCB2 = new JComboBox();
        button6 = new JButton();
        panel1 = new JPanel();
        supplierJCB = new JComboBox();
        label1 = new JLabel();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        orderTable = new JTable();
        panel3 = new JPanel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        orderCodeUpdateTxt = new JTextField();
        orderUpdateStateTxt = new JTextField();
        supplierJCB3 = new JComboBox();
        orderUpdateRemarkTxt = new JTextField();
        orderUpdatePayMoneyTxt = new JTextField();
        orderUpdateCreateDateTxt = new JTextField();
        orderUpdatePayDateTxt = new JTextField();
        button3 = new JButton();
        label17 = new JLabel();
        orderUpdateIdTxt = new JTextField();
        button4 = new JButton();
        panel4 = new JPanel();
        label2 = new JLabel();
        orderDeleteIdTxt = new JTextField();
        button5 = new JButton();

        //======== this ========
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setTitle(bundle.getString("orderManagerInner.this.title_2"));
        Container contentPane = getContentPane();

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(bundle.getString("orderManagerInner.panel2.border")));

            //---- label3 ----
            label3.setText(bundle.getString("orderManagerInner.label3.text"));

            //---- label4 ----
            label4.setText(bundle.getString("orderManagerInner.label4.text"));

            //---- label5 ----
            label5.setText(bundle.getString("orderManagerInner.label5.text"));

            //---- label6 ----
            label6.setText(bundle.getString("orderManagerInner.label6.text"));

            //---- label7 ----
            label7.setText(bundle.getString("orderManagerInner.label7.text"));

            //---- label8 ----
            label8.setText(bundle.getString("orderManagerInner.label8.text"));

            //---- label9 ----
            label9.setText(bundle.getString("orderManagerInner.label9.text"));

            //---- button2 ----
            button2.setText(bundle.getString("orderManagerInner.button2.text"));
            button2.addActionListener(e -> {
			button2ActionPerformed(e);
			button1ActionPerformed(e);
		});

            //---- button6 ----
            button6.setText(bundle.getString("orderManagerInner.button6.text"));
            button6.addActionListener(e -> button6ActionPerformed(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(label6)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(orderRemarkTxt, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(label5)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(orderStateTxt, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(label7)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(orderPayMoney, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(label8)
                                    .addGap(4, 4, 4)
                                    .addComponent(orderCreateDate, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(label9)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(orderPayDate, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(label4)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(supplierJCB2, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(orderCodeTxt, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(button2)
                            .addComponent(button6))
                        .addContainerGap())
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button6, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(orderCodeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(supplierJCB2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4))
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(orderRemarkTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label6))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(orderPayMoney, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label7))
                                        .addGap(6, 6, 6)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label8)
                                            .addComponent(orderCreateDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(orderStateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label5))))
                                .addGap(6, 6, 6)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label9)
                                    .addComponent(orderPayDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 80, Short.MAX_VALUE))
            );
        }

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(bundle.getString("orderManagerInner.panel1.border")));

            //---- label1 ----
            label1.setText(bundle.getString("orderManagerInner.label1.text"));

            //---- button1 ----
            button1.setText(bundle.getString("orderManagerInner.button1.text"));
            button1.addActionListener(e -> button1ActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierJCB, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(button1)
                        .addContainerGap(776, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierJCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1)
                            .addComponent(button1))
                        .addGap(0, 41, Short.MAX_VALUE))
            );
        }

        //======== scrollPane1 ========
        {

            //---- orderTable ----
            orderTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u4ee3\u7801", "\u4f9b\u5e94\u5546", "\u72b6\u6001", "\u5907\u6ce8", "\u652f\u4ed8\u4ef7\u683c", "\u521b\u5efa\u65e5\u671f", "\u652f\u4ed8\u65e5\u671f"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false, false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            orderTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    orderTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(orderTable);
        }

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder(bundle.getString("orderManagerInner.panel3.border")));

            //---- label10 ----
            label10.setText(bundle.getString("orderManagerInner.label10.text"));

            //---- label11 ----
            label11.setText(bundle.getString("orderManagerInner.label11.text"));

            //---- label12 ----
            label12.setText(bundle.getString("orderManagerInner.label12.text"));

            //---- label13 ----
            label13.setText(bundle.getString("orderManagerInner.label13.text"));

            //---- label14 ----
            label14.setText(bundle.getString("orderManagerInner.label14.text"));

            //---- label15 ----
            label15.setText(bundle.getString("orderManagerInner.label15.text"));

            //---- label16 ----
            label16.setText(bundle.getString("orderManagerInner.label16.text"));

            //---- button3 ----
            button3.setText(bundle.getString("orderManagerInner.button3.text"));
            button3.addActionListener(e -> {
			button3ActionPerformed(e);
			button1ActionPerformed(e);
		});

            //---- label17 ----
            label17.setText(bundle.getString("orderManagerInner.label17.text"));

            //---- orderUpdateIdTxt ----
            orderUpdateIdTxt.setEditable(false);

            //---- button4 ----
            button4.setText(bundle.getString("orderManagerInner.button4.text"));
            button4.addActionListener(e -> button4ActionPerformed(e));

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(label17))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label10)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addComponent(orderUpdateIdTxt)
                                    .addComponent(orderCodeUpdateTxt)))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addGap(0, 27, Short.MAX_VALUE)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addComponent(label11)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(supplierJCB3, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(label13)
                                            .addComponent(label12)
                                            .addComponent(label14)
                                            .addComponent(label15)
                                            .addComponent(label16))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel3Layout.createParallelGroup()
                                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGroup(panel3Layout.createParallelGroup()
                                                    .addComponent(orderUpdateStateTxt, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(orderUpdateRemarkTxt, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(orderUpdatePayMoneyTxt, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(orderUpdateCreateDateTxt, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(orderUpdatePayDateTxt, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addGroup(panel3Layout.createParallelGroup()
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(29, Short.MAX_VALUE))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label17)
                            .addComponent(orderUpdateIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(orderCodeUpdateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierJCB3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label11))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(orderUpdateStateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label12, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel3Layout.createParallelGroup()
                            .addComponent(label13)
                            .addComponent(orderUpdateRemarkTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label14, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orderUpdatePayMoneyTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addComponent(orderUpdateCreateDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label15)))
                            .addComponent(button4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label16, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderUpdatePayDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
            );
        }

        //======== panel4 ========
        {
            panel4.setBorder(new TitledBorder(bundle.getString("orderManagerInner.panel4.border")));

            //---- label2 ----
            label2.setText(bundle.getString("orderManagerInner.label2.text"));

            //---- orderDeleteIdTxt ----
            orderDeleteIdTxt.setEditable(false);

            //---- button5 ----
            button5.setText(bundle.getString("orderManagerInner.button5.text"));
            button5.addActionListener(e -> {
			button5ActionPerformed(e);
			button1ActionPerformed(e);
		});

            GroupLayout panel4Layout = new GroupLayout(panel4);
            panel4.setLayout(panel4Layout);
            panel4Layout.setHorizontalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addComponent(label2)
                                .addGap(18, 18, 18)
                                .addComponent(orderDeleteIdTxt, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                            .addComponent(button5, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75))
            );
            panel4Layout.setVerticalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(orderDeleteIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(button5, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(86, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 1090, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(116, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        this.fillSupplier();
        this.fillTable(new order());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel2;
    private JTextField orderCodeTxt;
    private JLabel label3;
    private JLabel label4;
    private JTextField orderStateTxt;
    private JLabel label5;
    private JTextField orderRemarkTxt;
    private JLabel label6;
    private JTextField orderPayMoney;
    private JLabel label7;
    private JTextField orderCreateDate;
    private JLabel label8;
    private JTextField orderPayDate;
    private JLabel label9;
    private JButton button2;
    private JComboBox supplierJCB2;
    private JButton button6;
    private JPanel panel1;
    private JComboBox supplierJCB;
    private JLabel label1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable orderTable;
    private JPanel panel3;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JTextField orderCodeUpdateTxt;
    private JTextField orderUpdateStateTxt;
    private JComboBox supplierJCB3;
    private JTextField orderUpdateRemarkTxt;
    private JTextField orderUpdatePayMoneyTxt;
    private JTextField orderUpdateCreateDateTxt;
    private JTextField orderUpdatePayDateTxt;
    private JButton button3;
    private JLabel label17;
    private JTextField orderUpdateIdTxt;
    private JButton button4;
    private JPanel panel4;
    private JLabel label2;
    private JTextField orderDeleteIdTxt;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
