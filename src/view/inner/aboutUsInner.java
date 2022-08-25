/*
 * Created by JFormDesigner on Sat Jun 26 14:57:24 CST 2021
 */

package view.inner;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author liu
 */
public class aboutUsInner extends JInternalFrame {
    public aboutUsInner() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("view.logInFrm");
        label1 = new JLabel();

        //======== this ========
        setVisible(true);
        setTitle("AboutUs");
        setClosable(true);
        setIconifiable(true);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText(bundle.getString("aboutUsInner.label1.text"));
        label1.setIcon(new ImageIcon(getClass().getResource("/images/aboutUs/QLU.jpg")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 610, GroupLayout.PREFERRED_SIZE)
                    .addGap(287, 287, 287))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(24, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
