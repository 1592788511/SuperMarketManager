package dao;
import util.*;
import moudel.*;
import java.sql.*;

/**
 * 商品Dao类
 */
public class goodsDao {

    /**
     * 增加商品
     * @param conn
     * @param goods
     * @return
     * @throws Exception
     */
    public static int add(Connection conn,goods goods) throws Exception {
        String sql = "insert into t_goods(inventory_quantity,last_purchasing_price,model,name,producer,purchasing_price,remarks,selling_price,unit,type_id,salenumber) values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,goods.getInventoryQuantity());
        ps.setString(2,goods.getLastPurchasingPrice());
        ps.setString(3,goods.getMoudel());
        ps.setString(4,goods.getName());
        ps.setString(5,goods.getProducer());
        ps.setString(6,goods.getPurchasingPrice());
        ps.setString(7,goods.getRemark());
        ps.setFloat(8,goods.getSellingPrice());
        ps.setString(9,goods.getUnit());
        ps.setInt(10,goods.getTypeId());
        ps.setInt(11,goods.getSaleNumber());

        return ps.executeUpdate();
    }


    /**
     * 查询商品集合
     * @param conn
     * @param goods
     * @return
     * @throws SQLException
     */
    public static ResultSet list(Connection conn,goods goods) throws SQLException{
        StringBuffer sb = new StringBuffer("select * from t_goods g,t_goodsType gt where g.type_id=gt.id");
        if(!stringUtil.isEmpty(goods.getName())){
            sb.append(" and g.name like '%"+goods.getName()+"%'");
        }
        if(!stringUtil.isEmpty(goods.getProducer())){
            sb.append(" and g.producer like '%"+goods.getProducer()+"%'");
        }
        if(goods.getTypeId() >= 1){
            sb.append(" and g.type_id = "+goods.getTypeId());
        }
        PreparedStatement ps = conn.prepareStatement(sb.toString());
        return ps.executeQuery();
    }

    /**
     * 删除商品
     * @param conn
     * @param id
     * @return
     * @throws Exception
     */
    public static int delete(Connection conn,String id) throws Exception{
        String sql = "delete from t_goods where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);

        return ps.executeUpdate();
    }

    /**
     * 更新商品信息
     * @param conn
     * @param goods
     * @return
     * @throws Exception
     */
    public static int update(Connection conn,goods goods) throws Exception{
        String sql = "update t_goods set " + "name = ?" + ",producer = ?" + ",model = ?" + ",unit = ?" + ",type_id = ?" + ",inventory_quantity = ?" + ",last_purchasing_price = ?" + ",purchasing_price = ?" + ",selling_price = ?" + ",salenumber = ?" + ",remarks = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1,goods.getName());
        ps.setString(2,goods.getProducer());
        ps.setString(3,goods.getMoudel());
        ps.setString(4,goods.getUnit());
        ps.setString(5,String.valueOf(goods.getTypeId()));
        ps.setString(6,goods.getInventoryQuantity());
        ps.setString(7,goods.getLastPurchasingPrice());
        ps.setString(8,goods.getPurchasingPrice());
        ps.setString(9,String.valueOf(goods.getSellingPrice()));
        ps.setString(10,String.valueOf(goods.getSaleNumber()));
        ps.setString(11,goods.getRemark());
        ps.setString(12,String.valueOf(goods.getId()));

        return ps.executeUpdate();
    }

    /**
     * 指定商品类别下是否有商品
     * @param conn
     * @param goodsTypeId
     * @return
     * @throws Exception
     */
    public static boolean existGoodsByGoogsTypeId(Connection conn,String goodsTypeId) throws Exception{
        String sql = "select * from t_goods where type_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,goodsTypeId);
        ResultSet rs = ps.executeQuery();
        return rs.next();

    }
}
