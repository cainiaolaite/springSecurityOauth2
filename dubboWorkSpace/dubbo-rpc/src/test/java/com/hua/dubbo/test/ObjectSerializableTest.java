package com.hua.dubbo.test;

import com.alibaba.fastjson.JSON;
import com.hua.dubbo.test.bean.IOrder;
import com.hua.dubbo.test.bean.IOrderImpl;
import com.hua.dubbo.test.bean.Order;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对象序列化
 */
public class ObjectSerializableTest {
  private static StringBuffer stringBuffer=new StringBuffer();
  public void provider(String a,String b) throws InterruptedException {
    //bean容器
    Map<String,Object> beanContainer=new HashMap<>();
    beanContainer.put(IOrder.class.getName(),new IOrderImpl());
    Thread thread = new Thread(()-> {
        synchronized (a) {
          if (stringBuffer.toString().equals("")) {
            try {
              a.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          String[] args = stringBuffer.toString().split("\n");
          String className = args[0];
          String methodStr = args[1];
          String paramClass = args[2];
          String param = args[3];
          try {
            Object object = beanContainer.get(className);//取得bean
            Method method = object.getClass().getMethod(methodStr, Class.forName(paramClass));//取得bean的方法
            //执行方法
            Object result = method.invoke(beanContainer.get(className), param);
            //转json
            stringBuffer = new StringBuffer();
            stringBuffer.append(JSON.toJSONString(result));
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          } catch (NoSuchMethodException e) {
            e.printStackTrace();
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          } catch (InvocationTargetException e) {
            e.printStackTrace();
          } finally {
            synchronized (b) {
              b.notify();
            }
          }
        }
      });
      thread.start();

  }


  public void consumer(String a,String b){
    Thread thread=new Thread(()->{
      synchronized (b){
        //需要执行 IOrder 中 byOrder方法 我有参数 123
        String params="123";
        String paramClass="java.lang.String";
        String className="com.hua.dubbo.test.bean.IOrder";
        String method="byOrder";
        stringBuffer=new StringBuffer();
        stringBuffer.append(className+"\n");
        stringBuffer.append(method+"\n");
        stringBuffer.append(paramClass+"\n");
        stringBuffer.append(params);
        synchronized (a){
          a.notify();
        }
        try {
          b.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(stringBuffer);
      }

    });
    thread.start();
  }

  /**
   * 线程不能使用等待方法，等待是用于上锁
   * @param args
   * @throws IOException
   * @throws ClassNotFoundException
   * @throws InterruptedException
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    ObjectSerializableTest objectSerializableTest=new ObjectSerializableTest();
    String a=new String();
    String b=new String();

    objectSerializableTest.provider(a,b);
    objectSerializableTest.consumer(a,b);

//    Order order=new Order();
//    Runnable runnable=new Runnable() {
//      @Override
//      public void run() {
//        for(int i=0;i<10;i++){
//          try {
//            Thread.sleep(1000);
//            System.out.println(Thread.currentThread().getName()+"---"+i);
//            synchronized (order){
//              order.wait();
//            }
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        }
//      }
//    };
//    Thread thread=new Thread(runnable);
//    Thread thread1=new Thread(runnable);
//    thread.start();
//    thread1.start();
  }


}


