package com.hua.dubbo.thread;

public class NotifyAndWaitTest {

  static class C{

    public void test(){
      synchronized(this){
        System.out.println(Thread.currentThread().getName()+"我开始执行");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        try {
          System.out.println(Thread.currentThread().getName()+"我在等待");
          this.wait();
          System.out.println(Thread.currentThread().getName()+"我已被唤醒");
          this.notify();
          System.out.println(Thread.currentThread().getName()+" 我开始唤醒别人");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] strs){
    C c=new C();
    String string="";


    new Thread(()->{
      c.test();
    }).start();

    new Thread(()->{
      synchronized (c){
        try {
          System.out.println(Thread.currentThread().getName()+"我也等待");
          c.wait();
          System.out.println(Thread.currentThread().getName()+"我已被唤醒");
          c.notify();
          System.out.println(Thread.currentThread().getName()+" 我开始唤醒别人");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(()->{
      synchronized (c){
        c.notify();
        System.out.println(Thread.currentThread().getName()+" 我开始唤醒别人");
      }
    }).start();





  }

}
