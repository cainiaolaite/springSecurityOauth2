package com.hua.lucene.propertie;

import com.hua.lucene.entity.Commodity;
import com.hua.lucene.entity.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 主配置文件加载的要被 辅配置文件快
 */
@ConfigurationProperties(prefix = "data")
@Component
public class CommodityProperties {
  private List<String> tests;

  private List<Test> testList;

  private List<Commodity> commondityList;

  public List<String> getTests() {
    return tests;
  }

  public void setTests(List<String> tests) {
    this.tests = tests;
  }

  public List<Test> getTestList() {
    return testList;
  }

  public void setTestList(List<Test> testList) {
    this.testList = testList;
  }

  public List<Commodity> getCommondityList() {
    return commondityList;
  }

  public void setCommondityList(List<Commodity> commondityList) {
    this.commondityList = commondityList;
  }
}
