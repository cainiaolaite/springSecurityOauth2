package com.hua.dubbo.thread;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {


  public static void main(String[] args){
    CopyOnWriteArrayList<String> copyOnWriteArrayList=new CopyOnWriteArrayList<>();
    copyOnWriteArrayList.add("吴海华");
    copyOnWriteArrayList.add("zhangsan");
    copyOnWriteArrayList.add("lisi");
    copyOnWriteArrayList.add("wangwu");
    for(String str:copyOnWriteArrayList){
      copyOnWriteArrayList.remove(str);
    }


    ArrayList<String> arrayList=new ArrayList<>();
    arrayList.add("吴海华");
    arrayList.add("zhangsan");
    arrayList.add("lisi");
    arrayList.add("wangwu");
    for(String str:arrayList){
      arrayList.remove(str);
    }





  }

}
