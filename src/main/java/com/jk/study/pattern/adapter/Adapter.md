## Object Adapter Pattern

쉽게 `adapter pattern` 라고들 부르고, 보통 method 생성 시 adaptor 를 붙이고 of 로 생성자를 만든다.

우리가 실생활에서 흔히 아는 `adaptor` 는 충전기가 있다. 콘센트를 통해서 전선주에서 220v의 전기를 사용하는 기구가 한번에 받을 수 있는
양의 전기를 변환하여 어느 콘센트에 꼽든 동일한 전기를 제공해준다.

여기서 컨셉은 `어느 콘센트에 꼽던` 과 `동일한 전기 제공` 이다.

각 집의 콘센트마다 별도의 컨버터가 필요하다면 충전기는 각 콘센트 개수에 맞게 필요할 것이다. 하지만 우리는 어떤 콘센트이던
충전기만 있다면 (220v 와 110v 등 규격은 일단 논외로 치고) 어떤 콘센트에서든 전기를 기기가 원하는 규격에 맞게 제공이 가능하다.

여기서 콘센트는 각자 다른 object 라고 볼 수 있고, 충전기는 그 각자 다른 object 를 각 adaptor 에 return 해주는 값으로 변환해주는 것이라고 생각하면 된다.

```java
import lombok.Data;

@Data
@Entity
public class AdaptorObject {

  private String id;
  private String name;

  private String code;
}
```

```java
import lombok.Data;

@Data
public class ServerReturnA {
  private String id;
  private String nameWithCode;
}
```

```java
import lombok.Data;

@Data
public class ServerReturnB {
  private String idWithName;
  private String code;
}
```

위와 같이 3개의 class 가 있다고 가정하다. `ServerReturnA` 의 경우 A 서버에서 들어오는 response 이고, 
`ServerReturnB` 의 경우 B 서버에서 들어오는 response 라고 하자.<br/>
그리고 `AdaptorObject` 가 실제 우리가 운영하는 서버에서 사용하는 class 라고 하자.

현재 A 와 B 에서 사용하는 object 는 그대로 사용이 불가능하다. 당연하게도, A는 name 과 code 가 합쳐져있는 상태로, B 는 id 와 name 이 합쳐져 있는 상태
이기 때문이다.

사용하려면 특정 delimiter 를 이용해서 분리 후 사용이 가능하다.
(여기에선 `|` 을 delimiter 로 사용했다고 가정한다.)

```java
public interface convertInterfaceA {
  public AdaptorObject convert(ServerReturnA a);
}

public interface convertInterfaceB {
  public AdaptorObject convert(ServerReturnB b);
}

public class converterA implements convertInterfaceA{
  @Override
  public AdaptorObject convert(ServerReturnA a) {
    String[] nameAndCode = a.getNameWithCode().split("|");
    return new AdaptorObject(a.getId(), nameAndCode[0], nameAndCode[1]);
  }
}

public class converterB implements convertInterfaceB{
  @Override
  public AdaptorObject convert(ServerReturnB b) {
    String[] idAndName = b.getIdWithName().split("|");
    return new AdaptorObject(idAndName[0], idAndName[1], b.getCode());
  }
}
```

각 서버로부터 들어오는 데이터를 변환해주는 class 를 만들고 관심사를 분리하기 위해 interface 로 추상화까지 마쳤다.

그런데 인터페이스까지 분리하고 보니 너무 많은 class 가 만들어졌다. 사용하는 입장에선 받는 데이터에 따라 같은 결과를 출력하지만 받는 데이터가
다르기 떄문에 분리되었기 때문인데 이럴 때 adaptor pattern 을 사용 할 수 있다.

```java
public class 
```