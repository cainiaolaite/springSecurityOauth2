1.线程的实现方式
    1.继承 Thread 类，通过 Thread 子类启动
       class CustomThread extends Thread{
            public void run(){ //执行代码 }
       }
    2.实现 Runnable 接口，通过 Thread 类启动
       class CustomRunnable implements Runnable{
            public void run(){ //执行代码 }
       }
    3.线程的生命周期
        创建，就绪，运行，等待，休眠，死亡。

    4.线程的 notify 和 wait
       1.notify 和 wait 只能 在 synchronized(a){ a.wait(); a.notify()} 中执行
       1.线程A 和 B 锁c  线程A中 a.wait() 被阻塞 ，那么B就可以执行了

       C lock=new C();
       new Thread(()->{
          public void run(){
             synchronized(lock){
                Thread.sheep(1000);
                lock.wait();
             }
          }
       }).start();

       new Thread(()->{
            public void run(){
                synchronized(lock){
                   lock.notify();
                }
            }
       }).start();

       运行过程

       thread 0->创建->运行
       thread 1->创建->运行
       thread 0->睡眠（10s）
       thread 1->等待（没有锁）
       thread 0->等待（调用了wait）
       thread 1->继续运行（相当于thread-0 放弃了 锁）
       thread 1->唤醒（唤醒被c执行等待的线程）
       thread 0->继续运行（被唤醒了）
       thread 1->结束
       thread 0->结束

     lock.wait()   锁被释放了 当前线程进入阻塞，等待被唤醒。
     lock.notify() 利用锁唤醒等待的线程，并释放锁。

     注：lock.wait() 锁被释放了 当前线程进入阻塞，等待被唤醒。
         lock.notify() 利用锁唤醒等待的线程，并释放锁。
         lock.notifyAll() 利用锁唤醒所有等待的线程，并释放锁。


2.线程定时器                     (schedule [ˈskedʒuːl] 调度  Task 任务 )
    Timer timer=new Timer();
    TimerTask timerTask=new TimerTask(new Runnable(){
    //执行的代码
    },1000);
    timer.schedule(timerTask);


3.线程同步互斥  (同步：同步协调，一个线程执行，其它线程阻塞)

    1.用于方法
        public static synchronized void add(){
         //执行的代码
        }
        注：synchronized 实现同步，进入这个方法就自动上了锁，而这个锁是一个固定锁。
            （好比和门焊在了一起，只能用在这里）

    2.局部代码块
         public void add(){
            synchronized(lock){
                //执行的代码
            }
         }
         注：lock 只要拿到这个锁的线程 就可以执行代码，否则进入阻塞状态，知道锁被释放
             （公共资源多线程访问要上锁）


4.线程范围内的数据共享

    1.通过 MAP 实现
        Map<Thread,Object> map=new HashMap<>();
        class Test(){
           public void test(){
              //取出当前线程的储存的数据
              Object object=map.get(Thread.currentThread());
           }
        }
        class CustomRunnable implements Runnable{
            public void run(){
               //线程范围内存数据
               map.put(Thread.currentThread(),new Object());
               new Test().test();
            }
        }
        注：current 当前的

    2.通过JDK1.5 concurrent 并发库 中 ThreadLocal 实现
        ThreadLocal<Object> threadLocal=new ThreadLocal();
        class Test(){
           public void test(){
              //取出当前线程的储存的数据
              Object object=threadLocal.get();
           }
        }
        class CustomRunnable implements Runnable{
            public void run(){
               //线程范围内存数据
               threadLocal.set(new Object());
               new Test().test();
            }
        }
        map.get(Thread.currentThread())


    注：线程执行的时候，线程内部的数据，可在这个范围内实现共享。

5.同步互斥对象 jdk1.5 concurrent.atomic 下的类 可以实现同步操作
    1.AtomicInteger
        AtomicInteger atomicInteger=new AtomicInteger();

        for(int i=0;i<10;i++){
            new Thread(()->{atomicInteger.andAntGet(5);}).start();
            new Thread(()->{atomicInteger.andAntGet(-2);}).start();
        }

    注：线程中没有使用同步关键字synchronized，atomicInteger 也能实现同步互斥。

6.线程池 （jdk1.5 concurrent 下的ExecutorService  [executor 执行器]）
    1.固定线程数的线程池 [fixed 固定]
        ExecutorService threadPool=Executors.newFixedThreadPool(5);
        注：线程池里有5个线程，是固定的不可更改的。

    2.缓存线程池
        ExecutorService threadPool=Executors.newCachedThreadPool();
        注：线程池中线程个数根据量而订。

    3.单例线程池
        ExecutorService threadPool=Executors.newSingleThreadPool();
        注：线程池中只有一个线程（single）

    4.调度线程池
       ExecutorService threadPool=Executors.newScheduleThreadPool();
       注：线程可以作为定时器。


    Executor 接口中 executor 执行线程任务
     threadPool.executor(new Runnable(){
        public void run(){
           //执行代码
        }
     });

    ExecutorService implements Executor
    ExecutorService 中有 submit() 可以提交 Runnable 任务  和 Callable


7.Callable 和 Future  (Callable 执行的任务，并且能返回结果。Future 用于等待获取结果)
    ExecutorService threadLocal= Executors.newFixedThreadPool(5);
    Future<C> future =threadLocal.submit(new Callable<C>() {
      @Override
      public C call() throws Exception {
        Thread.sleep(1000);
        return new C("张三");
      }
    });
    System.out.println("获取结构前"+System.currentTimeMillis());
    C c=future.get();//等待结果
    System.out.println("获取结果后"+System.currentTimeMillis()+" "+c.getName());

8.CompletionService  基于 Callable 和 Future 的多任务集中管理类
   例如 ：ExecutorTest

9.Lock锁
   让锁变得更具有面向对象的特征

   Lock lock=new ReentrantLock();//reentrant 可重入的（可以重复使用的锁）

10.读写锁
    1.读锁 与 读锁 不互斥
    2.读锁 与 写锁 互斥
    3.写锁 与 写锁 互斥

    ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    reentrantReadWriteLock.readLock().lock();//加上读锁

    reentrantReadWriteLock.readLock().unLock();//释放读锁
    reentrantReadWriteLock.writeLock().lock();//加上读锁

    reentrantReadWriteLock.readLock().lock();//加上读锁
    reentrantReadWriteLock.writeLock().unLock();//释放写锁

    reentrantReadWriteLock.readLock().unLock();//释放读锁


11.Condition  (wait 和 notify 的实现类)【condition 状态】

   Lock lock=new ReentrantLock();
   Condition condition=lock.newCondition();
   new Thread(()->{
      public void run(){
         lock.lock();
         try{
             //TODO 执行代码
             condition.await();//等待-阻塞-释放锁
         }finally{
            lock.unlock();
         }
      }
   }).start();

   new Thread(()->{
      public void run(){
         lock.lock();
         try{
            //TODO 执行代码
            condition.signal();//唤醒阻塞的线程
         }finally{
            lock.unlock();
         }
      }
   }).start();


12. Semaphore  允许执行的线程个数

    Semaphore semaphore=new Semaphore(3);
    for(int index=0;index<10;index++){
        new Thread(()->{
            semaphore.acquire();//获取信号，只有获取信号灯的才能执行此线程

            //TODO 执行代码

            semaphore.release();//释放信号灯
        }).start();
    }



14. CyclicBarrier (Cyclic 循环 Barrier 屏障)

    CyclicBarrier cyclicBarrier=new CyclicBarrier(3);

    for(int index=0;index<3;index++){
       new Thread(()->{
            Thread.sleep(new Random().nextInt(3000));
            cyclicBarrier.awite();//等待至三个线程都到达此处时，才执行以下操作
       }).start();
    }


15.Exchanger (线程之间交换数据)

    Exchanger exchanger=new Exchanger();

    new Thread(()->{
        String str="wuhaihua";
        System.out.println(Thread.currentThread().getName()+"  交换数据之前："+str);
        exchanger.exchanger(str);
        System.out.println(Thread.currentThread().getName()+"  交换数据之后："+str);
    }).start();

    new Thread(()->{
        String str="zhangsan";
        System.out.println(Thread.currentThread().getName()+"  交换数据之前："+str);
        exchanger.exchanger(str);
        System.out.println(Thread.currentThread().getName()+"  交换数据之后："+str);
    }).start();


16 CountDownLatch   线程计数，当凑足足够的数量时，就唤醒阻塞
    CountDownLatch countDownLatch=new CountDownLatch(3);
    for(int i=0;i<3;i++{
        new Thread(()->{
            //TODO 执行代码
            countDownLatch.countDown();//计数减一
        }).start();
    }
    //当计数统计下降到0 的时候，
    countDownLatch.awaite(); //才会被唤醒


17 线程安全的集合
   ConcurrentHashMap
   ConcurrentLinkedQueue
   ConcurrentNavigableMap
   ConcurrentSkipListMap
   ConcurrentSkipListSet
   CopyOnWriteArrayList
   CopyOnWriteArraySet

   LinkedBlockingDeque
   LinkedBlockingQueue
   PriorityBlockingQueue









