## Pub - Sub Pattern

Observer pattern 과 대동소이 하지만 가장 큰 차이점이 있다.

- 실패 시 핸들링 불가능
- client 상태와 별도로 무작정 보내는 server

앞서 observer pattern 설명 시 언급했던 observer pattern 의 한계인데,
이 부분을 보완한게 publisher - subscriber pattern 이다.

```java
import java.util.ArrayList;

public class pubsub {

  public void pubsubMain() {
    List<Integer> integerList = new ArrayList<>();
    integerPublisher().subscribe(integerSubscriber(10, integerList));
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
```

코드를 보면 Observable = Publisher / Observer = Subscriber 라고 볼 수 있기에 비슷하여 그대로 읽으면 문제는 없다.
다만 위의 두 가지 이슈를 커버하기 위해 생겨난 부분이

client 의 상황에 맞게 조절하기 위해 publisher 내의 request 가 있다. 이를 통해 값의 조절을 subscriber 측에서 조정이 가능하다.

error handling 을 위해 onError 와 cancel 이 있고 두 가지를 조합해 subscriber 시 cancel 로 publisher 에 중지 및 진행도 기억이 가능하다.