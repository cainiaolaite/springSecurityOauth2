package com.hua.dubbo.test.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单bean
 */
public class Order implements Serializable {
  /**
   * 编号
   */
  private String no;

  /**
   * 描述
   */
  private String description;

  /**
   * 创建日期
   */
  private Date createDate;

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }



}
