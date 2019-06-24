package com.hua.webflux.stream;

import com.hua.webflux.Student;

import java.util.concurrent.Flow;

public class ComstomProcessor implements Flow.Processor<Student,String> {
    private Flow.Subscriber<? super String> subscriber;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(Student item) {
        System.out.println("processor订阅的消息："+item.toString());
        subscriber.onNext(item.getName());
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }

    @Override
    public void subscribe(Flow.Subscriber<? super String> subscriber) {
        this.subscriber=subscriber;
    }
}
