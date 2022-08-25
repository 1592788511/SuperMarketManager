package view.capital;

import view.inner.aboutUsInner;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
            System.out.println("加载炫彩皮肤失败！");
        }

        MainFrm f = new MainFrm("null");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);

    }
}