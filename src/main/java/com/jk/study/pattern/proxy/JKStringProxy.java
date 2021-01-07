package com.jk.study.pattern.proxy;

public class JKStringProxy implements JKStringInterface {
	JKString jkString;

	public JKStringProxy (JKString jkString) {
		this.jkString = jkString;
	}

	@Override public String returnString() {
		System.out.println("Proxy Pattern On");
		return jkString.returnString();
	}
}
