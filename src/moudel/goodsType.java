package moudel;

/**
 * 商品类别实体
 */
public class goodsType {
    private int id;
    private String goodsTypeName;

    public goodsType(){};
    public goodsType(String goodsTypeName, String goodsTypeDesc) {
        this.goodsTypeName = goodsTypeName;
        this.goodsTypeDesc = goodsTypeDesc;
    }

    public goodsType(int id, String goodsTypeName, String goodsTypeDesc) {
        this.id = id;
        this.goodsTypeName = goodsTypeName;
        this.goodsTypeDesc = goodsTypeDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsTypeDesc() {
        return goodsTypeDesc;
    }

    public void setGoodsTypeDesc(String goodsTypeDesc) {
        this.goodsTypeDesc = goodsTypeDesc;
    }

    private String goodsTypeDesc;

    @Override
    public String toString() {
        return goodsTypeName;

    }
}
