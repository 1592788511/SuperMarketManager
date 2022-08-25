package dao;

import moudel.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.*;
import moudel.*;

/**
 * 销售统计Dao类
 */
public class saleDao {

    private saleDao(){}


    /**
     * 删除销售记录
     * @param conn
     * @param id
     * @return
     * @throws Exception
     */
    public static int delete(Connection conn,int id) throws Exception{
        String sql = "delete from t_sale where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,String.valueOf(id));

        return ps.executeUpdate();

    }

    /**
     * 更新sale状态
     * @param conn
     * @param sale
     * @return
     * @throws Exception
     */
    public static int update(Connection conn,sale sale) throws Exception{
        String sql = "update t_sale set state = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1,sale.getState());
        ps.setString(2,String.valueOf(sale.getId()));

        return ps.executeUpdate();

    }

    /**
     * 获取销售统计集合
     * @param conn
     * @param sale
     * @return
     * @throws Exception
     */
    public static ResultSet list(Connection conn, sale sale) throws Exception{
        StringBuffer sb = new StringBuffer("select * from t_sale ts,t_goods tg where ts.goods_id = tg.id");

        PreparedStatement ps = conn.prepareStatement(sb.toString());
        return ps.executeQuery();
    }
}
