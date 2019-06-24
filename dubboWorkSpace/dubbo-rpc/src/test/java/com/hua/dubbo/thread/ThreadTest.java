package com.hua.dubbo.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 1.继承Thread 类
 */
class ThreadImpl extends Thread{
  @Override
  public void run() {
    for(int i=0;i<3;i++){
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("当前线程名称"+Thread.currentThread().getName()+"=="+i);
    }
  }
}

/**
 * 2.实现Runnable 接口
 */
class RunnableImpl implements Runnable{

  @Override
  public void run() {
    for(int i=0;i<3;i++){
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("当前线程名称"+Thread.currentThread().getName()+"=="+i);
    }
  }
}

public class ThreadTest {
  public boolean falt;

  /**
   * 线程实现的两种方式
   */
  public static void thread(){
    new ThreadImpl().start();//继承Thread 类
    new Thread(new RunnableImpl()).start();//实现Runnable 接口
  }

  /**
   * 线程定时器
   *
   * 定时器 在 某个 时间 调度 一个任务 执行。
   *     schedule 调度
   *     task 任务
   */
  public static void timer() throws InterruptedException {
     //定时器 10 秒之后 会执行 任务 Timer
     new Timer().schedule(new TimerTask(){
       @Override
       public void run() {
          System.out.println("开始执行任务A");
       }
     },3000);

    new Timer().schedule(new TimerTask(){
      @Override
      public void run() {
        System.out.println("开始执行任务B");
      }
    },1000,1000);//3 参数延迟  1000 执行时间间隔  持续执行不中断

     while(true){
       Thread.sleep(1000);
       System.out.println(new Date().getSeconds());
     }
  }


  private synchronized void pringName(String name){
    char[] chars=name.toCharArray();
    for(char c:chars){
      System.out.print(c);
    }
    System.out.println();
  }

  private void pringName1(String name){
    //this 为当前对象，而当前对象为线程共享的，可以拿来作为互斥锁
    //注：互斥锁必须是线程共享的，如果换成 name 那么就会出现线程强占资源
    synchronized(this){
      char[] chars=name.toCharArray();
      for(char c:chars){
        System.out.print(c);
      }
      System.out.println();
    }
  }

  /**
   * 两个线程访问同一个资源的互斥
   *  1.方法体重包含 synchronized 关键字
   *  2.synchronized(共享对象){}
   *       注：synchronized 相当于锁 ，共享对象就相当于钥匙，谁掌握就钥匙谁优先
   */
  public static void synchronizedTest(){
    ThreadTest threadTest=new ThreadTest();
    //线程同步互斥（共享同一资源下互斥）
    new Thread(()->{
      for(int i=0;i<1000;i++){
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        threadTest.pringName("wuhaihua");
      }
    });//.start();
    new Thread(()->{
      for(int i=0;i<1000;i++){
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        threadTest.pringName("wuyanzu");
      }
    });//.start();


    //线程同步互斥（共享同一资源下互斥）
    new Thread(()->{
      for(int i=0;i<1000;i++){
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        threadTest.pringName1("wuhaihua");
      }
    }).start();
    new Thread(()->{
      for(int i=0;i<1000;i++){
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        threadTest.pringName1("wuyanzu");
      }
    }).start();


  }

  public void count(int count) throws InterruptedException {
    synchronized(this){
      if(count == 10 && this.falt){
         this.falt=false;
      }else if(count == 50 && !this.falt){
        this.falt=true;
      }else{
        return;
      }
      for(int i=0;i<count;i++){
        Thread.sleep(10);
        System.out.println(Thread.currentThread().getName()+"==="+i);
      }
    }
  }

  public static void threadSynchronized(){
    ThreadTest threadTest=new ThreadTest();//同一资源互斥
    new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i=0;i<500;i++){
          try {
            threadTest.count(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
    for(int i=0;i<500;i++){
      try {
        threadTest.count(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  public static void main(String[] args) throws InterruptedException {
    //thread();
    //timer();
    //synchronizedTest();

    threadSynchronized();
  }

}
