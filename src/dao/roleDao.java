package dao;

import moudel.role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class roleDao {

    /**
     * 查询角色集合
     * @param conn
     * @param role
     * @return
     */
    public static ResultSet list(Connection conn, role role) throws Exception{
        StringBuffer sb = new StringBuffer("select * from t_role");
        PreparedStatement ps = conn.prepareStatement(sb.toString());
        return ps.executeQuery();
    }
}
