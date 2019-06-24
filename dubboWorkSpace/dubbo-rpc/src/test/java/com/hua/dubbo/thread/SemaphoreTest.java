package com.hua.dubbo.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号灯
 *
 * 在开启信号灯的地方只能允许指定数量的线程
 */
public class SemaphoreTest {

  public static void main(String[] strs){

    Semaphore semaphore=new Semaphore(3);
    for(int index=0;index<10;index++){
      new Thread(()->{
        try {
          semaphore.acquire();//开启信号灯 型号灯的数量  决定着 异步线程的 个数,没有获取信号灯，将会阻塞
          int second=new Random().nextInt(3000);
          System.out.println(Thread.currentThread().getName()+"-------"+second);
          Thread.sleep(second);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          semaphore.release();//释放信号灯
        }
      }).start();
    }


  }




}


