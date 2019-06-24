package com.hua.dubbo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有四个线程  其中两个线程 对 j 进行加一，另外连个对 j 进行 减一
 */
public class LockTest {

  static class C{
    private int j=0;

    public void add(){
      j++;
      System.out.println("线程名称"+Thread.currentThread().getName()+"  j="+j);
    }

    public void remove(){
      j--;
      System.out.println("线程名称"+Thread.currentThread().getName()+"  j="+j);
    }
  }

  public static void main(String[] args){
    C c=new C();
    Lock lock=new ReentrantLock();

    for(int i=0;i<2;i++){
      new Thread(()->{
        lock.lock();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          c.add();
        lock.unlock();
      }).start();
    }

    for(int i=0;i<2;i++){
      new Thread(()->{
        lock.lock();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          c.remove();
        lock.unlock();
      }).start();
    }
  }

}
