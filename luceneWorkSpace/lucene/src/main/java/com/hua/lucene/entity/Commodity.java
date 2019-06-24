package com.hua.lucene.entity;

import java.io.Serializable;

/**
 * 商品
 */
public class Commodity implements Serializable {

  /**
   * id
   */
  private String id;
  /**
   * 商品名称
   */
  private String name;
  /**
   * 商品价格
   */
  private float price;
  /**
   * 描述
   */
  private String description;

  /**
   * 图片路径
   */
  private String imagePath;
  public Commodity(){

  }

  public Commodity(String id, String name, float price, String description, String imagePath) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.imagePath = imagePath;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
