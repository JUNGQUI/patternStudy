## Object Adapter Pattern

쉽게 `adapter pattern` 라고들 부르고, 보통 method 생성 시 adapter 를 붙이고 of 로 생성자를 만든다.

우리가 실생활에서 흔히 아는 `adapter` 는 충전기가 있다. 콘센트를 통해서 전선주에서 220v의 전기를 사용하는 기구가 한번에 받을 수 있는
양의 전기를 변환하여 어느 콘센트에 꼽든 동일한 전기를 제공해준다.

여기서 컨셉은 `어느 콘센트에 꼽던` 과 `동일한 전기 제공` 이다.

각 집의 콘센트마다 별도의 컨버터가 필요하다면 충전기는 각 콘센트 개수에 맞게 필요할 것이다. 하지만 우리는 어떤 콘센트이던
충전기만 있다면 (220v 와 110v 등 규격은 일단 논외로 치고) 어떤 콘센트에서든 전기를 기기가 원하는 규격에 맞게 제공이 가능하다.

여기서 콘센트는 각자 다른 object 라고 볼 수 있고, 충전기는 그 각자 다른 object 를 각 adapter 에 return 해주는 값으로 변환해주는 것이라고 생각하면 된다.

```java
import lombok.Data;
import lombok.experimental.UtilityClass;

@Data
@Entity
public class AdapterObject {
  private String id;
  private String name;
  private String code;
}

@Data
@Entity
public class AdapterServerRequest {
  private String id;
  private String nameWithCode;
}

@UtilityClass
public class converter implements AdapterConverter {
  public AdapterObject convert(AdapterServerRequest adapterServerRequest) {
    String[] nameAndCode = adapterServerRequest.getNameWithCode().split("|");
    return new AdapterObject(adapterServerRequest.getId, nameAndCode[0], nameAndCode[1]);
  }
}

public interface AdapterConverter {
  public AdapterObject convert(AdapterServerRequest adapterServerRequest);
}
```

위와 같은 형태의 class 가 있다고 가정하자.

`AdapterObject` 는 서버 내에서 사용하는 클래스, `ServerRequest` 는 외부에서 유입되는 request 라고 가정하면 두 가지를 호환되게 사용하기 위해
converter 가 필요하다.

> nameWithCode 의 경우 delimiter 로 '|' 를 가진다고 가정하자.

converter 를 만들고 interface 로 감싸서 구현하여 제공하였는데, 여기에서 serverRequest 가 하나 더 생겼다고 가정을 하자.

```java
import lombok.Data;
import lombok.experimental.UtilityClass;

@Data
@Entity
public class AdapterServerRequest2 {
  private String idWithName;
  private String code;
}

@UtilityClass
public class converter implements AdapterConverter {
  public AdapterObject convert(AdapterServerRequest2 adapterServerRequest) {
    String[] idWithName = adapterServerRequest.getIdWithName().split("|");
    return new AdapterObject(idWithName[0], idWithName[1], adapterServerRequest.getCode());
  }
}

public interface AdapterConverter2 {
  public AdapterObject convert(AdapterServerRequest2 adapterServerRequest);
}
```

가만 보면 구성이 모두 똑같은데 인터페이스를 굳이 두개로 나누어 사용할 필요가 없다. 이럴 경우 adapter pattern 을 이용하면 중복도 피할 수 있다.

```java
@UtilityClass
public class converter implements AdapterConverter {
  
  private 
  
  public AdapterObject convert(AdapterServerRequest2 adapterServerRequest) {
    String[] idWithName = adapterServerRequest.getIdWithName().split("|");
    return new AdapterObject(idWithName[0], idWithName[1], adapterServerRequest.getCode());
  }
}

public interface AdapterConverter2 {
  public AdapterObject convert(AdapterServerRequest2 adapterServerRequest);
}
```