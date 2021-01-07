## Proxy Pattern

`대리자` 라는 직역에 맞게 기존의 로직은 지키되 return 값은 그대로인 대리자를 내세워 실행하는 것이다.

이 패턴의 장점은 중간에 변형된, 추가되야 할 비즈니스 로직이 있거나 보완해야할 부분이 있을 경우 기존의 로직을 손대지 않되
추가 로직을 생성 할 수 있다.

또한 원래 로직을 감싸는 형식을 취할 수 있기 때문에 사용하던 부분에서도 부담없이 사용이 가능하다.

가장 중요한 포인트는 '결과는 바뀌지 않는다' 이다.

```java
public interface SomethingAccount {
	private String somethingID;
	private String somethingPW;
	
	public String getAccount();
}

public class JKAccount implements SomethingAccount {
	
	private String jkID;
	private String jkPW;
	
	public JKAccount (String id, String pw) {
		this.jkID = id;
		this.jkPW = pw;
    }
	
	@Override
	public String getAccount() {
      return "id : " + jkID + " pw : " + jkPW;
	}
}

public class JKProxyAccount implements SomethingAccount {
	
	// 기존 로직을 계승
	private JKAccount jkAccount;
	
	public JKProxyAccount(JKAccount jkAccount) {
		this.jkAccount = jkAccount;
    }
	
	@Override 
    public String getAccount() {
		// 로그를 기록하는 새로운 로직 추가, 이 부분이 다양하게 변형이 가능하다.
		System.out.println("Proxy Account called.");
		
		// 하지만 결과 값은 동일하게 제공한다.
		return jkAccount.getAccount();
    }
}
```

기존 계정 정보를 가져오는 `getAccount()` method 가 있을 때 새로운 로직으로 인해 호출 시 로그를 기록한다고 가정하자면,
위와 같은 구조를 가져갈 경우 기존 로직은 헤치지 않으며 동시에 로직을 추가할 수 있다.

만약 이와 같은 proxy pattern 을 적용하지 않는다면 모든 method 를 찾아가며 로그를 기록하는 부분을 추가해야 할 것이다.

이와 같은 형식으로 proxy 를 쓸 수 있고 동시에 proxy 내부에서 어떤 로직을 담느냐에 따라 virtual, protection 등 이름이 붙기도 하는데
virtual 의 경우 위와 같이 어떤 로직을 덧씌우는 경우 `가상` 으로 로직을 감싸서 그렇게 부르고 protection 의 경우
내부에 호출한 쪽의 권한을 체크하는 로직을 넣는다면 protection 이라는 이름을 붙일 수 있다.

