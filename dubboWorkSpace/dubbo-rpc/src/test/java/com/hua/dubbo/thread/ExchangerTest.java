package com.hua.dubbo.thread;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * exchanger 交换机
 */
public class ExchangerTest {

  public static void main(String[] args){
    Exchanger<String> exchanger=new Exchanger<>();
    new Thread(()->{
      String str="wuhaihua";
      try {
        System.out.println(Thread.currentThread().getName()+" 交换信息之前："+str);
        Thread.sleep(new Random().nextInt(3000));
        str=exchanger.exchange(str);
        System.out.println(Thread.currentThread().getName()+" 交换信息之后："+str);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(()->{
      String str="zhangsan";
      try {
        System.out.println(Thread.currentThread().getName()+" 交换信息之前："+str);
        Thread.sleep(new Random().nextInt(3000));
        str=exchanger.exchange(str);
        System.out.println(Thread.currentThread().getName()+" 交换信息之后："+str);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

  }




}
