package com.hua.dubbo.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/***
 * Cyclic  循环
 * Barrier 屏障
 */
public class CyclicBarrierTest {

  public static void main(String[] args){
    CyclicBarrier cyclicBarrier= new CyclicBarrier(3);//循环屏障  多个线程 一起到达 马上回进入下一个执行

    for (int i=0;i<3;i++){
      new Thread(()->{
        int random=new Random().nextInt(2000);
        try {
          Thread.sleep(random);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"  "+random+" 毫秒到达");

        try {
          cyclicBarrier.await();//等待其它线程执行到这里
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"  "+random);

      }).start();
    }
  }

}
