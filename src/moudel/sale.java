package moudel;

public class sale {
    private int id;
    private int goodsId;
    private String goodsName;
    private int goodsTypeId;
    private String customer;
    private float payMoney;
    private String createDate;
    private String state;

    public sale() {}

    public int getId() {
        return id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public sale(String state) {
        this.state = state;
    }

    public sale(int id, String state) {
        this.id = id;
        this.state = state;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(int goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return state;
    }
}
