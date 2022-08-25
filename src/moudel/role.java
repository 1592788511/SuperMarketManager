package moudel;

public class role {
    private int id;
    private String name;
    private String roleRemark;

    public role() {
    }

    public role(String name, String roleRemark) {
        this.name = name;
        this.roleRemark = roleRemark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark;
    }

    @Override
    public String toString() {
        return  name;
    }
}
