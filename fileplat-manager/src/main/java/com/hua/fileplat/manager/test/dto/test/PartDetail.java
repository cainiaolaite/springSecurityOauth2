package com.hua.fileplat.manager.test.dto.test;

/**
 * 零件详
 */
public class PartDetail {
    private String partNumber;//零件编号
    private int revision;//修订
    private boolean drawing;//图纸
    private boolean specification;//规范

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public boolean isSpecification() {
        return specification;
    }

    public void setSpecification(boolean specification) {
        this.specification = specification;
    }
}
