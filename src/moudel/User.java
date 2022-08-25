package moudel;

/**
 * 用户实体
 */
public class User {

    private int id;
    private String userName;
    private String passWord;
    private String realName;
    private int roleId;

    public User(){
        super();
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String userName, String passWord, int roleId,String realName) {
        this.userName = userName;
        this.passWord = passWord;
        this.roleId = roleId;
        this.realName = realName;
    }

    public int getRoleId() {
        return roleId;
    }

    public User(int roleId) {
        this.roleId = roleId;
    }

    public User(int id,String passWord, String realName, int roleId) {
        this.id = id;
        this.passWord = passWord;
        this.realName = realName;
        this.roleId = roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
