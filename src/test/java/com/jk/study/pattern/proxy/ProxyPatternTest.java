package com.jk.study.pattern.proxy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProxyPatternTest {

	@Test
	void proxyTest() {
		JKString jkString = new JKString();
		JKStringProxy jkStringProxy = new JKStringProxy(jkString);

		Assertions.assertEquals(jkString.returnString(), jkStringProxy.returnString());
	}
}