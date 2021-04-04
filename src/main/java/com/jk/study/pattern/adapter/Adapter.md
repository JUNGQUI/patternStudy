## Object Adapter Pattern

쉽게 `adapter pattern` 라고들 부르고, 보통 method 생성 시 adaptor 를 붙이고 of 로 생성자를 만든다.

우리가 실생활에서 흔히 아는 `adaptor` 는 충전기가 있다. 콘센트를 통해서 전선주에서 220v의 전기를 사용하는 기구가 한번에 받을 수 있는
양의 전기를 변환하여 어느 콘센트에 꼽든 동일한 전기를 제공해준다.

여기서 컨셉은 `어느 콘센트에 꼽던` 과 `동일한 전기 제공` 이다.

각 집의 콘센트마다 별도의 컨버터가 필요하다면 충전기는 각 콘센트 개수에 맞게 필요할 것이다. 하지만 우리는 어떤 콘센트이던
충전기만 있다면 (220v 와 110v 등 규격은 일단 논외로 치고) 어떤 콘센트에서든 전기를 기기가 원하는 규격에 맞게 제공이 가능하다.

여기서 콘센트는 각자 다른 object 라고 볼 수 있고, 충전기는 그 각자 다른 object 를 각 adaptor 에 return 해주는 값으로 변환해주는 것이라고 생각하면 된다.

```java
public interface Animal {
	public void acting();
    public void shouting();
}

public class Dog {
	private String act = "Bark";
	private String sound = "Bark";
}

public class DogActing implements Animal {
	public String acting(String act) {
		return act;
    }
}
```

이러한 인터페이스가 있다고 가정하자. 

