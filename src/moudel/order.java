package moudel;

public class order {
    private int id;
    private String code;
    private int supplierId;
    private String state;
    private String remark;
    private float payMoney;
    private String createDate;
    private String payDate;

    public order() {}

    public order(int supplierId) {
        this.supplierId = supplierId;
    }

    public order(String code, int supplierId, String state, String remark, float payMoney, String createDate, String payDate) {
        this.code = code;
        this.supplierId = supplierId;
        this.state = state;
        this.remark = remark;
        this.payMoney = payMoney;
        this.createDate = createDate;
        this.payDate = payDate;
    }

    public order(int id, String code, int supplierId, String state, String remark, float payMoney, String createDate, String payDate) {
        this.id = id;
        this.code = code;
        this.supplierId = supplierId;
        this.state = state;
        this.remark = remark;
        this.payMoney = payMoney;
        this.createDate = createDate;
        this.payDate = payDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(float payMoney) {
        this.payMoney = payMoney;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
}
