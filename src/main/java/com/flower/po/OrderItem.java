package com.flower.po;

public class OrderItem {
    private Integer itemId;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Double price;
    private Integer num;

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getNum() { return num; }
    public void setNum(Integer num) { this.num = num; }
}
