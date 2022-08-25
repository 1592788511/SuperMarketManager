/*
 * Created by JFormDesigner on Sun Jun 27 23:30:26 CST 2021
 */

package view.inner;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.*;

import dao.goodsDao;
import dao.goodsTypeDao;
import moudel.goodsType;
import util.DBUtil;
import util.stringUtil;
import moudel.*;
/**
 * @author liu
 */
public class goodsManagerInner extends JInternalFrame {
    public goodsManagerInner() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        goodsAddInner gai = new goodsAddInner();
        gai.setBounds(200,200,500,500);
        gai.setVisible(true);
        goodManagerTable.add(gai);
    }

    public void button2ActionPerformed(ActionEvent e) {
        goodsSearchActionPerformed(e);
    }

    private void button4ActionPerformed(ActionEvent e) {
        deleteGoodsActionPerformed(e);
        this.goodsTypeJCB.setSelectedIndex(0);
        this.producer.setText("");
        this.name.setText("");
    }

    private void button5ActionPerformed(ActionEvent e) {
        updateGoodsActionPerformed(e);
    }


    private void button6ActionPerformed(ActionEvent e) {
        resetValue();
    }

    /**
     * 删除商品事件处理
     * @param e
     */
    private void deleteGoodsActionPerformed(ActionEvent e){
        Connection conn = null;
        String id = this.goodsDeleteTxt.getText();

        if(stringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择一个表单!");
            return;
        }

        int confrimNum = JOptionPane.showConfirmDialog(null,"请问真的要删除此表单吗？");
        if(confrimNum == 0){
            try{
                conn = DBUtil.getConnection();
                int n = goodsDao.delete(conn,id);
                if(n==1){
                    JOptionPane.showMessageDialog(null,"商品信息删除成功！");
                }
                else{
                    JOptionPane.showMessageDialog(null,"商品信息删除失败！");
                }
            } catch(Exception ex){
                ex.printStackTrace();
            } finally {
                DBUtil.close(conn,null,null);
            }

        }
    }

    /**
     * 重置表单
     */
    private void resetValue(){
        this.goodsIdTxt.setText("");
        this.goodsNameTxt.setText("");
        this.goodsProducerTxt.setText("");
        this.goodsMoudelTxt.setText("");
        this.goodsUnitTxt.setText("");
        this.goodsInventerQTxt.setText("");
        this.goodsLastPurTxt.setText("");
        this.goodsPurTxt.setText("");
        this.goodsSellPriceTxt.setText("");
        this.goodsSaleNumTxt.setText("");
        this.goodsRemarkTxt.setText("");
        this.goodsTypeJCB2.setSelectedIndex(0);

    }

    /**
     * 商品信息更新事件处理
     * @param e
     */
    private void updateGoodsActionPerformed(ActionEvent e){
        String id = this.goodsIdTxt.getText();
        String name = this.goodsNameTxt.getText();
        String producer = this.goodsProducerTxt.getText();
        String model = this.goodsMoudelTxt.getText();
        String unit = this.goodsUnitTxt.getText();
        String inventory_quantity = this.goodsInventerQTxt.getText();
        String last_purchasing_price = this.goodsLastPurTxt.getText();
        String purchasing_price = this.goodsPurTxt.getText();
        String selling_price_s = this.goodsSellPriceTxt.getText();
        String salenumber_s = this.goodsSaleNumTxt.getText();
        String remarks = this.goodsRemarkTxt.getText();

        if(stringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择一个表单！");
            return;
        }
        if(stringUtil.isEmpty(name)){
            JOptionPane.showMessageDialog(null,"商品名称不可为空！");
            return;
        }
        if(stringUtil.isEmpty(producer)){
            JOptionPane.showMessageDialog(null,"生产商不可为空！");
            return;
        }
        if(stringUtil.isEmpty(this.goodsSellPriceTxt.getText())){
            JOptionPane.showMessageDialog(null,"售价不可为空！");
            return;
        }
        if(stringUtil.isEmpty(purchasing_price)){
            JOptionPane.showMessageDialog(null,"此次采购价格不可为空！");
            return;
        }
        if(stringUtil.isEmpty(last_purchasing_price)){
            JOptionPane.showMessageDialog(null,"上次采购价格不可为空！");
            return;
        }
        if(stringUtil.isEmpty(salenumber_s)){
            JOptionPane.showMessageDialog(null,"销量不可为空！");
            return;
        }

        float selling_price = Float.parseFloat(selling_price_s);
        int salenumber = Integer.parseInt(salenumber_s);

        goodsType goodsType = (goodsType) goodsTypeJCB2.getSelectedItem();
        int type_id = goodsType.getId();

        Connection conn = DBUtil.getConnection();

        goods goods = new goods(Integer.valueOf(id),name, producer, model, unit, type_id, inventory_quantity, last_purchasing_price, purchasing_price, selling_price, salenumber, remarks);
        try{
            int n = goodsDao.update(conn,goods);
            if(n==1){
                JOptionPane.showMessageDialog(null,"商品信息修改成功！");
            }
            else{
                JOptionPane.showMessageDialog(null,"商品信息修改失败！");
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn,null,null);
        }

    }

    /**
     * 商品查询事件处理
     * @param e
     */
    private void goodsSearchActionPerformed(ActionEvent e){
        String name = this.name.getText();
        String producer = this.producer.getText();
        goodsType goodsType = (goodsType) goodsTypeJCB.getSelectedItem();
        int typeId = goodsType.getId();

        goods goods = new goods(name,producer,typeId);

        this.fillTable(goods);


    }



    /**
     *初始化下拉框
     */
    private void fillGoodsType(){
        Connection conn = null;
        goodsType goodsType = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = goodsTypeDao.list(conn,new goodsType());

            goodsType = new goodsType();
            goodsType.setGoodsTypeName("请选择...");
            goodsType.setId(-1);
            this.goodsTypeJCB.addItem(goodsType);
            this.goodsTypeJCB2.addItem(goodsType);

            while(rs.next()){
                goodsType = new goodsType();
                goodsType.setGoodsTypeName(rs.getString("name"));
                goodsType.setId(rs.getInt("id"));
                this.goodsTypeJCB.addItem(goodsType);
                this.goodsTypeJCB2.addItem(goodsType);
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally{
            DBUtil.close(conn,null,null);
        }
    }

    /**
     *填充表格
     * @param goods
     */
    private void fillTable(goods goods){
        DefaultTableModel dtm = (DefaultTableModel) goodsTable.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = goodsDao.list(conn,goods);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("producer"));
                v.add(rs.getString("model"));
                v.add(rs.getString("unit"));
                v.add(rs.getString("gt.name"));
                v.add(rs.getString("inventory_quantity"));
                v.add(rs.getString("last_purchasing_price"));
                v.add(rs.getString("purchasing_price"));
                v.add(rs.getString("selling_price"));
                v.add(rs.getString("salenumber"));
                v.add(rs.getString("remarks"));
                dtm.addRow(v);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,null,null);
        }

    }

    /**
     * 表格点击事件
     * @param e
     */
    private void goodsTableMousePressed(MouseEvent e) {
        int row = goodsTable.getSelectedRow();
        goodsIdTxt.setText((String)goodsTable.getValueAt(row,0));
        goodsDeleteTxt.setText((String)goodsTable.getValueAt(row,0));
        goodsNameTxt.setText((String)goodsTable.getValueAt(row,1));
        goodsProducerTxt.setText((String)goodsTable.getValueAt(row,2));
        goodsMoudelTxt.setText((String)goodsTable.getValueAt(row,3));
        goodsUnitTxt.setText((String)goodsTable.getValueAt(row,4));
        goodsInventerQTxt.setText((String)goodsTable.getValueAt(row,6));
        goodsLastPurTxt.setText((String)goodsTable.getValueAt(row,7));
        goodsPurTxt.setText((String)goodsTable.getValueAt(row,8));
        goodsSellPriceTxt.setText((String)goodsTable.getValueAt(row,9));
        goodsSaleNumTxt.setText((String)goodsTable.getValueAt(row,10));
        goodsRemarkTxt.setText((String)goodsTable.getValueAt(row,11));

        String goodsTypeName = (String)goodsTable.getValueAt(row,5);

        int n = this.goodsTypeJCB2.getItemCount();
        for(int i=0;i<n;i++){
            goodsType item = (goodsType) this.goodsTypeJCB2.getItemAt(i);
            if(item.getGoodsTypeName().equals(goodsTypeName)){
                this.goodsTypeJCB2.setSelectedIndex(i);
            }

        }

    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        goodManagerTable = new JDesktopPane();
        button1 = new JButton();
        panel1 = new JPanel();
        label1 = new JLabel();
        name = new JTextField();
        label2 = new JLabel();
        producer = new JTextField();
        goodsTypeJCB = new JComboBox();
        label3 = new JLabel();
        button2 = new JButton();
        scrollPane1 = new JScrollPane();
        goodsTable = new JTable();
        panel2 = new JPanel();
        label4 = new JLabel();
        goodsNameTxt = new JTextField();
        goodsProducerTxt = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        goodsMoudelTxt = new JTextField();
        goodsUnitTxt = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        goodsTypeJCB2 = new JComboBox();
        goodsInventerQTxt = new JTextField();
        label9 = new JLabel();
        label10 = new JLabel();
        goodsLastPurTxt = new JTextField();
        goodsPurTxt = new JTextField();
        label11 = new JLabel();
        label12 = new JLabel();
        goodsSellPriceTxt = new JTextField();
        label13 = new JLabel();
        label14 = new JLabel();
        goodsRemarkTxt = new JTextField();
        button5 = new JButton();
        button6 = new JButton();
        goodsSaleNumTxt = new JTextField();
        label16 = new JLabel();
        goodsIdTxt = new JTextField();
        panel3 = new JPanel();
        button4 = new JButton();
        goodsDeleteTxt = new JTextField();
        label15 = new JLabel();

        //======== this ========
        setVisible(true);
        setIconifiable(true);
        setClosable(true);
        setTitle(bundle.getString("goodsManagerInner.this.title"));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== goodManagerTable ========
        {

            //---- button1 ----
            button1.setText(bundle.getString("goodsManagerInner.button1.text"));
            button1.addActionListener(e -> button1ActionPerformed(e));
            goodManagerTable.add(button1, JLayeredPane.DEFAULT_LAYER);
            button1.setBounds(860, 490, 150, 270);

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder(bundle.getString("goodsManagerInner.panel1.border")));

                //---- label1 ----
                label1.setText(bundle.getString("goodsManagerInner.label1.text"));

                //---- label2 ----
                label2.setText(bundle.getString("goodsManagerInner.label2.text"));

                //---- label3 ----
                label3.setText(bundle.getString("goodsManagerInner.label3.text"));

                //---- button2 ----
                button2.setText(bundle.getString("goodsManagerInner.button2.text"));
                button2.addActionListener(e -> button2ActionPerformed(e));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(name, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(label2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(producer, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(label3)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(goodsTypeJCB, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(42, Short.MAX_VALUE))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label1)
                                .addComponent(label2)
                                .addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(goodsTypeJCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3)
                                .addComponent(producer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button2))
                            .addContainerGap(15, Short.MAX_VALUE))
                );
            }
            goodManagerTable.add(panel1, JLayeredPane.DEFAULT_LAYER);
            panel1.setBounds(95, 10, 980, 70);

            //======== scrollPane1 ========
            {

                //---- goodsTable ----
                goodsTable.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u751f\u4ea7\u5546", "\u5305\u88c5", "\u8ba1\u91cf\u5355\u4f4d", "\u5546\u54c1\u7c7b\u522b", "\u5e93\u5b58", "\u4e0a\u6b21\u91c7\u8d2d\u4ef7\u683c", "\u6b64\u6b21\u91c7\u8d2d\u4ef7\u683c", "\u552e\u4ef7", "\u51fa\u552e\u91cf", "\u5907\u6ce8"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false, false, false, false, false, false, false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                goodsTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        goodsTableMousePressed(e);
                    }
                });
                scrollPane1.setViewportView(goodsTable);
            }
            goodManagerTable.add(scrollPane1, JLayeredPane.DEFAULT_LAYER);
            scrollPane1.setBounds(25, 85, 1150, 395);

            //======== panel2 ========
            {
                panel2.setBorder(new TitledBorder(bundle.getString("goodsManagerInner.panel2.border")));

                //---- label4 ----
                label4.setText(bundle.getString("goodsManagerInner.label4.text"));

                //---- label5 ----
                label5.setText(bundle.getString("goodsManagerInner.label5.text"));

                //---- label6 ----
                label6.setText(bundle.getString("goodsManagerInner.label6.text"));

                //---- label7 ----
                label7.setText(bundle.getString("goodsManagerInner.label7.text"));

                //---- label8 ----
                label8.setText(bundle.getString("goodsManagerInner.label8.text"));

                //---- label9 ----
                label9.setText(bundle.getString("goodsManagerInner.label9.text"));

                //---- label10 ----
                label10.setText(bundle.getString("goodsManagerInner.label10.text"));

                //---- label11 ----
                label11.setText(bundle.getString("goodsManagerInner.label11.text"));

                //---- label12 ----
                label12.setText(bundle.getString("goodsManagerInner.label12.text"));

                //---- label13 ----
                label13.setText(bundle.getString("goodsManagerInner.label13.text"));

                //---- label14 ----
                label14.setText(bundle.getString("goodsManagerInner.label14.text"));

                //---- button5 ----
                button5.setText(bundle.getString("goodsManagerInner.button5.text"));
                button5.addActionListener(e -> {
			button5ActionPerformed(e);
			button2ActionPerformed(e);
		});

                //---- button6 ----
                button6.setText(bundle.getString("goodsManagerInner.button6.text"));
                button6.addActionListener(e -> button6ActionPerformed(e));

                //---- label16 ----
                label16.setText(bundle.getString("goodsManagerInner.label16.text"));

                //---- goodsIdTxt ----
                goodsIdTxt.setEditable(false);

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createParallelGroup()
                                        .addGroup(panel2Layout.createParallelGroup()
                                            .addGroup(panel2Layout.createParallelGroup()
                                                .addGroup(panel2Layout.createSequentialGroup()
                                                    .addGap(41, 41, 41)
                                                    .addComponent(label9))
                                                .addComponent(label8, GroupLayout.Alignment.TRAILING))
                                            .addComponent(label7, GroupLayout.Alignment.TRAILING))
                                        .addComponent(label6, GroupLayout.Alignment.TRAILING))
                                    .addComponent(label5, GroupLayout.Alignment.TRAILING))
                                .addComponent(label4, GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(goodsProducerTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(goodsMoudelTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(goodsUnitTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addComponent(goodsTypeJCB2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(goodsInventerQTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label10)
                                        .addGap(4, 4, 4)
                                        .addComponent(goodsLastPurTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addComponent(label11)
                                            .addGap(4, 4, 4)
                                            .addComponent(goodsPurTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(label12)
                                                .addComponent(label13))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(goodsSellPriceTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(goodsSaleNumTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label16)
                                        .addComponent(label14))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(goodsRemarkTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(goodsIdTxt, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(button5, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button6, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                            .addGap(59, 59, 59))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addComponent(button5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(button6, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(goodsNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4))
                                    .addGap(6, 6, 6)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(goodsProducerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label5))
                                    .addGap(6, 6, 6)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(goodsMoudelTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label6))
                                    .addGap(9, 9, 9)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(goodsUnitTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label7))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(goodsTypeJCB2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label8))
                                    .addGap(9, 9, 9)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(goodsInventerQTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label9)))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup()
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addGap(84, 84, 84)
                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label13)
                                                .addComponent(goodsSaleNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addGroup(panel2Layout.createParallelGroup()
                                                .addGroup(panel2Layout.createSequentialGroup()
                                                    .addGap(3, 3, 3)
                                                    .addComponent(label10))
                                                .addComponent(goodsLastPurTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addGap(6, 6, 6)
                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label11)
                                                .addComponent(goodsPurTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addGap(3, 3, 3)
                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label12, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(goodsSellPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addGap(34, 34, 34)
                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(goodsRemarkTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label14))))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label16)
                                        .addComponent(goodsIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }
            goodManagerTable.add(panel2, JLayeredPane.DEFAULT_LAYER);
            panel2.setBounds(25, 490, 830, 270);

            //======== panel3 ========
            {

                //---- button4 ----
                button4.setText(bundle.getString("goodsManagerInner.button4.text"));
                button4.addActionListener(e -> {
			button4ActionPerformed(e);
			button2ActionPerformed(e);
		});

                //---- goodsDeleteTxt ----
                goodsDeleteTxt.setEditable(false);

                //---- label15 ----
                label15.setText(bundle.getString("goodsManagerInner.label15.text"));

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addComponent(label15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addComponent(button4, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addGap(64, 64, 64)
                                    .addComponent(goodsDeleteTxt, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label15, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addComponent(goodsDeleteTxt, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button4, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
            }
            goodManagerTable.add(panel3, JLayeredPane.DEFAULT_LAYER);
            panel3.setBounds(1015, 490, 160, 270);
        }
        contentPane.add(goodManagerTable, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.fillGoodsType();
        this.fillTable(new goods());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JDesktopPane goodManagerTable;
    private JButton button1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField name;
    private JLabel label2;
    private JTextField producer;
    private JComboBox goodsTypeJCB;
    private JLabel label3;
    private JButton button2;
    private JScrollPane scrollPane1;
    private JTable goodsTable;
    private JPanel panel2;
    private JLabel label4;
    private JTextField goodsNameTxt;
    private JTextField goodsProducerTxt;
    private JLabel label5;
    private JLabel label6;
    private JTextField goodsMoudelTxt;
    private JTextField goodsUnitTxt;
    private JLabel label7;
    private JLabel label8;
    private JComboBox goodsTypeJCB2;
    private JTextField goodsInventerQTxt;
    private JLabel label9;
    private JLabel label10;
    private JTextField goodsLastPurTxt;
    private JTextField goodsPurTxt;
    private JLabel label11;
    private JLabel label12;
    private JTextField goodsSellPriceTxt;
    private JLabel label13;
    private JLabel label14;
    private JTextField goodsRemarkTxt;
    private JButton button5;
    private JButton button6;
    private JTextField goodsSaleNumTxt;
    private JLabel label16;
    private JTextField goodsIdTxt;
    private JPanel panel3;
    private JButton button4;
    private JTextField goodsDeleteTxt;
    private JLabel label15;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
