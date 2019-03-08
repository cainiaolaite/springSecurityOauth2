package com.hua.fileplat.manager.test.dto.test;

import java.util.Date;

/**
 * 零件
 */
public class Part {
    private int revision;//修订
    private String partNumber;//零件编号
    private String description;//描述
    private Date revisionDate;//修订日期
    private int bomRevision;//物料清单修订
    private String bomPartNumber;//物料清单零件编号

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getBomRevision() {
        return bomRevision;
    }

    public void setBomRevision(int bomRevision) {
        this.bomRevision = bomRevision;
    }

    public String getBomPartNumber() {
        return bomPartNumber;
    }

    public void setBomPartNumber(String bomPartNumber) {
        this.bomPartNumber = bomPartNumber;
    }
}
