package com.jk.study.pattern.facade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FacadeAccountTest {

	@Test
	void facadeTest() {
		String id = "JungQui";
		String invalidPassword1 = "password";
		String invalidPassword2 = "123";
		String invalidPassword3 = "password123";

		String password = "password123!@#";

		FacadeCreateAccountAndLogin facadeCreateAccountAndLogin = new FacadeCreateAccountAndLogin();

		Assertions.assertFalse(facadeCreateAccountAndLogin.joinAndLogin(id, invalidPassword1));
		Assertions.assertFalse(facadeCreateAccountAndLogin.joinAndLogin(id, invalidPassword2));
		Assertions.assertFalse(facadeCreateAccountAndLogin.joinAndLogin(id, invalidPassword3));

		Assertions.assertTrue(facadeCreateAccountAndLogin.joinAndLogin(id, password));
	}

}