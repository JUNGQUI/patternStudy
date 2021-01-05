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