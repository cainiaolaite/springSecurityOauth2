package com.hua.dubbo.test;

import com.hua.dubbo.test.bean.IOrder;
import com.hua.dubbo.test.bean.Order;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Cusumer{

  public static class IOrderInvotinerHandler implements InvocationHandler {

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Class classs;

    public IOrderInvotinerHandler(Class classs, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) throws IOException {
      this.classs=classs;
      this.objectInputStream=objectInputStream;
      this.objectOutputStream=objectOutputStream;
    }


    /**
     * 取得对象的类型
     * @param args
     * @return
     */
    public static Class[] objectParamClass(Object[] args){
      if(args==null || args.length == 0){
        return null;
      }else{
        Class[] classes=new Class[args.length];
        int i=0;
        for(Object object:args){
          classes[i]=object.getClass();
          i++;
        }
        return classes;
      }
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("服务消费者正在执行方法");
      //调用方法时需要与socket 通信
      objectOutputStream.writeUTF(classs.getName());//类名
      objectOutputStream.writeUTF(method.getName());//方法名
      objectOutputStream.writeObject(objectParamClass(args));//方法参数class
      objectOutputStream.writeObject(args);//方法参数
      System.out.println("服务消费者发送了参数");
      //读取结果
      Object object = objectInputStream.readObject();
      System.out.println("服务消费者返回了对象"+object);
      objectOutputStream.writeUTF("1");//类名

      return object;
    }
    @Override
    protected void finalize() throws Throwable {
      objectOutputStream.close();
      objectInputStream.close();
    }

  }

  public void start() throws IOException {

    Socket socket=new Socket("localhost",9090);
    ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
    IOrderInvotinerHandler iOrderInvotinerHandler=new IOrderInvotinerHandler(IOrder.class,objectOutputStream,objectInputStream);
    IOrder iOrder= (IOrder) Proxy.newProxyInstance(IOrder.class.getClassLoader(), new Class[]{IOrder.class},iOrderInvotinerHandler);
    System.out.println("服务消费者准备执行方法");
    Order order=iOrder.byOrder("123");
    System.out.println(order.getDescription());

  }


  public static void main(String[] args) throws IOException {
    try {
      Cusumer cusumer=new Cusumer();
      cusumer.start();
    }catch (IOException io){
      io.printStackTrace();
    }

  }

}


