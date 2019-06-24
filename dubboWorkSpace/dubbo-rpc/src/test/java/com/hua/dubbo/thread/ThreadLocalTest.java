package com.hua.dubbo.thread;

/**
 * 用于储存线程内的数据
 *      线程范围内的数据共享
 */
public class ThreadLocalTest {

  static ThreadLocal<C> threadLocal=new ThreadLocal<>();
  static class C{
    private String name;
    private String sex;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getSex() {
      return sex;
    }

    public void setSex(String sex) {
      this.sex = sex;
    }

    @Override
    public String toString() {
      return "C{" +
          "name='" + name + '\'' +
          ", sex='" + sex + '\'' +
          '}';
    }

    public static  C instance(){
      synchronized(threadLocal){
        C c = threadLocal.get();
        if(c == null){
          c=new C();
          threadLocal.set(c);
        }
        return c;
      }
    }

  }

  static class B{
    public void c(){
      C c=C.instance();
      System.out.println(c.toString());
    }
  }

  public static void main(String[] args){


    new Thread(()->{
      C c=C.instance();
      c.setName("wuhaihua");
      c.setSex("12");
      new B().c();
    }).start();

    new Thread(()->{
      C c=C.instance();
      c.setName("zhangsan");
      c.setSex("22");
      new B().c();
    }).start();

  }

}
