/*
 * Created by JFormDesigner on Sun Jul 04 15:16:08 CST 2021
 */

package view.inner;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.*;
import util.*;
import dao.*;
import moudel.*;
import java.sql.*;

/**
 * @author liu
 */
public class saleManagerInner extends JInternalFrame {



    /**
     *初始化状态下拉框
     */
    private void fillState(){
        sale sale = null;
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();

            sale = new sale();
            sale.setId(-1);
            sale.setState("请选择...");
            this.saleStateJCB.addItem(sale);

            sale = new sale();
            sale.setId(0);
            sale.setState("已付款");
            this.saleStateJCB.addItem(sale);

            sale = new sale();
            sale.setId(1);
            sale.setState("未付款");
            this.saleStateJCB.addItem(sale);


        } catch(Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }

    }


    /**
     * 填充销售统计表格
     * @param sale
     */
    private void fillTable(sale sale){
        DefaultTableModel dtm = (DefaultTableModel) saleTable.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = saleDao.list(conn,sale);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("goods_id"));
                v.add(rs.getString("tg.name"));
                v.add(rs.getString("goods_typeId"));
                v.add(rs.getString("customer"));
                v.add(rs.getString("pay_money"));
                v.add(rs.getString("create_date"));
                v.add(rs.getString("state"));
                dtm.addRow(v);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,null,null);
        }

    }




    public saleManagerInner() {
        initComponents();
    }

    /**
     * 表格点击事件
     * @param e
     */
    private void saleTableMousePressed(MouseEvent e) {
        int row = saleTable.getSelectedRow();
        this.updateIdTxt.setText((String)saleTable.getValueAt(row,0));
        this.deleteIdTxt.setText((String)saleTable.getValueAt(row,0));
        String state = (String)saleTable.getValueAt(row,7);

        int n = this.saleStateJCB.getItemCount();
        for(int i=0;i<n;i++){
            sale item = (sale) this.saleStateJCB.getItemAt(i);
            if(item.getState().equals(state)){
                this.saleStateJCB.setSelectedIndex(i);
            }

        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        updateSaleActionPerformed(e);
    }

    /**
     * 更新状态事件处理
     * @param e
     */
    private void updateSaleActionPerformed(ActionEvent e){
        Connection conn = null;
        sale sale = null;
        String id = this.updateIdTxt.getText();

        if(stringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择一个表单！");
            return;
        }

        sale = (sale) this.saleStateJCB.getSelectedItem();
        sale.setId(Integer.valueOf(id));

        if(sale.getState().equals("请选择...")){
            JOptionPane.showMessageDialog(null,"请选择一个状态！");
            return;
        }

        try{
            conn = DBUtil.getConnection();
            int n = saleDao.update(conn,sale);
            if(n==1){
                JOptionPane.showMessageDialog(null,"更新状态成功！");
                fillTable(new sale());
            }
            else{
                JOptionPane.showMessageDialog(null,"更新状态失败！");
            }


        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }
    }

    private void button3ActionPerformed(ActionEvent e) {
        deleteSaleActionPerformed(e);
    }

    /**
     * 删除销售记录事件处理
     * @param e
     */
    private void deleteSaleActionPerformed(ActionEvent e){
        Connection conn = null;
        String deleteId = this.deleteIdTxt.getText();
        if(stringUtil.isEmpty(deleteId)){
            JOptionPane.showMessageDialog(null,"请选择一个表单！");
            return;
        }

        try{
            conn = DBUtil.getConnection();
            int n = saleDao.delete(conn,Integer.valueOf(deleteId));

            if(n == 1){
                JOptionPane.showMessageDialog(null,"删除销售记录成功！");
                fillTable(new sale());
            }
            else{
                JOptionPane.showMessageDialog(null,"删除销售记录失败！");
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
        scrollPane1 = new JScrollPane();
        saleTable = new JTable();
        panel2 = new JPanel();
        saleStateJCB = new JComboBox();
        label2 = new JLabel();
        label3 = new JLabel();
        updateIdTxt = new JTextField();
        button2 = new JButton();
        panel3 = new JPanel();
        label5 = new JLabel();
        deleteIdTxt = new JTextField();
        button3 = new JButton();

        //======== this ========
        setVisible(true);
        setClosable(true);
        setIconifiable(true);
        setTitle(bundle.getString("saleManagerInner.this.title_2"));
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- saleTable ----
            saleTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", "", null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5546\u54c1\u7c7b\u578b", "\u987e\u5ba2", "\u652f\u4ed8\u91d1\u989d", "\u652f\u4ed8\u65f6\u95f4", "\u72b6\u6001"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    true, false, false, false, false, false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            saleTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    saleTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(saleTable);
        }

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(bundle.getString("saleManagerInner.panel2.border")));

            //---- label2 ----
            label2.setText(bundle.getString("saleManagerInner.label2.text_2"));

            //---- label3 ----
            label3.setText(bundle.getString("saleManagerInner.label3.text_2"));

            //---- updateIdTxt ----
            updateIdTxt.setEditable(false);

            //---- button2 ----
            button2.setText(bundle.getString("saleManagerInner.button2.text"));
            button2.addActionListener(e -> button2ActionPerformed(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label3)
                            .addComponent(label2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(saleStateJCB, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(updateIdTxt))
                        .addGap(18, 18, 18)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(150, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, panel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(updateIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(saleStateJCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2))))
                        .addContainerGap(82, Short.MAX_VALUE))
            );
        }

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder(bundle.getString("saleManagerInner.panel3.border")));

            //---- label5 ----
            label5.setText(bundle.getString("saleManagerInner.label5.text"));

            //---- deleteIdTxt ----
            deleteIdTxt.setEditable(false);

            //---- button3 ----
            button3.setText(bundle.getString("saleManagerInner.button3.text"));
            button3.addActionListener(e -> button3ActionPerformed(e));

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(label5)
                                .addGap(18, 18, 18)
                                .addComponent(deleteIdTxt, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(button3, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(204, Short.MAX_VALUE))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5))
                        .addGap(18, 18, 18)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(45, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(61, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.fillTable(new sale());
        this.fillState();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable saleTable;
    private JPanel panel2;
    private JComboBox saleStateJCB;
    private JLabel label2;
    private JLabel label3;
    private JTextField updateIdTxt;
    private JButton button2;
    private JPanel panel3;
    private JLabel label5;
    private JTextField deleteIdTxt;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
