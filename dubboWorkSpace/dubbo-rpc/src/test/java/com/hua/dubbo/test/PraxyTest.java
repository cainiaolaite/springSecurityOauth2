package com.hua.dubbo.test;

import com.hua.dubbo.test.bean.IOrder;
import com.hua.dubbo.test.bean.IOrderImpl;
import com.hua.dubbo.test.bean.Order;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PraxyTest {

  public static void main(String[] args) throws IOException {
    Cusumer cusumer=new Cusumer();
    Provider provider=new Provider();
    provider.start();
    cusumer.start();
  }
}
