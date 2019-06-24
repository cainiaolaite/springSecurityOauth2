package com.hua.dubbo.thread;

import java.util.concurrent.*;

/**
 * Callable 和 Future
 */
public class ExecutorTest {
  static class C{
    private String name;

    public C(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public static void main(String[] agrs) throws ExecutionException, InterruptedException {
    ExecutorService threadPool= Executors.newFixedThreadPool(5);
    Future<C> future =threadPool.submit(new Callable<C>() {
      @Override
      public C call() throws Exception {
        Thread.sleep(1000);
        return new C("张三");
      }
    });
    System.out.println("获取结构前"+System.currentTimeMillis());
    C c=future.get();//等待结果
    System.out.println("获取结果后"+System.currentTimeMillis()+" "+c.getName());
    CompletionService<C> completionService=new ExecutorCompletionService<C>(threadPool);
    completionService.submit(new Callable<C>() {
      @Override
      public C call() throws Exception {
        Thread.sleep(1000);
        return new C("张三");
      }
    });

    completionService.submit(new Callable<C>() {
      @Override
      public C call() throws Exception {
        Thread.sleep(1000);
        return new C("李四");
      }
    });

    int i=2;
    while(true){
      Thread.sleep(100);
      //completionService.take();//获取任务结果，并移除，如果没有结果则等待结果
      Future<C> futureResult=completionService.poll();//获取任务结果，没有就返回为空
      if(future == null){
        continue;
      }else{
        i--;
        System.out.println(future.get().getName());
      }
      if(i==0){
        System.out.println("结果获取完成");
        break;
      }
    }






  }


}
