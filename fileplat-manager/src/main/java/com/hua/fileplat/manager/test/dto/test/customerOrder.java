package com.hua.fileplat.manager.test.dto.test;

import java.util.Date;

/**
 * 客户订单
 */
public class customerOrder {
    private int orderId;//订单ID
    private int discount;//折扣
    private Date lastUpdate;//最后更新时间
    private String shipmentInfo;//发货信息
    private String status;//状态

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(String shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }
}
