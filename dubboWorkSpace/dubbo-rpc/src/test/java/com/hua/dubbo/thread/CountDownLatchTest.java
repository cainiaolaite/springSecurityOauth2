package com.hua.dubbo.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Latch 门闩
 * CountDownLath 解释：
 *     CountDownlatch 是通过一个计数器来实现的，计数器的初始值为线程的数量。
 *                   每当一个线程完成了自己的任务后，计数器的的值就会减1.
 *                   当计数器值到达0时，它标识所有的线程已经完成任务，然后
 *                   在闭锁上等待的线程就可以恢复执行任务。
 *      线程统计锁
 *
 *     主线程 等待 子线程执行完成后执行，其中通过计数实现，子线程执行完成后计数减一，当为0时主线程执行。
 *
 *
 */
public class CountDownLatchTest {
  /**
   * countDown（） 方法是统计减一，如果没有执行主线程会一直等待
   * @throws InterruptedException
   */
  public static void countDownLatchTest() throws InterruptedException {
    //1 线程数
    CountDownLatch countDownLatch=new CountDownLatch(1);
    new Thread(new Runnable() {
      @Override
      public void run() {
         System.out.println("让子线程休眠两秒");
        try {
          Thread.sleep(1000*2);
          System.out.println("子线程休眠完毕");
          countDownLatch.countDown();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    countDownLatch.await();
    System.out.println("执行到我这儿");

  }

  public static void main(String[] strs) throws InterruptedException {
    countDownLatchTest();
  }
}
