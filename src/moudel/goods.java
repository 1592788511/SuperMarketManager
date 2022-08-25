package moudel;

/**
 * 商品实体
 */
public class goods {
    private int id;
    private String name;
    private String producer;
    private String moudel;
    private String unit;
    private int typeId;
    private String inventoryQuantity;
    private String lastPurchasingPrice;
    private String PurchasingPrice;
    private float sellingPrice;
    private int saleNumber;
    private String remark;

    public goods() {
    }

    public goods(String name, String producer, int typeId) {
        this.name = name;
        this.producer = producer;
        this.typeId = typeId;
    }

    public goods(String name, String producer, String moudel, String unit, int typeId, String inventoryQuantity, String lastPurchasingPrice, String purchasingPrice, float sellingPrice, int saleNumber, String remark) {
        this.name = name;
        this.producer = producer;
        this.moudel = moudel;
        this.unit = unit;
        this.typeId = typeId;
        this.inventoryQuantity = inventoryQuantity;
        this.lastPurchasingPrice = lastPurchasingPrice;
        PurchasingPrice = purchasingPrice;
        this.sellingPrice = sellingPrice;
        this.saleNumber = saleNumber;
        this.remark = remark;
    }

    public goods(int id, String name, String producer, String moudel, String unit, int typeId, String inventoryQuantity, String lastPurchasingPrice, String purchasingPrice, float sellingPrice, int saleNumber, String remark) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.moudel = moudel;
        this.unit = unit;
        this.typeId = typeId;
        this.inventoryQuantity = inventoryQuantity;
        this.lastPurchasingPrice = lastPurchasingPrice;
        PurchasingPrice = purchasingPrice;
        this.sellingPrice = sellingPrice;
        this.saleNumber = saleNumber;
        this.remark = remark;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getMoudel() {
        return moudel;
    }

    public void setMoudel(String moudel) {
        this.moudel = moudel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(String inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public String getLastPurchasingPrice() {
        return lastPurchasingPrice;
    }

    public void setLastPurchasingPrice(String lastPurchasingPrice) {
        this.lastPurchasingPrice = lastPurchasingPrice;
    }

    public String getPurchasingPrice() {
        return PurchasingPrice;
    }

    public void setPurchasingPrice(String purchasingPrice) {
        PurchasingPrice = purchasingPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
