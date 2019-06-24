package com.hua.dubbo.thread;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 线程安全的 hashMap
 */
public class ConcurrentHashMapTest {

  public static void main(String[] args) throws InterruptedException {
    HashMap hashMap=new HashMap();
    CountDownLatch countDownLatch=new CountDownLatch(10);
    for(int index=0;index<10;index++){
      new Thread(()->{
        for(int i=0;i<10;i++){
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          hashMap.put(i,i);
        }
        countDownLatch.countDown();
      }).start();
    }

    countDownLatch.await();
    for(int index=0;index<10;index++){
      System.out.println(index+"  "+hashMap.get(index));
    }
    System.out.println("--------------------------------------");
    CountDownLatch countDownLatch1=new CountDownLatch(10);
    ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
    for(int index=0;index<10;index++){
      new Thread(()->{
        for(int i=0;i<10;i++){
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          concurrentHashMap.put(i,i);
        }
        countDownLatch1.countDown();
      }).start();
    }

    countDownLatch1.await();
    for(int index=0;index<10;index++){
      System.out.println(index+"  "+concurrentHashMap.get(index));
    }

  }
}
