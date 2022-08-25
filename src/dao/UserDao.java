package dao;

import com.mysql.cj.protocol.Resultset;
import moudel.User;
import moudel.supplier;
import util.stringUtil;

import java.sql.*;

/**
 * 用户Dao
 */
public class UserDao {
    /**
     * 登陆验证
     * @param conn
     * @param user
     * @return resultUser
     * @throws SQLException
     */
    public static User login(Connection conn,User user) throws SQLException {
        User resultUser = null;
        String sql = "select * from t_user where loginName = ? and loginPwd = ?";
        PreparedStatement ps =  conn.prepareStatement(sql);
        ps.setString(1,user.getUserName());
        ps.setString(2,user.getPassWord());
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            resultUser = new User();
            resultUser.setId(rs.getInt("id"));
            resultUser.setUserName(rs.getString("loginName"));
            resultUser.setPassWord(rs.getString("loginPwd"));
            resultUser.setRealName(rs.getString("realName"));
        }


        return resultUser;

    }

    /**
     * 获取用户信息
     * @param conn
     * @param userId
     * @return
     */
    public static ResultSet list(Connection conn,String userId) throws Exception{

        String sql = "select loginName,realName,roleId from t_user where loginName = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userId);

        return ps.executeQuery();
    }


    /**
     * 查询用户集合
     * @param conn
     * @param user
     * @return
     */
    public static ResultSet listTable(Connection conn, User user) throws Exception{
        StringBuffer sb = new StringBuffer("select * from t_user tu,t_role tr where tu.roleId = tr.id");
        if(user.getRoleId()!=-1){
            sb.append(" and tu.roleId = " + user.getRoleId());
        }
        PreparedStatement ps = conn.prepareStatement(sb.toString());
        return ps.executeQuery();
    }

    /**
     * 添加用户
     * @param conn
     * @param user
     * @return
     * @throws Exception
     */
    public static int add(Connection conn,User user) throws Exception{
        String sql = "insert into t_user(loginName,loginPwd,roleId,realName) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,user.getUserName());
        ps.setString(2,user.getPassWord());
        ps.setString(3,String.valueOf(user.getRoleId()));
        ps.setString(4,user.getRealName());

        return ps.executeUpdate();

    }

    /**
     * 删除用户
     * @param conn
     * @param id
     * @return
     * @throws Exception
     */
    public static int delete(Connection conn,int id) throws Exception{
        String sql = "delete from t_user where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,String.valueOf(id));

        return ps.executeUpdate();
    }

    /**
     * 更新用户信息
     * @param conn
     * @param user
     * @return
     * @throws Exception
     */
    public static int update(Connection conn,User user) throws Exception{
        String sql = "update t_user set loginPwd = ?,roleId = ?,realName = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,user.getPassWord());
        ps.setString(2,String.valueOf(user.getRoleId()));
        ps.setString(3,user.getRealName());
        ps.setString(4,String.valueOf(user.getId()));

        return ps.executeUpdate();
    }

}
