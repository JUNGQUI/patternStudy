## facade pattern

파사드 패턴에서 파사드는 건물의 정면이라는 뜻을 가지고 있다.

> 건축학적으로 봤을 때 facade란
> 
> `정면에서 해당 건물이 어떠한 목적을 가졌는지 단적으로 보여주는 형식` 이라는 의미가 있다. 
> 
> 오사카의 파카디가 대표적인데, 도톤보리의 큰 조형물을 이용한 간판의 경우가 이러한 양식을 잘 살린 경우라 볼 수 있다~~고 한다~~.

이러한 파카디의 형식을 맞춰서 구현하는 패턴을 파카디 패턴이라고 하는데
`정면에서 모든걸 표현하고 내부에 복잡한 것을 감추는 것` 이 핵심이라 볼 수 있다.

복잡한 로직을 가진 회원 가입 을 구현한다고 가정해 보자.

```java
public class Account {
	private String id;
	private String password;
	
	public Account(String id, String password) {
		this.id = id;
		this.password = password;
    }
}
```

제약 조건으로 비밀번호는 10자 이상, 알파벳,숫자,특수문자 포함 되어 있어야 한다고 가장하자.

```java
public class FacadeAccountCheck {
	public boolean specialRegex(String password) {
		Pattern patternSpecial = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
		return patternSpecial.matcher(password).find();
	}

	public boolean numberRegex(String password) {
		Pattern patternNumber = Pattern.compile("[0-9]");
		return patternNumber.matcher(password).find();
	}

	public boolean alphabetRegex(String password) {
		Pattern patternAlphabet = Pattern.compile("[a-zA-Z]");
		return patternAlphabet.matcher(password).find();
	}

	public boolean passwordLengthCheck(String password) {
		return password.length() >= 10;
	}
}
```

이 조건들을 회원을 만드는 method 마다 모두 validation 을 한다고 한다면, 계정과 관련된 작업을 진행 할 때 마다
이 모든 method 를 사용해야 한다면 사용 할 때마다 해당 class 만큼의 method 를 다 사용해야 하며,
실수로 몇 가지를 빼먹는 human error 가 발생 할 수 있다.

그래서 이러한 부분을 하나로 묶어 전면 (facade) 에 내세우고 해당 하나의 class 를 사용하게 하는게 facade pattern
이다.

```java
public class FacadeCreateAccountAndLogin {
	public boolean joinAndLogin (String id, String password) {
		FacadeLogin facadeLogin = new FacadeLogin();
		FacadeAccountCheck facadeAccountCheck = new FacadeAccountCheck();

		boolean check = facadeAccountCheck.alphabetRegex(password)
				&& facadeAccountCheck.numberRegex(password)
				&& facadeAccountCheck.specialRegex(password)
				&& facadeAccountCheck.passwordLengthCheck(password);

		if (!check) {
			System.out.println("password invalid");
			return false;
		}

		FacadeAccount facadeAccount = new FacadeAccount(id, password);

		return facadeLogin.login(id, password, facadeAccount);
	}
}
```

이와 같이 validation 을 하나로 해결하는 부분과, 생성하는 부분까지 같이 묶어서 사용한다면
추후 이와 같은 class 만 그대로 사용이 가능하여 간편해지고 human error 도 줄일 수 있다.