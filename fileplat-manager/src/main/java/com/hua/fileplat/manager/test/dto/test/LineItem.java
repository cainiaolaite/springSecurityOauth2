package com.hua.fileplat.manager.test.dto.test;

/**
 * 订单项
 */
public class LineItem {
    private int itemId;//订单项编号
    private int orderId;//订单编号
    private int quantity;//数量
    private long vendorPartNumber;//供应商零件数量  vendorPart Number

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getVendorPartNumber() {
        return vendorPartNumber;
    }

    public void setVendorPartNumber(long vendorPartNumber) {
        this.vendorPartNumber = vendorPartNumber;
    }
}
