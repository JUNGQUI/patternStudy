# patternStudy
다양한 pattern 과 관련된 project


[Observer pattern](/src/main/java/com/jk/study/pattern/observer/README.md)

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

## Proxy Pattern


## Strategy Pattern

하나의 action 에 대해 여러 class 를 상황에 맞게 Runtime 에서 바꿔 유동적으로 request 를 처리하는 패턴을 의미한다.

이렇게 하나의 method 를 상황에 따라 갈아 끼우는 것이 `전략` 적이여서 전략 즉, strategy pattern 으로 명명하게 되었다.

```java
public interface StrategyInterface {
	String methodOne();
	String methodTwo();
	String methodThree();
}

public class StrategyObject {
	private StrategyInterface strategyInterface;

	public StrategyObject(StrategyInterface strategyInterface) {
		this.strategyInterface = strategyInterface;
	}
}

```
이러한 object 가 존재하고 각 상황에 맞게 해당 object 의 method 가 다른 값을 출력해야 한다고 할 때,
```java
public class FirstStrategy implements StrategyInterface {

	@Override public String methodOne() {
		System.out.println("First Strategy, Method One.");
		return "first/one";
	}

	@Override public String methodTwo() {
		System.out.println("First Strategy, Method Two.");
		return "first/two";
	}

	@Override public String methodThree() {
		System.out.println("First Strategy, Method Three.");
		return "first/three";
	}
}

public class SecondStrategy implements StrategyInterface {

	@Override public String methodOne() {
		System.out.println("Second Strategy, Method One.");
		return "second/one";
	}

	@Override public String methodTwo() {
		System.out.println("Second Strategy, Method Two.");
		return "second/two";
	}

	@Override public String methodThree() {
		System.out.println("Second Strategy, Method Three.");
		return "second/three";
	}
}
```

interface 를 상속받아 각 class 에서 별도로 구현하여 구성을 한다면 아래와 같이 사용이 가능하다. 

```java
public class main {
	public void callStrategy() {
		// 기존에 1개만을 사용중이였는데,
		StrategyObject first = new StrategyObject(new FirstStrategy());
		// needs 가 생겨 두 번째 전략이 생성
		StrategyObject second = new StrategyObject(new SecondStrategy());
    }
}
```

## Builder Pattern

Builder pattern 의 기본적으로 setter 를 이용한 무분별한 데이터의 변환을 방지하기 위함이다.

```java
public class Account {
  private String id;
  private String password;
}
```

모든 code 는 각 package 및 test case 에 존재한다.