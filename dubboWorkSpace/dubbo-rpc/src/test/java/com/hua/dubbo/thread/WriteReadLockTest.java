package com.hua.dubbo.thread;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.*;

public class WriteReadLockTest {

  static class CacheData<V,T>{

    private final int MAX_CAPACITY=5;//最大容量
    private int count=0;
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();//读写锁
    private ReadWriteLock readWriteLock1=new ReentrantReadWriteLock();//读写锁
    private Map<V,T> fileMap=new HashMap<>();
    //储存数据
    Map<V,T> map=new HashMap();
    LinkedList<V> linkedList=new LinkedList();

    public boolean put(V v,T t){
      //多线程访问时，上锁
      readWriteLock.writeLock().lock();
      System.out.println("put上了写锁");
      try{
        if(count>=MAX_CAPACITY){
          //不能储存
          V vk=linkedList.getLast();//取得最后一个
          //存入文件
          fileMap.put(vk,t);
          //删除 map 中的最后一个对象
          linkedList.pollLast();
          map.remove(vk);
          count--;
        }
        //放入数据
        map.put(v,t);
        linkedList.addFirst(v);
        count++;
        //检查是否放入缓存中
        if(map.get(v) != null){
          return true;
        }
      }catch (Exception e){
        throw e;
      }finally {
        System.out.println("put放了写锁");
        readWriteLock.writeLock().unlock();
      }
      return false;
    }


    public T tack(V v){
      readWriteLock.readLock().lock();
      System.out.println("tack上了读锁");
      try{
         T t=map.get(v);
         if(t == null){
           t=fileMap.get(v);
           if(t != null){
             //将此数据放入 map 中
             readWriteLock.readLock().unlock();
             System.out.println("tack放了读锁--writeLock");
             readWriteLock.writeLock().lock();//上写锁原来数据重复取出
             System.out.println("tack上了写锁");
             try{
               //判断是否能放入最新数据
               if(count>=MAX_CAPACITY){
                 //不能储存
                 V vk=linkedList.getLast();//取得最后一个（l咯数据）
                 map.remove(vk);
                 //删除 map 中的最后一个对象
                 linkedList.pollLast();
                 count--;
               }
               //放入数据
               map.put(v,t);
               linkedList.addFirst(v);
               count++;
             }finally {
               readWriteLock.readLock().lock();
               System.out.println("tack锁上读锁--writeLock");
               readWriteLock.writeLock().unlock();
               System.out.println("tack放了写锁");
             }
           }
           return t;
         }
      }finally {
          System.out.println("tack放了读锁");
          readWriteLock.readLock().unlock();
      }
      return null;
    }

    public Map map(){
      return map;
    }

    public Map fileMap(){
      return fileMap;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    CacheData cacheData=new CacheData();

    CountDownLatch countDownLatch=new CountDownLatch(15);
    CountDownLatch countDownLatch1=new CountDownLatch(10);

    for(int i=0;i<10;i++){
      final int key=i;
      new Thread(()->{
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        cacheData.put(key,new Random().nextInt());
        countDownLatch.countDown();
        countDownLatch1.countDown();
      }).start();
    }

    countDownLatch1.await();
    for(int i=5;i>0;i--){
      final int key=i;
      new Thread(()->{
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        cacheData.tack(new Random().nextInt(10));
        countDownLatch.countDown();
      }).start();
    }

    countDownLatch.await();
      Map map=cacheData.map();
      Map fileMap=cacheData.fileMap();
      for(Object key:map.keySet()){
        System.out.println("map 中的数据"+"    key="+key+"   value="+map.get(key));
      }

      for(Object key:fileMap.keySet()){
        System.out.println("fileMap 中的数据"+"    key="+key+"   value="+fileMap.get(key));
      }

  }
}

