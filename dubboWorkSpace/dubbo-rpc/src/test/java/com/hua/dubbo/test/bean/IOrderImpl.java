package com.hua.dubbo.test.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IOrderImpl implements IOrder {

  public static List<Order> orderList=new ArrayList<>();
  static{
    Order order=new Order();
    order.setNo("123");
    order.setCreateDate(new Date());
    order.setDescription("气球在哪里");
    orderList.add(order);

    order=new Order();
    order.setNo("456");
    order.setCreateDate(new Date());
    order.setDescription("哈哈哈哈");
    orderList.add(order);

    order=new Order();
    order.setNo("789");
    order.setCreateDate(new Date());
    order.setDescription("呵呵呵");
    orderList.add(order);
  }

  @Override
  public Order byOrder(String id) {
    for(Order order:orderList){
      if(order.getNo().equals(id)){
        return order;
      }
    }
    return null;
  }
}
