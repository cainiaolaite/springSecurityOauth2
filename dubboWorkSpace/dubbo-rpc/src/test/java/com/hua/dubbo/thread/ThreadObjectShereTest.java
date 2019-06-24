package com.hua.dubbo.thread;

/**
 * 有四个线程  其中两个线程 对 j 进行加一，另外连个对 j 进行 减一
 */
public class ThreadObjectShereTest {

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

    for(int i=0;i<2;i++){
      new Thread(()->{
        synchronized (c){
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          c.add();
        }
      }).start();
    }

    for(int i=0;i<2;i++){
      new Thread(()->{
        synchronized (c){
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          c.remove();
        }
      }).start();
    }
  }

}
