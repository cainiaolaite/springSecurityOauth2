package com.hua.dubbo.thread;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程范围内的共享变量
 * A B线程  同时 访问  c d 模块，取出关于自己的线程数据
 *
 * 思路 是以  线程对象为 key  线程需要的数据为 value
 */



public class ThreadScopeShere {

  static Map<Thread,Integer> threadDataMap=new HashMap();

  static class C{
    public Integer getThreadData(){
      synchronized(this){
        Integer integer=threadDataMap.get(Thread.currentThread());
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return integer;
      }
    }
  }

  static class D{
    public Integer getThreadData(){
      synchronized(this){
        Integer integer=threadDataMap.get(Thread.currentThread());
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return integer;
      }
    }
  }

  /**
   * 主函数
   * @param args
   */
  public static void main(String[] args){
    C c=new C();
    D d=new D();

    for(int i=0;i<200;i++){
      new Thread(()->{
        Integer integer=new Random().nextInt();
        synchronized(threadDataMap){//线程都在调这个公共模块 需要加互斥锁，以免数据没有加入进去
          threadDataMap.put(Thread.currentThread(),integer);
        }
        System.out.println("线程名称："+Thread.currentThread().getName()+"  线程创建的值"+integer);
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        //在访问公共模块时
        Integer integer1=c.getThreadData();
        Integer integer2=d.getThreadData();
        System.out.println("线程名称："+Thread.currentThread().getName()+"  取模块C的值"+integer1);
        System.out.println("线程名称："+Thread.currentThread().getName()+"  取模块D的值"+integer2);

        if(!integer.equals(integer1) || !integer.equals(integer2) ){
          System.out.println("=========================没有加互斥锁吧====================");
        }

      }).start();
    }
  }

}
