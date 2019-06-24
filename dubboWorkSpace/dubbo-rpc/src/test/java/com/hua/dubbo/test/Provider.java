package com.hua.dubbo.test;

import com.hua.dubbo.test.bean.IOrder;
import com.hua.dubbo.test.bean.IOrderImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Provider{

  //服务容器
  private Map<String,Object> containerMap=new HashMap<>();
  //新建线程池
  ExecutorService executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public  void start() throws IOException {
    //准备服务
    containerMap.put(IOrder.class.getName(),new IOrderImpl());

    //启动socket
    ServerSocket serverSocket=new ServerSocket();
    serverSocket.setReuseAddress(true);
    serverSocket.setReceiveBufferSize(1024*10);//接收字符串的大小
//        serverSocket.setSoTimeout(1000*20);
    serverSocket.bind(new InetSocketAddress("localhost",9090));
    Socket socket=null;
    while ((socket=serverSocket.accept()) != null){//接收套接字
      System.out.println("服务提供者接受到socket");
      Socket acceptSocket=socket;
      executorService.execute(()->{
        try {
          ObjectInputStream objectInputStream = new ObjectInputStream(acceptSocket.getInputStream());
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(acceptSocket.getOutputStream());
          //实时接收到参数
          while(true){
            if(Thread.currentThread().isInterrupted()){//判断当前线程是否中断
              break;
            }
            System.out.println("服务提供者接受到对象流");
            //读取参数调用的参数
            String className=objectInputStream.readUTF();//类名
            if(className.equals("1")){
              break;
            }
            String classMethod=objectInputStream.readUTF();//方法名
            Class[] methodParamClasss=(Class[])objectInputStream.readObject();//方法参数
            Object[] methodParamObjects=(Object[])objectInputStream.readObject();//方法参数
            //调用此方法
            Object object=containerMap.get(className);
            //获取对象方法
            Method method=object.getClass().getMethod(classMethod,methodParamClasss);
            //执行方法
            Object result=method.invoke(object,methodParamObjects);
            System.out.println("服务提供者执行了方法，返回了对象+"+result);
            //返回对象
            objectOutputStream.writeObject(result);
          }
          objectInputStream.close();
          objectOutputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        }
      });

    }



  }

  public static void main(String[] args) throws IOException {
    Provider provider=new Provider();
    provider.start();
  }

}


