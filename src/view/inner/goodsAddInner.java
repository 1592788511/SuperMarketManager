/*
 * Created by JFormDesigner on Sun Jun 27 23:14:25 CST 2021
 */

package view.inner;

import java.awt.event.*;

import com.mysql.cj.util.StringUtils;
import util.*;
import moudel.*;
import dao.*;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author liu
 */
public class goodsAddInner extends JInternalFrame {
    public goodsAddInner() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        goodsAddActionPerformed(e);
    }

    private void button2ActionPerformed(ActionEvent e) {
        resetValueActionPerformed(e);
    }

    /**
     * 商品添加事件处理
     * @param e
     */
    private void goodsAddActionPerformed(ActionEvent e) {
        String goodsName = this.goodsNameTxt.getText();
        String goodsProducer = this.goodsProducerTxt.getText();
        String goodsMoudel = this.goodsMoudelTxt.getText();
        String goodsUnit = this.goodsUnitTxt.getText();
        String goodsInventoryQuantity = this.goodsInventerQTxt.getText();
        String goodsLastPurchasingPrice = this.goodsLastPurTxt.getText();
        String goodsPurchasingPrice = this.goodsPurTxt.getText();
        String goodsSellingPrice = this.goodsSellPriceTxt.getText();
        int goodsSaleNumber = 0;
        String goodsRemark = this.goodsRemarkTxt.getText();

        if(stringUtil.isEmpty(goodsName)){
            JOptionPane.showMessageDialog(null,"商品名称不可为空！");
            return;
        }
        if(stringUtil.isEmpty(goodsProducer)){
            JOptionPane.showMessageDialog(null,"生产商不可为空！");
            return;
        }
        if(stringUtil.isEmpty(goodsSellingPrice)){
            JOptionPane.showMessageDialog(null,"售价不可为空！");
            return;
        }
        if(stringUtil.isEmpty(goodsInventoryQuantity)){
            JOptionPane.showMessageDialog(null,"库存量不可为空！");
            return;
        }
        if(stringUtil.isEmpty(goodsLastPurchasingPrice)){
            JOptionPane.showMessageDialog(null,"上次采购价格不可为空！");
            return;
        }
        if(stringUtil.isEmpty(goodsPurchasingPrice)){
            JOptionPane.showMessageDialog(null,"此次采购价格不可为空！");
            return;
        }

        goodsType goodsType = (goodsType) goodsTypeIdJCB.getSelectedItem();
        int goodsTypeId = goodsType.getId();

        goods goods = new goods(goodsName,goodsProducer,goodsMoudel,goodsUnit,goodsTypeId,goodsInventoryQuantity,goodsLastPurchasingPrice,goodsPurchasingPrice,Float.parseFloat(goodsSellingPrice),goodsSaleNumber,goodsRemark);

        Connection conn = null;

        try{
            conn = DBUtil.getConnection();
            int n = goodsDao.add(conn,goods);
            if(n == 1){
                JOptionPane.showMessageDialog(null,"添加商品成功，请刷新查询后查看！");
            }
            else{
                JOptionPane.showMessageDialog(null,"添加商品失败！");
            }

        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }


    }

    /**
     * 重置表单事件处理
     * @param e
     */
    private void resetValueActionPerformed(ActionEvent e){
        this.goodsNameTxt.setText("");
        this.goodsProducerTxt.setText("");
        this.goodsRemarkTxt.setText("");
        this.goodsProducerTxt.setText("");
        this.goodsMoudelTxt.setText("");
        this.goodsUnitTxt.setText("");
        this.goodsPurTxt.setText("");
        this.goodsLastPurTxt.setText("");
        this.goodsInventerQTxt.setText("");
        this.goodsSellPriceTxt.setText("");


        if(this.goodsTypeIdJCB.getItemCount()>0){
            this.goodsTypeIdJCB.setSelectedIndex(0);
        }

    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        goodsNameTxt = new JTextField();
        goodsProducerTxt = new JTextField();
        goodsMoudelTxt = new JTextField();
        goodsUnitTxt = new JTextField();
        goodsPurTxt = new JTextField();
        goodsLastPurTxt = new JTextField();
        goodsInventerQTxt = new JTextField();
        goodsSellPriceTxt = new JTextField();
        goodsSaleNumTxt = new JTextField();
        button1 = new JButton();
        goodsTypeIdJCB = new JComboBox();
        button2 = new JButton();
        goodsRemarkTxt = new JTextField();

        //======== this ========
        setVisible(true);
        setTitle(bundle.getString("goodsAddInner.this.title"));
        setClosable(true);
        setIconifiable(true);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText(bundle.getString("goodsAddInner.label1.text"));

        //---- label2 ----
        label2.setText(bundle.getString("goodsAddInner.label2.text"));

        //---- label3 ----
        label3.setText(bundle.getString("goodsAddInner.label3.text"));

        //---- label4 ----
        label4.setText(bundle.getString("goodsAddInner.label4.text"));

        //---- label5 ----
        label5.setText(bundle.getString("goodsAddInner.label5.text"));

        //---- label6 ----
        label6.setText(bundle.getString("goodsAddInner.label6.text"));

        //---- label7 ----
        label7.setText(bundle.getString("goodsAddInner.label7.text"));

        //---- label8 ----
        label8.setText(bundle.getString("goodsAddInner.label8.text"));

        //---- label9 ----
        label9.setText(bundle.getString("goodsAddInner.label9.text"));

        //---- label10 ----
        label10.setText(bundle.getString("goodsAddInner.label10.text"));

        //---- label11 ----
        label11.setText(bundle.getString("goodsAddInner.label11.text"));

        //---- goodsSaleNumTxt ----
        goodsSaleNumTxt.setEditable(false);
        goodsSaleNumTxt.setText(bundle.getString("goodsAddInner.goodsSaleNumTxt.text"));

        //---- button1 ----
        button1.setText(bundle.getString("goodsAddInner.button1.text"));
        button1.addActionListener(e -> button1ActionPerformed(e));

        //---- button2 ----
        button2.setText(bundle.getString("goodsAddInner.button2.text"));
        button2.addActionListener(e -> button2ActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(label4))
                                    .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label5)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(goodsUnitTxt, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(goodsTypeIdJCB, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(label2)
                                            .addComponent(label1)
                                            .addComponent(label3))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(goodsNameTxt, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(goodsProducerTxt, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(goodsMoudelTxt, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                                    .addGroup(contentPaneLayout.createSequentialGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(label6)
                                            .addComponent(label7)
                                            .addComponent(label8)
                                            .addComponent(label10)
                                            .addComponent(label9)
                                            .addComponent(label11))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(contentPaneLayout.createParallelGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(goodsSellPriceTxt, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(goodsSaleNumTxt, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                            .addComponent(goodsInventerQTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(goodsLastPurTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(goodsPurTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(goodsRemarkTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(89, 89, 89)
                            .addComponent(button1)
                            .addGap(52, 52, 52)
                            .addComponent(button2)))
                    .addContainerGap(78, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(goodsProducerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label3)
                        .addComponent(goodsMoudelTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(goodsUnitTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(goodsTypeIdJCB, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label5))
                    .addGap(9, 9, 9)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label6)
                                .addComponent(goodsInventerQTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 25, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label7)
                                .addComponent(goodsLastPurTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addGap(9, 9, 9)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(goodsPurTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label8))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(goodsSellPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label9))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(goodsSaleNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label10))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label11)
                        .addComponent(goodsRemarkTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button2)
                        .addComponent(button1))
                    .addContainerGap(56, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        fillGoodsType();
    }

    /**
     * 初始化商品类别选择框
     */
    private void fillGoodsType(){
        Connection conn = null;
        goodsType goodsType = new goodsType();
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = goodsTypeDao.list(conn,goodsType);
            while(rs.next()){
                goodsType = new goodsType();
                goodsType.setId(rs.getInt("id"));
                goodsType.setGoodsTypeName(rs.getString("name"));
                this.goodsTypeIdJCB.addItem(goodsType);

            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBUtil.close(conn,null,null);
        }

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JTextField goodsNameTxt;
    private JTextField goodsProducerTxt;
    private JTextField goodsMoudelTxt;
    private JTextField goodsUnitTxt;
    private JTextField goodsPurTxt;
    private JTextField goodsLastPurTxt;
    private JTextField goodsInventerQTxt;
    private JTextField goodsSellPriceTxt;
    private JTextField goodsSaleNumTxt;
    private JButton button1;
    private JComboBox goodsTypeIdJCB;
    private JButton button2;
    private JTextField goodsRemarkTxt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
