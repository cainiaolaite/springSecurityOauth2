package com.hua.dubbo.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 数组阻塞队列
 */
public class ArrayBlockQueryTest {


  public static void main(String[] strs){
    ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue(3);


    for (int i=0;i<10;i++){
      new Thread(()->{
        try {
          Thread.sleep(new Random().nextInt(3000));
          String str="ss"+new Random().nextInt(200);
          System.out.println(Thread.currentThread().getName()+"  准备放入数据"+str);
          arrayBlockingQueue.add(str);
          System.out.println(Thread.currentThread().getName()+"  成功放入数据"+str);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    for (int i=0;i<10;i++){
      new Thread(()->{
        try {
          Thread.sleep(new Random().nextInt(3000));
          System.out.println(Thread.currentThread().getName()+"  准备取数据");
          String str=arrayBlockingQueue.take();
          System.out.println(Thread.currentThread().getName()+"  取出数据"+str);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }


  }




}
