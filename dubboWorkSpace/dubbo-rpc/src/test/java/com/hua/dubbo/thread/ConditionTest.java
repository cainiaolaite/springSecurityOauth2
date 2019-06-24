package com.hua.dubbo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程互斥锁
 * 每个锁有若干的Condition(状态)，来实现（wait 和 notify）
 *
 * 三个线程 分为 老大，老二，老三
 *
 * 老大执行一段，老大让老二执行一段，老二让老三执行一段，老三唤醒老二，老二唤醒老师
 *
 */
public class ConditionTest {

  public static void main(String[] args){
    Lock lock=new ReentrantLock();
    Condition condition1=lock.newCondition();
    Condition condition2=lock.newCondition();
    Condition condition3=lock.newCondition();


    new Thread(()->{
      lock.lock();
      try {
        Thread.sleep(10);
        System.out.println("老大先执行一段。");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        condition1.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("老大先执行第二段。");
      condition2.signal();
      lock.unlock();
    }).start();

    new Thread(()->{
      lock.lock();
      try {
        condition2.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      try {
        Thread.sleep(10);
        System.out.println("老二先执行一段。");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        condition2.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("老二先执行第二段。");
      condition3.signal();
      lock.unlock();
    }).start();

    new Thread(()->{
      lock.lock();
      try {
        Thread.sleep(10);
        System.out.println("老三先执行一段。");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        condition1.signal();
        condition3.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("老三先执行第二段。");
      lock.unlock();
    }).start();
  }

}
