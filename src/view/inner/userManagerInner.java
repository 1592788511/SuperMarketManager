/*
 * Created by JFormDesigner on Sun Jul 04 20:15:20 CST 2021
 */

package view.inner;

import java.awt.event.*;
import javax.swing.border.*;
import dao.supplierDao;
import moudel.User;
import util.DBUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;
import dao.*;
import moudel.*;
import util.*;
/**
 * @author liu
 */
public class userManagerInner extends JInternalFrame {
    public userManagerInner() {
        initComponents();
    }

    private void fillTable(User user){
        DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
        dtm.setRowCount(0);
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = UserDao.listTable(conn,user);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("loginName"));
                v.add(rs.getString("loginPwd"));
                v.add(rs.getString("tr.roleName"));
                v.add(rs.getString("realName"));
                dtm.addRow(v);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,null,null);
        }
    }

    /**
     * 填充下拉框
     */
    private void fillRoleJCB(){
        Connection conn = null;
        role role = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = roleDao.list(conn,new role());

            role = new role();
            role.setName("请选择...");
            role.setId(-1);
            this.roleJCB.addItem(role);
            this.roleJCB2.addItem(role);
            this.roleJCB3.addItem(role);

            while(rs.next()){
                role = new role();
                role.setName(rs.getString("roleName"));
                role.setId(rs.getInt("id"));
                this.roleJCB.addItem(role);
                this.roleJCB2.addItem(role);
                this.roleJCB3.addItem(role);
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally{
            DBUtil.close(conn,null,null);
        }
    }

    private void userTableMousePressed(MouseEvent e) {
        int row = userTable.getSelectedRow();
        this.updateIdTxt.setText((String)userTable.getValueAt(row,0));
        this.deleteIdTxt.setText((String)userTable.getValueAt(row,0));
        this.updateUserNameTxt.setText((String)userTable.getValueAt(row,1));
        this.updatePassWordTxt.setText((String)userTable.getValueAt(row,2));
        this.updateRealNameTxt.setText((String)userTable.getValueAt(row,4));

        String roleName = (String)userTable.getValueAt(row,3);

        int n = this.roleJCB3.getItemCount();
        for(int i=0;i<n;i++){
            role item = (role) this.roleJCB3.getItemAt(i);
            if(item.getName().equals(roleName)){
                this.roleJCB3.setSelectedIndex(i);
            }

        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        searchUserActionPerformed(e);
    }

    /**
     * 搜索用户事件处理
     * @param e
     */
    private void searchUserActionPerformed(ActionEvent e){
        role role = (role)this.roleJCB.getSelectedItem();
        int roleId = role.getId();

        User user = new User(roleId);

        this.fillTable(user);

    }

    private void button2ActionPerformed(ActionEvent e) {
        addUserActionPerfromed(e);
    }

    /**
     * 添加用户事件处理
     * @param e
     */
    private void addUserActionPerfromed(ActionEvent e){
        Connection conn = null;
        String userName = this.addUserNameTxt.getText();
        String passWord = this.addPassWordTxt.getText();
        String realName = this.addRealNameTxt.getText();

        if(stringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(null,"用户名不可为空！");
            return;
        }
        if(stringUtil.isEmpty(passWord)){
            JOptionPane.showMessageDialog(null,"密码不可为空！");
            return;
        }
        if(stringUtil.isEmpty(realName)){
            JOptionPane.showMessageDialog(null,"真实姓名不可为空！");
            return;
        }


        role role = (role)this.roleJCB2.getSelectedItem();
        int roleId = role.getId();
        User user = null;

        if(((role) this.roleJCB2.getSelectedItem()).getId() == -1){
            JOptionPane.showMessageDialog(null,"请选择一个角色！");
            return;
        }

        try{
            conn = DBUtil.getConnection();
            user = new User(userName,passWord,roleId,realName);
            int n = UserDao.add(conn,user);
            if(n == 1){
                JOptionPane.showMessageDialog(null,"添加用户成功！");
                this.fillTable(new User(-1));
            }
            else{
                JOptionPane.showMessageDialog(null,"添加用户失败！");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            DBUtil.close(conn,null,null);
        }
    }

    private void button3ActionPerformed(ActionEvent e) {
        updateUserActionPerformed(e);
    }

    private void updateUserActionPerformed(ActionEvent e){
        Connection conn = null;
        String id = this.updateIdTxt.getText();
        String passWord = this.updatePassWordTxt.getText();
        String realName = this.updateRealNameTxt.getText();


        if(stringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择一个表单！");
            return;
        }

        if(stringUtil.isEmpty(passWord)){
            JOptionPane.showMessageDialog(null,"密码不可为空！");
            return;
        }
        if(stringUtil.isEmpty(realName)){
            JOptionPane.showMessageDialog(null,"真实姓名不可为空！");
            return;
        }

        role role = (role)this.roleJCB3.getSelectedItem();
        int roleId = role.getId();
        User user = null;

        if(((role) this.roleJCB3.getSelectedItem()).getId() == -1){
            JOptionPane.showMessageDialog(null,"请选择一个角色！");
            return;
        }

        try{
            conn = DBUtil.getConnection();
            user = new User(Integer.valueOf(id),passWord,realName,roleId);
            int n = UserDao.update(conn,user);
            if(n == 1){
                JOptionPane.showMessageDialog(null,"更新用户信息成功！");
                this.fillTable(new User(-1));
            }
            else{
                JOptionPane.showMessageDialog(null,"更新用户信息失败！");
            }
        } catch(Exception ex){
            ex.printStackTrace();
        } finally{
            DBUtil.close(conn,null,null);
        }
    }

    private void button4ActionPerformed(ActionEvent e) {
        deleteUserActionPerformed(e);
    }

    /**
     * 删除用户事件处理
     * @param e
     */
    private void deleteUserActionPerformed(ActionEvent e){
        Connection conn = null;
        String deleteId = this.deleteIdTxt.getText();

        if(stringUtil.isEmpty(deleteId)){
            JOptionPane.showMessageDialog(null,"请选择一个表单！");
        }

        try{
            conn = DBUtil.getConnection();
            int n = UserDao.delete(conn,Integer.valueOf(deleteId));
            if(n == 1){
                JOptionPane.showMessageDialog(null,"删除用户信息成功！");
                this.fillTable(new User(-1));
            }
            else{
                JOptionPane.showMessageDialog(null,"删除用户信息失败！");

            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        scrollPane1 = new JScrollPane();
        userTable = new JTable();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        addUserNameTxt = new JTextField();
        addPassWordTxt = new JTextField();
        addRealNameTxt = new JTextField();
        roleJCB2 = new JComboBox();
        button2 = new JButton();
        panel2 = new JPanel();
        label5 = new JLabel();
        roleJCB = new JComboBox();
        button1 = new JButton();
        panel3 = new JPanel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        updateUserNameTxt = new JTextField();
        updatePassWordTxt = new JTextField();
        updateRealNameTxt = new JTextField();
        roleJCB3 = new JComboBox();
        button3 = new JButton();
        label10 = new JLabel();
        updateIdTxt = new JTextField();
        panel4 = new JPanel();
        label11 = new JLabel();
        deleteIdTxt = new JTextField();
        button4 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle(bundle.getString("userManagerInner.this.title"));
        setClosable(true);
        setIconifiable(true);
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- userTable ----
            userTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u89d2\u8272", "\u771f\u5b9e\u59d3\u540d"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            userTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    userTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(userTable);
        }

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(bundle.getString("userManagerInner.panel1.border")));

            //---- label1 ----
            label1.setText(bundle.getString("userManagerInner.label1.text"));

            //---- label2 ----
            label2.setText(bundle.getString("userManagerInner.label2.text"));

            //---- label3 ----
            label3.setText(bundle.getString("userManagerInner.label3.text"));

            //---- label4 ----
            label4.setText(bundle.getString("userManagerInner.label4.text"));

            //---- button2 ----
            button2.setText(bundle.getString("userManagerInner.button2.text"));
            button2.addActionListener(e -> button2ActionPerformed(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addUserNameTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addPassWordTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(label4)
                                        .addComponent(label3, GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(addRealNameTxt, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                        .addComponent(roleJCB2, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(addUserNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label1))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(addPassWordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(roleJCB2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(addRealNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 138, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(bundle.getString("userManagerInner.panel2.border")));

            //---- label5 ----
            label5.setText(bundle.getString("userManagerInner.label5.text"));

            //---- button1 ----
            button1.setText(bundle.getString("userManagerInner.button1.text"));
            button1.addActionListener(e -> button1ActionPerformed(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(label5)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roleJCB, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button1)
                        .addContainerGap(545, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(roleJCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1))
                        .addContainerGap(82, Short.MAX_VALUE))
            );
        }

        //======== panel3 ========
        {
            panel3.setBorder(new TitledBorder(bundle.getString("userManagerInner.panel3.border")));

            //---- label6 ----
            label6.setText(bundle.getString("userManagerInner.label6.text"));

            //---- label7 ----
            label7.setText(bundle.getString("userManagerInner.label7.text"));

            //---- label8 ----
            label8.setText(bundle.getString("userManagerInner.label8.text"));

            //---- label9 ----
            label9.setText(bundle.getString("userManagerInner.label9.text"));

            //---- updateUserNameTxt ----
            updateUserNameTxt.setEditable(false);

            //---- button3 ----
            button3.setText(bundle.getString("userManagerInner.button3.text"));
            button3.addActionListener(e -> button3ActionPerformed(e));

            //---- label10 ----
            label10.setText(bundle.getString("userManagerInner.label10.text"));

            //---- updateIdTxt ----
            updateIdTxt.setEditable(false);

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(GroupLayout.Alignment.LEADING, panel3Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(label8)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(roleJCB3, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label6)
                                        .addComponent(label7)
                                        .addComponent(label10))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel3Layout.createParallelGroup()
                                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(updatePassWordTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(updateUserNameTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                            .addComponent(updateIdTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                                            .addGap(2, 2, 2)))))
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addComponent(label9)
                                .addGap(4, 4, 4)
                                .addComponent(updateRealNameTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button3, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(button3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, panel3Layout.createSequentialGroup()
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label10)
                                    .addComponent(updateIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label6)
                                    .addComponent(updateUserNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(updatePassWordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label7))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(roleJCB3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label8))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(updateRealNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label9))))
                        .addContainerGap(109, Short.MAX_VALUE))
            );
        }

        //======== panel4 ========
        {
            panel4.setBorder(new TitledBorder(bundle.getString("userManagerInner.panel4.border")));

            //---- label11 ----
            label11.setText(bundle.getString("userManagerInner.label11.text"));

            //---- deleteIdTxt ----
            deleteIdTxt.setEditable(false);

            //---- button4 ----
            button4.setText(bundle.getString("userManagerInner.button4.text"));
            button4.addActionListener(e -> button4ActionPerformed(e));

            GroupLayout panel4Layout = new GroupLayout(panel4);
            panel4.setLayout(panel4Layout);
            panel4Layout.setHorizontalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                        .addContainerGap(43, Short.MAX_VALUE)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(button4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addComponent(label11)
                                .addGap(4, 4, 4)
                                .addComponent(deleteIdTxt, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
            );
            panel4Layout.setVerticalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(label11))
                            .addComponent(deleteIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.fillTable(new User(-1));
        this.fillRoleJCB();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable userTable;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField addUserNameTxt;
    private JTextField addPassWordTxt;
    private JTextField addRealNameTxt;
    private JComboBox roleJCB2;
    private JButton button2;
    private JPanel panel2;
    private JLabel label5;
    private JComboBox roleJCB;
    private JButton button1;
    private JPanel panel3;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JTextField updateUserNameTxt;
    private JTextField updatePassWordTxt;
    private JTextField updateRealNameTxt;
    private JComboBox roleJCB3;
    private JButton button3;
    private JLabel label10;
    private JTextField updateIdTxt;
    private JPanel panel4;
    private JLabel label11;
    private JTextField deleteIdTxt;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
