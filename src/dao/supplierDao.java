package dao;

import moudel.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import moudel.*;
import dao.*;
import util.*;

public class supplierDao {
    /**
     * 查询供应商集合
     * @param conn
     * @param supplier
     * @return
     */
    public static ResultSet list(Connection conn, supplier supplier) throws Exception{
        StringBuffer sb = new StringBuffer("select * from t_supplier");
        if(!stringUtil.isEmpty(supplier.getName())){
            sb.append(" and supplier_id like '%"+supplier.getName()+"%'");
        }
        PreparedStatement ps = conn.prepareStatement(sb.toString().replaceFirst("and","where"));
        return ps.executeQuery();
    }

    /**
     * 添加供应商
     * @param conn
     * @param supplier
     * @return
     * @throws Exception
     */
    public static int add(Connection conn,supplier supplier) throws Exception{
        String sql = "insert into t_supplier(address,contact,name,number,remarks) values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,supplier.getAddress());
        ps.setString(2,supplier.getContact());
        ps.setString(3,supplier.getName());
        ps.setString(4,supplier.getNumber());
        ps.setString(5,supplier.getRemarks());

        return ps.executeUpdate();

    }

    /**
     * 删除供应商
     * @param conn
     * @param id
     * @return
     * @throws Exception
     */
    public static int delete(Connection conn,int id) throws Exception{
        String sql = "delete from t_supplier where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,String.valueOf(id));

        return ps.executeUpdate();
    }

    /**
     * 更新供应商信息
     * @param conn
     * @param supplier
     * @return
     * @throws Exception
     */
    public static int update(Connection conn, supplier supplier) throws Exception{
        String sql = "update t_supplier set address = ?, contact = ?,name = ?,number = ?,remarks = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,supplier.getAddress());
        ps.setString(2,supplier.getContact());
        ps.setString(3,supplier.getName());
        ps.setString(4,supplier.getNumber());
        ps.setString(5,supplier.getRemarks());
        ps.setString(6,String.valueOf(supplier.getId()));

        return ps.executeUpdate();

    }

}
