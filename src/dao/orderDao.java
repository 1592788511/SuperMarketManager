package dao;

import java.sql.*;
import moudel.*;
import util.*;
import dao.*;


public class orderDao {

    private orderDao(){}

    /**
     * 查询订单集合
     * @param conn
     * @param order
     * @return
     */
    public static ResultSet list(Connection conn,order order) throws Exception{
        StringBuffer sb = new StringBuffer("select * from t_order o,t_supplier s where o.supplier_id = s.id");
        if(order.getSupplierId()>=1){
            sb.append(" and supplier_id = "+order.getSupplierId());
        }
        PreparedStatement ps = conn.prepareStatement(sb.toString());
        return ps.executeQuery();
    }

    /**
     * 添加订单信息
     * @param conn
     * @param order
     * @return
     * @throws Exception
     */
    public static int add(Connection conn,order order) throws Exception{
        String sql = "insert into t_order(code,supplier_id,state,paymoney,createdate,paydate) values(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,order.getCode());
        ps.setString(2,String.valueOf(order.getSupplierId()));
        ps.setString(3,order.getState());
        ps.setString(4,String.valueOf(order.getPayMoney()));
        ps.setString(5,order.getCreateDate());
        ps.setString(6,order.getPayDate());

        return ps.executeUpdate();
    }

    /**
     * 更新订单信息
     * @param conn
     * @param order
     * @return
     * @throws Exception
     */
    public static int update(Connection conn,order order) throws Exception{
        String sql = "update t_order set code = ?,supplier_id = ?,state = ?,remark = ?,paymoney = ?,createdate = ?,paydate = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,order.getCode());
        ps.setString(2,String.valueOf(order.getSupplierId()));
        ps.setString(3,order.getState());
        ps.setString(4,order.getRemark());
        ps.setString(5,String.valueOf(order.getPayMoney()));
        ps.setString(6,order.getCreateDate());
        ps.setString(7,order.getPayDate());
        ps.setString(8,String.valueOf(order.getId()));

        return ps.executeUpdate();
    }

    /**
     * 删除订单
     * @param conn
     * @param id
     * @return
     * @throws Exception
     */
    public static int delete(Connection conn,int id) throws Exception{
        String sql = "delete from t_order where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,String.valueOf(id));

        return ps.executeUpdate();
    }
}
