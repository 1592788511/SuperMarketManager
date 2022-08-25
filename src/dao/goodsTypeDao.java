package dao;

import java.sql.*;
import moudel.*;
import util.*;


/**
 * 商品类别Dao类
 */
public class goodsTypeDao {

    /**
     * 商品类别添加
     * @param conn
     * @param goodsType
     * @return
     */
    public static int add(Connection conn, goodsType goodsType) throws SQLException{
        String sql = "insert into t_goodsType values(null,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,goodsType.getGoodsTypeName());
        ps.setString(2,goodsType.getGoodsTypeDesc());
        return ps.executeUpdate(); //影响的条数
    }

    /**
     * 商品类别删除
     * @param conn
     * @param id
     * @return
     * @throws SQLException
     */
    public static int delete(Connection conn,String id) throws SQLException{
        String sql = "delete from t_goodstype where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        return ps.executeUpdate(); //影响的条数
    }

    /**
     * 商品类别更新
     * @param conn
     * @param goodsType
     * @return
     * @throws SQLException
     */
    public static int update(Connection conn,goodsType goodsType) throws SQLException{
        String sql = "update t_goodstype set name = ?,d = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,goodsType.getGoodsTypeName());
        ps.setString(2,goodsType.getGoodsTypeDesc());
        ps.setString(3,""+goodsType.getId());
        return ps.executeUpdate(); //影响的条数
    }

    /**
     * 查询商品类别集合
     * @param conn
     * @param goodsType
     * @return
     * @throws SQLException
     */
    public static ResultSet list(Connection conn,goodsType goodsType) throws SQLException{
        StringBuffer sb = new StringBuffer("select * from t_goodsType");
        if(!stringUtil.isEmpty(goodsType.getGoodsTypeName())){
            sb.append(" and name like '%"+goodsType.getGoodsTypeName()+"%'");
        }
        PreparedStatement ps = conn.prepareStatement(sb.toString().replaceFirst("and","where"));
        return ps.executeQuery();
    }
}
