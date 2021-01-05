## Builder Pattern

Builder pattern 의 기본적으로 setter 를 이용한 무분별한 데이터의 변환을 방지하기 위함이다.

```java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  private String id;
  private String password;
}


@Data @Builder(builderMethodName = "accountBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  private String id;
  private String password;
}

```

위 두가지 케이스에서 변경을 하고자 할 때 이와 같은 방식을 취한다면
```java
public class ChangeState() {
  public Account changeFirst(Account account) {
    account.setPassword("password1");
    
    return account;
  }

  public Account changeSecond(Account account) {
    account.setPassword("password2");

    return account;
  }

  public Account changeThird(Account account) {
    account.setPassword("password3");

    return account;
  }
}
```

이렇게 하여 해당 method 들이 여러번 재사용된다면 어느 시점에서 어떻게 변경이 되었을지 구분하기가 어렵다.

그렇다고 setter 를 지워서 변경 로직을 안에 숨기되 생성자를 통해 생성한다 한다면

```java
public class CreateAccount {
  public Account createAccount() {
    return new Account("이건뭘까", "이건뭐게");
  }
}
```

이와 같이 생성자를 통해 생성 시 로직을 아는 개발자라면 생성이 쉽게 이해가 되지만 로직을 모르는 개발자라면 파악하기 어렵다.

또한 필수 값이 아닌 경우가 생겼다고 가정한다면

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  private String id;
  private String password;

  private String comment;
}

public class CreateAccount {

  public Account createAccount() {
    return new Account("이건뭘까", "이건뭐게", null);
  }
}
```

필수값이 아니여도 항상 생성 시 null 로 지정이 필요하다.

```java
public class CreateAccount {

  public Account createAccount() {
    return Account.builder("id", "password")
        // .comment()
        .comment()
        .build();
  }
}
```

생성자처럼 필수 값을 method 에 지정하여 error handling 이 가능하며, 비 필수값의 경우 builder pattern 에 따라 포함시켜도,
포함시키지 않아도 적용이 가능하다.

이러한 장점으로 인해 setter, 생성자를 이용한 방식보다 builder pattern 이 활용된다.