package com.hua.project;


import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Test {
  public static void main(String args[]){
    System.out.println(ClassLoader.getSystemClassLoader());
    System.out.println(ClassLoader.getSystemClassLoader().getParent());
    System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());


  }
}

class CustomMap extends  AbstractMap{

  @Override
  public Set<Entry> entrySet() {
    return null;
  }
}
