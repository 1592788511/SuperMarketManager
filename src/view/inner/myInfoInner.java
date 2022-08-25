/*
 * Created by JFormDesigner on Sun Jul 04 17:37:33 CST 2021
 */

package view.inner;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import dao.*;
import util.DBUtil;

/**
 * @author liu
 */
public class myInfoInner extends JInternalFrame {
    private JTextField userIdTxt;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JTextField userRoleTxt;
    private JLabel label3;
    private JTextField userRealNameTxt;
    private JLabel label4;
    public String userId = "null";
    public myInfoInner(String id) {
        this.userId = id;
        initComponents();
    }

    private void initUserInfo(){
        this.userIdTxt.setText(userId);
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            ResultSet rs = UserDao.list(conn,userId);
            while(rs.next()){
                this.userRealNameTxt.setText(rs.getString("realName"));
                this.userRoleTxt.setText(rs.getString("roleId"));
            }

        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        this.initUserInfo();
    }


    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        userIdTxt = new JTextField();
        button1 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        userRoleTxt = new JTextField();
        label3 = new JLabel();
        userRealNameTxt = new JTextField();
        label4 = new JLabel();

        //======== this ========
        setVisible(true);
        setTitle(bundle.getString("myInfoInner.this.title"));
        setClosable(true);
        setIconifiable(true);
        Container contentPane = getContentPane();

        //---- button1 ----
        button1.setText(bundle.getString("myInfoInner.button1.text"));
        button1.addActionListener(e -> button1ActionPerformed(e));

        //---- label1 ----
        label1.setText(bundle.getString("myInfoInner.label1.text"));

        //---- label2 ----
        label2.setText(bundle.getString("myInfoInner.label2.text"));

        //---- label3 ----
        label3.setText(bundle.getString("myInfoInner.label3.text"));

        //---- label4 ----
        label4.setText(bundle.getString("myInfoInner.label4.text"));
        label4.setIcon(new ImageIcon(getClass().getResource("/images/user/userImg.jpg")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label1)
                                .addComponent(label2)
                                .addComponent(label3))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(userIdTxt, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                .addComponent(userRoleTxt, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                .addComponent(userRealNameTxt, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(button1)))
                    .addContainerGap(29, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label4, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(userIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(userRoleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(userRealNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                    .addGap(58, 58, 58))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables

    // JFormDesigner - End of variables declaration  //GEN-END:variables

}

