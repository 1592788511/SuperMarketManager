/*
 * Created by JFormDesigner on Sat Jun 26 12:40:03 CST 2021
 */

package view.capital;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

import moudel.User;
import util.*;
import dao.*;

/**
 * @author liu
 */
public class loginInFrm extends JFrame {
    public loginInFrm() {
        initComponents();
    }

    private void button2ActionPerformed(ActionEvent e) {
        resetValueActionPerformed(e);
    }

    private void button1ActionPerformed(ActionEvent e) {
        loginActionPerformed(e);
    }

    /**
     * 重置事件处理
     * @param evt
     */
    private void resetValueActionPerformed(ActionEvent evt){
        this.userNameText.setText("");
        this.passWordText.setText("");
    }

    /**
     * 登陆事件处理
     * @param evt
     */
    private void loginActionPerformed(ActionEvent evt) {
        String userName = this.userNameText.getText();
        String passWord = new String(this.passWordText.getPassword());
        if(stringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
            return;
        }
        if(stringUtil.isEmpty(passWord)){
            return;
        }
        Connection conn = null;
        try{
            User user = new User(userName,passWord);
            conn = DBUtil.getConnection();
            User currentUser = UserDao.login(conn,user);
            if(currentUser != null){
                dispose();
                JFrame main = new MainFrm(user.getUserName());
                main.setExtendedState(JFrame.MAXIMIZED_BOTH);
                main.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null,"用户名或密码错误！");
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,null,null);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        desktopPane1 = new JDesktopPane();
        label1 = new JLabel();
        label2 = new JLabel();
        userNameText = new JTextField();
        passWordText = new JPasswordField();
        label3 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("Login");
        setResizable(false);
        setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 30));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== desktopPane1 ========
        {

            //---- label1 ----
            label1.setText(bundle.getString("loginInFrm.label1.text"));
            label1.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 36));
            desktopPane1.add(label1, JLayeredPane.DEFAULT_LAYER);
            label1.setBounds(65, 50, 420, 90);

            //---- label2 ----
            label2.setText(bundle.getString("loginInFrm.label2.text"));
            label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            desktopPane1.add(label2, JLayeredPane.DEFAULT_LAYER);
            label2.setBounds(60, 140, 110, 33);
            desktopPane1.add(userNameText, JLayeredPane.DEFAULT_LAYER);
            userNameText.setBounds(170, 150, 179, 21);
            desktopPane1.add(passWordText, JLayeredPane.DEFAULT_LAYER);
            passWordText.setBounds(170, 195, 179, 21);

            //---- label3 ----
            label3.setText(bundle.getString("loginInFrm.label3.text"));
            label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            desktopPane1.add(label3, JLayeredPane.DEFAULT_LAYER);
            label3.setBounds(65, 190, 100, 24);

            //---- button1 ----
            button1.setText(bundle.getString("loginInFrm.button1.text"));
            button1.addActionListener(e -> button1ActionPerformed(e));
            desktopPane1.add(button1, JLayeredPane.DEFAULT_LAYER);
            button1.setBounds(105, 255, 57, 23);

            //---- button2 ----
            button2.setText(bundle.getString("loginInFrm.button2.text"));
            button2.addActionListener(e -> button2ActionPerformed(e));
            desktopPane1.add(button2, JLayeredPane.DEFAULT_LAYER);
            button2.setBounds(265, 255, 57, 23);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(desktopPane1, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(desktopPane1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JDesktopPane desktopPane1;
    private JLabel label1;
    private JLabel label2;
    private JTextField userNameText;
    private JPasswordField passWordText;
    private JLabel label3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}


