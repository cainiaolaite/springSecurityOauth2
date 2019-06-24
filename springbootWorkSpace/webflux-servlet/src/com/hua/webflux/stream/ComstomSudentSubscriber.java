package com.hua.webflux.stream;

import com.hua.webflux.Student;

import java.util.concurrent.Flow;

/**
 * 自定义订阅者
 */
public class ComstomSudentSubscriber implements Flow.Subscriber<Student> {

    //订阅令牌
    private Flow.Subscription subscription;
    /**
     * 调用订阅服务器之前调用的方法
     * @param subscription 订阅
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(3);//订阅3次
        this.subscription=subscription;
    }

    /**
     * 订阅者每接收一次订阅消息数据时，
     * 该方法会被发布者自动调用一次
     * 故，有多少数据接收，该方法就会被执行多少次
     * @param item
     */
    @Override
    public void onNext(Student item) {
        System.out.println("订阅的消息："+item.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subscription.request(10);
    }

    /**
     * 在发生错误时调用
     * @param throwable
     */
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace(System.out);
        subscription.cancel();
    }


    /**
     * 当发布者的所有消息全部正常处理完毕后，
     * 即当发布者关闭时，该方法会被发布者自动调用
     */
    @Override
    public void onComplete() {
        System.out.println("发布者关闭或者订阅者");
    }

    /**
     *
     * @param strs
     */
    public static void main(String[] strs){

    }
}
