## Observer Pattern

관찰자 패턴, Event loop 를 이용하는 대표적인 패턴으로 spring 에서 대놓고 Observer 라는 class 가 존재한다.

하지만

- 실패 시 핸들링 불가능
- client 상태와 별도로 무작정 보내는 server

이 두 가지 방식으로 인해 java 9 부터 deprecated 되었다.

기본적으로 response 를 제공하는 Observable, request 하는 Observer 로 나뉘어져 있다.

코드로 설명하자면 아래와 같다.

```java
public class JKObservable extends Observable {
  // observable 내에 property 를 정의 할 수도 있다.
  private List<Integer> integers;

  public JKObservable () {
    this.integers = new ArrayList<>();
  }

  public void setIntegers(int i) {
    for (int a = 0; a < i; a++) {
      this.integers.add(a);
    }

    // 변경이 되었다는 것을 Observable 에게 알림
    setChanged();
    // 값을 알려준다. 이 때 Observer 내에 IntegerCollection 이 있기에 접근 가능
    // notifyObservers();
    // 아니면 arg 로 바로 전달도 가능하다.
    notifyObservers(this.integers);
  }
}


public class JKObserver {
  public Observer JKObserverFunction() {
    return new Observer() {
      @Override
      public void update(Observable o, Object arg) {
        // 비즈니스 로직에 대해 정의
        // Observable Object 내에 integerCollection 존재하기 때문에 Observable 에서 가져오거나
        observerIntegers1.addAll(
            ((JKObservable) o).getIntegers()
        );

        // notify 에 collection 등록했기에 arg 를 통해 직접 가져올 수 있다.
        observerIntegers2.addAll((Collection<? extends Integer>) arg);
      }
    };
  }
  
  public void doSomething() {
    // ...
    // observable 생성 후 event 등록
    JKObservable jkObservable = new JKObservable();
    jkObservable.addObserver(integerObserver);
    // 실행, setIntegers 안에 setChanged(), notifyObservers() 정의되어 있음
    jkObservable.setIntegers(10);
    // ...
  }
}
```

핵심적인 부분은 Observable 을 통해 request 에 대한 로직을 수행하는 부분을 만들고 response 를 등록된 Observer 에 전달해준다.