package com.hua.fileplat.manager.test.dto.test;

/**
 * 供应商零件
 */
public class VendorPart {
    private long vendorPartNumber;//供应商零件号
    private String description;//描述
    private double price;//价格
    private int vendorId;//供应商id
    private String partNumber;//零件编码
    private int partRevision;//修订

    public long getVendorPartNumber() {
        return vendorPartNumber;
    }

    public void setVendorPartNumber(long vendorPartNumber) {
        this.vendorPartNumber = vendorPartNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public int getPartRevision() {
        return partRevision;
    }

    public void setPartRevision(int partRevision) {
        this.partRevision = partRevision;
    }
}
