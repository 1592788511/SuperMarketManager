/*
 * Created by JFormDesigner on Sun Jul 04 11:36:58 CST 2021
 */

package view.inner;

import java.awt.event.*;

import com.sun.media.jfxmediaimpl.platform.ios.IOSPlatform;
import dao.orderDao;
import moudel.order;
import util.DBUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.*;
import moudel.*;
import util.*;
import dao.*;

/**
 * @author liu
 */
public class returnsManagerInner extends JInternalFrame {
    public returnsManagerInner() {
        initComponents();
    }



    private void button1ActionPerformed(ActionEvent e) {
        searchReturnsActionPerformed(e);
    }

    private void button2ActionPerformed(ActionEvent e) {
        examineReturnsActionPerformed(e);
    }

    private void button3ActionPerformed(ActionEvent e) {
        deleteReturnsActionPerformed(e);
    }


    /**
     * 删除退货记录事件处理
     * @param e
     */
    private void deleteReturnsActionPerformed(ActionEvent e){
        Connection conn = null;
        try{
            String deleteId = this.deleteIdTxt.getText();
            conn = DBUtil.getConnection();
            if(stringUtil.isEmpty(deleteId)){
                JOptionPane.showMessageDialog(null,"请选择一个表单！");
                return;
            }

            int n = returnsDao.delete(conn,deleteId);
            if(n == 1){
                JOptionPane.showMessageDialog(null,"退货记录删除成功！");
                this.fillTable(new returns(-1,"请选择..."));
            }
            else {
                JOptionPane.showMessageDialog(null,"退货记录删除失败！");

            }

        } catch(Exception ex){
            ex.printStackTrace();
        } finally{
            DBUtil.close(conn,null,null);
        }
    }

    /**
     * 表格行点击事件
     * @param e
     */
    private void returnsTableMousePressed(MouseEvent e){
        int row = returnsTable.getSelectedRow();
        this.examineIdTxt.setText((String)returnsTable.getValueAt(row,0));
        this.deleteIdTxt.setText((String)returnsTable.getValueAt(row,0));

        String returnsState = (String)returnsTable.getValueAt(row,5);

        int n = this.returnsStateJCB2.getItemCount();
        for(int i=0;i<n;i++){
            returns item = (returns) this.returnsStateJCB2.getItemAt(i);
            if(item.getState().equals(returnsState)){
                this.returnsStateJCB2.setSelectedIndex(i);
            }
        }
    }

    /**
     * 审核退货事件处理
     * @param e
     */
    private void examineReturnsActionPerformed(ActionEvent e){
        Connection conn = null;

        try{
            conn = DBUtil.getConnection();
            String updateId = (this.examineIdTxt.getText());
            if(stringUtil.isEmpty(updateId)){
                JOptionPane.showMessageDialog(null,"请选择一个表单！");
                return;
            }
            returns returns = (returns)this.returnsStateJCB2.getSelectedItem();
            String updateState = returns.getState();

            int n = returnsDao.examine(conn,updateId,updateState);
            if(n==1){
                JOptionPane.showMessageDialog(null,"审核成功！");
                this.fillTable(new returns(-1,"请选择..."));

            }
            else{
                JOptionPane.showMessageDialog(null,"审核失败！");
            }


        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }

    }



    /**
     * 查询退货事件处理
     * @param e
     */
    private void searchReturnsActionPerformed(ActionEvent e){
        returns returns = null;
        returns = (returns) this.returnsStateJCB.getSelectedItem();
        String state = returns.getState();
        int id = returns.getId();

        this.fillTable(new returns(id,state));
    }


    /**
     * 填充订单表格
     * @param returns
     */
    private void fillTable(returns returns){
        DefaultTableModel dtm = (DefaultTableModel) returnsTable.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = returnsDao.list(conn,returns);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("tg.name"));
                v.add(rs.getString("goods_typeId"));
                v.add(rs.getString("customer_id"));
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

    /**
     *初始化下拉框
     */
    private void fillState(){
        returns returns = null;
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();

            returns = new returns();
            returns.setId(-1);
            returns.setState("请选择...");
            this.returnsStateJCB.addItem(returns);
            this.returnsStateJCB2.addItem(returns);

            returns = new returns();
            returns.setId(0);
            returns.setState("已驳回");
            this.returnsStateJCB.addItem(returns);
            this.returnsStateJCB2.addItem(returns);

            returns = new returns();
            returns.setId(1);
            returns.setState("待审核");
            this.returnsStateJCB.addItem(returns);
            this.returnsStateJCB2.addItem(returns);

            returns = new returns();
            returns.setId(2);
            returns.setState("已通过");
            this.returnsStateJCB.addItem(returns);
            this.returnsStateJCB2.addItem(returns);


        } catch(Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }

    }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        panel1 = new JPanel();
        label1 = new JLabel();
        returnsStateJCB = new JComboBox();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        returnsTable = new JTable();
        panel2 = new JPanel();
        label2 = new JLabel();
        returnsStateJCB2 = new JComboBox();
        button2 = new JButton();
        label3 = new JLabel();
        examineIdTxt = new JTextField();
        panel3 = new JPanel();
        label4 = new JLabel();
        deleteIdTxt = new JTextField();
        button3 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle(bundle.getString("returnsManagerInner.this.title"));
        setClosable(true);
        setIconifiable(true);
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(bundle.getString("returnsManagerInner.panel1.border")));

            //---- label1 ----
            label1.setText(bundle.getString("returnsManagerInner.label1.text"));

            //---- button1 ----
            button1.setText(bundle.getString("returnsManagerInner.button1.text"));
            button1.addActionListener(e -> button1ActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(returnsStateJCB, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(button1)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(returnsStateJCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1))
                        .addContainerGap(18, Short.MAX_VALUE))
            );
        }

        //======== scrollPane1 ========
        {

            //---- returnsTable ----
            returnsTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", "", "", "", "", ""},
                    {null, null, null, "", null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5546\u54c1\u7c7b\u578b", "\u987e\u5ba2\u7f16\u53f7", "\u521b\u5efa\u65f6\u95f4", "\u72b6\u6001"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            returnsTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    returnsTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(returnsTable);
        }

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(bundle.getString("returnsManagerInner.panel2.border")));

            //---- label2 ----
            label2.setText(bundle.getString("returnsManagerInner.label2.text"));

            //---- button2 ----
            button2.setText(bundle.getString("returnsManagerInner.button2.text"));
            button2.addActionListener(e -> button2ActionPerformed(e));

            //---- label3 ----
            label3.setText(bundle.getString("returnsManagerInner.label3.text"));

            //---- examineIdTxt ----
            examineIdTxt.setEditable(false);

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(4, 4, 4)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(returnsStateJCB2, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(examineIdTxt, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(button2)
                        .addContainerGap(82, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(examineIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(button2)
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(label2))
                                .addComponent(returnsStateJCB2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(112, Short.MAX_VALUE))
            );
        }

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder(bundle.getString("returnsManagerInner.panel3.border")));

            //---- label4 ----
            label4.setText(bundle.getString("returnsManagerInner.label4.text"));

            //---- deleteIdTxt ----
            deleteIdTxt.setEditable(false);

            //---- button3 ----
            button3.setText(bundle.getString("returnsManagerInner.button3.text"));
            button3.addActionListener(e -> button3ActionPerformed(e));

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(label4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteIdTxt, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(button3)))
                        .addContainerGap(157, Short.MAX_VALUE))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(deleteIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(button3)
                        .addContainerGap(84, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE))
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(48, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.fillTable(new returns(-1,"请选择..."));
        this.fillState();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JComboBox returnsStateJCB;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable returnsTable;
    private JPanel panel2;
    private JLabel label2;
    private JComboBox returnsStateJCB2;
    private JButton button2;
    private JLabel label3;
    private JTextField examineIdTxt;
    private JPanel panel3;
    private JLabel label4;
    private JTextField deleteIdTxt;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}
