/*
 * Created by JFormDesigner on Sun Jun 27 15:42:05 CST 2021
 */

package view.inner;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import moudel.*;
import util.*;
import dao.*;

/**
 * @author liu
 */
public class goodsTypeManagerInner extends JInternalFrame {
    public goodsTypeManagerInner() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        goodsTypeSearchActionPerformed(e);
    }

    private void button2ActionPerformed(ActionEvent e) {
        goodsTypeAddActionPerformed(e);
        this.sGoogsTypeNameTxt.setText("");
    }

    private void button3ActionPerformed(ActionEvent e) {
        resetValueActionPerformed(e);
    }

    private void button4ActionPerformed(ActionEvent e) {
        goodsTypeUpdateActionPerformed(e);
    }

    private void button5ActionPerformed(ActionEvent e) {
        goodsTypeDeleteActionPerformed(e);
        this.sGoogsTypeNameTxt.setText("");
    }

    /**
     * 表格行点击事件
     * @param e
     */
    private void goodsTypeTableMousePressed(MouseEvent e) {
        int row = goodsTypeTable.getSelectedRow();
        updateGoodsTypeIdTxt.setText((String)goodsTypeTable.getValueAt(row,0));
        deleteGoodsTypeIdTxt.setText((String)goodsTypeTable.getValueAt(row,0));
        updateGoodsTypeNameTxt.setText((String)goodsTypeTable.getValueAt(row,1));
        updateGoodsTypeDescTxt.setText((String)goodsTypeTable.getValueAt(row,2));

    }

    /**
     * 图书类别搜索事件处理
     */
    private void goodsTypeSearchActionPerformed(ActionEvent e){
        String sGoodsTypeName = this.sGoogsTypeNameTxt.getText();
        goodsType gt = new goodsType();
        gt.setGoodsTypeName(sGoodsTypeName);
        this.fillTable(gt);
    }

    /**
     * 重置事件处理
     * @param evt
     */
    private void resetValueActionPerformed(ActionEvent evt){
        this.resetValue();
    }

    /**
     * 重置表单
     */
    private void resetValue(){
        this.goodsTypeDescTxt.setText("");
        this.goodsTypeNameTxt.setText("");
    }

    /**
     * 商品类别添加事件处理
     * @param evt
     */
    private void goodsTypeAddActionPerformed(ActionEvent evt){
        String goodsTypeName = this.goodsTypeNameTxt.getText();
        String goodsTypeDesc = this.goodsTypeDescTxt.getText();

        if(stringUtil.isEmpty(goodsTypeName)){
            JOptionPane.showMessageDialog(null,"商品类别名不可为空！");
            return;
        }

        goodsType goodsType = new goodsType(goodsTypeName,goodsTypeDesc);
        Connection conn = DBUtil.getConnection();

        try{
            int n = goodsTypeDao.add(conn,goodsType);
            if(n==1){
                JOptionPane.showMessageDialog(null,"图书类别添加成功");
                this.resetValue();
            }
            else {
                JOptionPane.showMessageDialog(null,"图书类别添加失败!");
            }

        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"图书类别添加失败!");
        } finally {
            try {
                DBUtil.close(conn,null,null);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 商品类别更新事件处理
     * @param e
     */
    private void goodsTypeUpdateActionPerformed(ActionEvent e){
        String updateId = this.updateGoodsTypeIdTxt.getText();
        String updateName = this.updateGoodsTypeNameTxt.getText();
        String updateDesc = this.updateGoodsTypeDescTxt.getText();

        if(stringUtil.isEmpty(updateId)){
            JOptionPane.showMessageDialog(null,"请选择需要修改的表单!");
            return;
        }

        if(stringUtil.isEmpty(updateName)){
            JOptionPane.showMessageDialog(null,"请输入商品类别名称!");
            return;
        }

        goodsType goodsType = new goodsType(Integer.valueOf(updateId),updateName,updateDesc);
        Connection conn = DBUtil.getConnection();
        try{
            int n = goodsTypeDao.update(conn,goodsType);
            if(n==1){
                JOptionPane.showMessageDialog(null,"图书类别更新成功");
            }
            else {
                JOptionPane.showMessageDialog(null,"图书类别更新失败!");
            }

        } catch(SQLException evt) {
            evt.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }

    }

    /**
     *填充表格
     * @param goodsType
     */
    private void fillTable(goodsType goodsType){
        DefaultTableModel dtm = (DefaultTableModel) goodsTypeTable.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = goodsTypeDao.list(conn,goodsType);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("d"));
                dtm.addRow(v);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,null,null);
        }

    }

    /**
     * 商品类别删除事件处理
     * @param e
     */
    private void goodsTypeDeleteActionPerformed(ActionEvent e){
        String deleteId = this.deleteGoodsTypeIdTxt.getText();

        if(stringUtil.isEmpty(deleteId)){
            JOptionPane.showMessageDialog(null,"请选择一个表单!");
            return;
        }

        Connection conn = DBUtil.getConnection();
        int confirmNum = JOptionPane.showConfirmDialog(null,"确定要删除这条表单吗？");
        if(confirmNum == 0){
            try{
                boolean flag = goodsDao.existGoodsByGoogsTypeId(conn,deleteId);
                if(flag){
                    JOptionPane.showMessageDialog(null,"当前商品类别下有商品，不可删除次类别！");
                    return;
                }

                int n = goodsTypeDao.delete(conn,deleteId);
                if(n==1){
                    JOptionPane.showMessageDialog(null,"商品类别删除成功!");
                }
                else {
                    JOptionPane.showMessageDialog(null,"商品类别删除失败!");
                }

            } catch(Exception evt) {
                evt.printStackTrace();
            } finally {
                DBUtil.close(conn,null,null);
            }
        }


    }

    private void goodsTypeTableMouseClicked(MouseEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        JScrollPane1 = new JScrollPane();
        goodsTypeTable = new JTable();
        label1 = new JLabel();
        sGoogsTypeNameTxt = new JTextField();
        button1 = new JButton();
        panel1 = new JPanel();
        label3 = new JLabel();
        goodsTypeNameTxt = new JTextField();
        goodsTypeDescTxt = new JTextArea();
        label4 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        panel2 = new JPanel();
        label5 = new JLabel();
        updateGoodsTypeIdTxt = new JTextField();
        label6 = new JLabel();
        updateGoodsTypeNameTxt = new JTextField();
        label7 = new JLabel();
        updateGoodsTypeDescTxt = new JTextArea();
        button4 = new JButton();
        panel3 = new JPanel();
        label2 = new JLabel();
        deleteGoodsTypeIdTxt = new JTextField();
        button5 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u5546\u54c1\u7c7b\u522b\u7ba1\u7406");
        setIconifiable(true);
        setClosable(true);
        Container contentPane = getContentPane();

        //======== JScrollPane1 ========
        {

            //---- goodsTypeTable ----
            goodsTypeTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", "", ""},
                    {null, null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u5546\u54c1\u7c7b\u522b\u540d\u79f0", "\u5546\u54c1\u7c7b\u522b\u63cf\u8ff0"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            goodsTypeTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    goodsTypeTableMouseClicked(e);
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    goodsTypeTableMousePressed(e);
                }
            });
            JScrollPane1.setViewportView(goodsTypeTable);
        }

        //---- label1 ----
        label1.setText(bundle.getString("goodsTypeManagerInner.label1.text_2"));

        //---- button1 ----
        button1.setText(bundle.getString("goodsTypeManagerInner.button1.text_2"));
        button1.addActionListener(e -> button1ActionPerformed(e));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(bundle.getString("goodsTypeManagerInner.panel1.border")));

            //---- label3 ----
            label3.setText(bundle.getString("goodsTypeManagerInner.label3.text_2"));

            //---- label4 ----
            label4.setText(bundle.getString("goodsTypeManagerInner.label4.text"));

            //---- button2 ----
            button2.setText(bundle.getString("goodsTypeManagerInner.button2.text_2"));
            button2.addActionListener(e -> {
			button2ActionPerformed(e);
			button1ActionPerformed(e);
		});

            //---- button3 ----
            button3.setText(bundle.getString("goodsTypeManagerInner.button3.text"));
            button3.addActionListener(e -> button3ActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(goodsTypeNameTxt, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label4)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(goodsTypeDescTxt, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(button2)
                                .addGap(62, 62, 62)
                                .addComponent(button3)))
                        .addContainerGap(29, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(goodsTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(goodsTypeDescTxt, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(button2, GroupLayout.Alignment.TRAILING)
                            .addComponent(button3, GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38))
            );
        }

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(bundle.getString("goodsTypeManagerInner.panel2.border")));

            //---- label5 ----
            label5.setText(bundle.getString("goodsTypeManagerInner.label5.text"));

            //---- updateGoodsTypeIdTxt ----
            updateGoodsTypeIdTxt.setEditable(false);

            //---- label6 ----
            label6.setText(bundle.getString("goodsTypeManagerInner.label6.text"));

            //---- label7 ----
            label7.setText(bundle.getString("goodsTypeManagerInner.label7.text"));

            //---- button4 ----
            button4.setText(bundle.getString("goodsTypeManagerInner.button4.text"));
            button4.addActionListener(e -> {
			button4ActionPerformed(e);
			button1ActionPerformed(e);
		});

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label7)
                            .addComponent(label6)
                            .addComponent(label5))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(button4)
                            .addComponent(updateGoodsTypeIdTxt, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateGoodsTypeNameTxt, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateGoodsTypeDescTxt, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(71, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(updateGoodsTypeIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label6)
                            .addComponent(updateGoodsTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(updateGoodsTypeDescTxt, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(button4)
                        .addContainerGap(45, Short.MAX_VALUE))
            );
        }

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder(bundle.getString("goodsTypeManagerInner.panel3.border")));

            //---- label2 ----
            label2.setText(bundle.getString("goodsTypeManagerInner.label2.text_2"));

            //---- deleteGoodsTypeIdTxt ----
            deleteGoodsTypeIdTxt.setEditable(false);

            //---- button5 ----
            button5.setText(bundle.getString("goodsTypeManagerInner.button5.text"));
            button5.addActionListener(e -> {
			button5ActionPerformed(e);
			button1ActionPerformed(e);
			button1ActionPerformed(e);
		});

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(label2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteGoodsTypeIdTxt, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(button5)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(deleteGoodsTypeIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(button5)
                        .addGap(50, 50, 50))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(50, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sGoogsTypeNameTxt, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(JScrollPane1, GroupLayout.PREFERRED_SIZE, 993, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(sGoogsTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(JScrollPane1, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.fillTable(new goodsType());
    }




    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane JScrollPane1;
    private JTable goodsTypeTable;
    private JLabel label1;
    private JTextField sGoogsTypeNameTxt;
    private JButton button1;
    private JPanel panel1;
    private JLabel label3;
    private JTextField goodsTypeNameTxt;
    private JTextArea goodsTypeDescTxt;
    private JLabel label4;
    private JButton button2;
    private JButton button3;
    private JPanel panel2;
    private JLabel label5;
    private JTextField updateGoodsTypeIdTxt;
    private JLabel label6;
    private JTextField updateGoodsTypeNameTxt;
    private JLabel label7;
    private JTextArea updateGoodsTypeDescTxt;
    private JButton button4;
    private JPanel panel3;
    private JLabel label2;
    private JTextField deleteGoodsTypeIdTxt;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
