## Object Adapter Pattern

쉽게 `adapter pattern` 라고들 부르고, 보통 method 생성 시 adapter 를 붙이고 of 로 생성자를 만든다.

우리가 실생활에서 흔히 아는 `adapter` 는 충전기가 있다. 콘센트를 통해서 전선주에서 220v의 전기를 사용하는 기구가 한번에 받을 수 있는
양의 전기를 변환하여 어느 콘센트에 꼽든 동일한 전기를 제공해준다.

여기서 컨셉은 `어느 콘센트에 꼽던` 과 `동일한 전기 제공` 이다.

각 집의 콘센트마다 별도의 컨버터가 필요하다면 충전기는 각 콘센트 개수에 맞게 필요할 것이다. 하지만 우리는 어떤 콘센트이던
충전기만 있다면 (220v 와 110v 등 규격은 일단 논외로 치고) 어떤 콘센트에서든 전기를 기기가 원하는 규격에 맞게 제공이 가능하다.

여기서 콘센트는 각자 다른 object 라고 볼 수 있고, 충전기는 그 각자 다른 object 를 각 adapter 에 return 해주는 값으로 변환해주는 것이라고 생각하면 된다.

```java
import lombok.AllArgsConstructor;
import lombok.Data;

public interface AnimalDoing {

  public void acting();

  public void shouting();
}

public class DogActing implements AnimalDoing {

  @Override
  public void acting() {
    System.out.println("Bite");
  }

  @Override
  public void shouting() {
    System.out.println("Bark");
  }
}
```

이러한 인터페이스가 있다고 가정하자. 

그리고, 이런 인터페이스도 있다고 가정해보자.

```java
public interface InsectActing {
  public void actingInsect();
}

public class InsectActingClass implements InsectActing {
  
  @Override
  public void actingInsect() {
    System.out.println("DO SOMETHING");
  }
}
```

곤충의 경우 소리를 내는게 없다고 가정하고, 이러한 환경을 반영해 InsectActing 인터페이스에는 해당 행동이 존재하지 않는다. 그런데, AnimalActing, InsectActing 이 제법 유사하다.

이럴 때 adapter 를 사용 할 수 있다.

```java
public class InsectAdapter implements AnimalActing {
  private InsectActing insectActing;
  
  public InsectAdapter(InsectActing insectActing) {
    this.insectActing = insectActing;
  }

  @Override
  public void acting() {
    insectActing.actingInsect();
  }

  @Override
  public void shouting() {
    // InsectActing 에 shouting 은 없기에 이렇게 표현
    // 비즈니스 로직에 따라 예외처리 등을 통해 변환
    System.out.println("");
  }
}
```

이렇게 사용 할 경우 AnimalActing 만을 가지고 DogActing, InsectActing 에 대한 사용 및 표현이 가능하다.