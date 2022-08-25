package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import moudel.*;
import util.*;


/**
 * 退货Dao类
 */
public class returnsDao {

    private returnsDao(){}

    /**
     * 审核订单
     * @param conn
     * @param id
     * @param state
     * @return
     * @throws Exception
     */
    public static int examine(Connection conn,String id,String state) throws Exception{
        String sql = "update t_returns set state = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(2,id);
        ps.setString(1,state);

        return ps.executeUpdate();
    }

    /**
     * 删除退货记录
     * @param conn
     * @param id
     * @return
     * @throws Exception
     */
    public static int delete(Connection conn,String id) throws Exception{
        String sql = "delete from t_returns where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);

        return ps.executeUpdate();
    }

    /**
     * 查询退货集合
     * @param conn
     * @param returns
     * @return
     */
    public static ResultSet list(Connection conn, returns returns) throws Exception{
        StringBuffer sb = new StringBuffer("select * from t_returns tr,t_goods tg where tr.goods_id = tg.id and tr.goods_typeId = tg.type_id");
        if(!returns.getState().equals("请选择...")){
            sb.append(" and tr.state = '"+returns.getState()+"'");
        }
        PreparedStatement ps = conn.prepareStatement(sb.toString());
        return ps.executeQuery();
    }

}
