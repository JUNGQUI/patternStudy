package com.jk.study.pattern.pubsub;

import java.util.ArrayList;
import java.util.List;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;

@Component
public class PubSub {
  public List<Integer> integerBuilder() {
    List<Integer> result = new ArrayList<>();

    integerPublisher().subscribe(integerSubscriber(10, result));

    return result;
  }

  private Publisher<Integer> integerPublisher() {
    return subscriber -> subscriber.onSubscribe(
        new Subscription() {
          @Override
          public void request(long n) {
            int i = 0;

            while (i < n) {
              subscriber.onNext(i);
              i++;
            }

            subscriber.onComplete();
          }

          @Override
          public void cancel() {
            subscriber.onComplete();
          }
    });
  }

  private Subscriber<Integer> integerSubscriber(long i, List<Integer> result) {
    return new Subscriber<Integer>() {
      @Override
      public void onSubscribe(Subscription s) {
        s.request(i);
      }

      @Override
      public void onNext(Integer integer) {
        result.add(integer);
        System.out.println("onNext : " + integer);
      }

      @Override
      public void onError(Throwable t) {
        System.out.println("onError : " + t.getMessage());
      }

      @Override
      public void onComplete() {
        System.out.println("onComplete");
      }
    };
  }
}
