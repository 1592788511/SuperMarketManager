package util;

import java.util.ResourceBundle;
import java.sql.*;
/**
 * 数据库工具类
 *
 */
public class DBUtil {

    /**
     * 获取数据库连接
     * @return conn
     */

    private DBUtil(){}

    public static Connection getConnection(){
        ResourceBundle bundle = ResourceBundle.getBundle("util.jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);

        }catch(Exception e){
            e.printStackTrace();
        } finally {
            return  conn;
        }
    }

    /**
     * 关闭数据库连接
     * @param conn
     * @param ps
     * @param rs
     */
    public static void close(Connection conn,Statement ps,ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        try{
            if(ps != null){
                ps.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        try{
            if(conn != null){
                conn.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }

}
