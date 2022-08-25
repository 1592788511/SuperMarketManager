package moudel;

public class returns {
    private int id;
    private int goodsId;
    private int goodsTypeId;
    private int customerId;
    private String createDate;
    private String state;

    public returns() {
    }

    public int getId() {
        return id;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public returns(int goodsId, int goodsTypeId, int customerId, String createDate, String state) {
        this.goodsId = goodsId;
        this.goodsTypeId = goodsTypeId;
        this.customerId = customerId;
        this.createDate = createDate;
        this.state = state;
    }

    public returns(int id,String state) {
        this.state = state;
        this.id = id;
    }

    @Override
    public String toString() {
        return state;
    }
}
