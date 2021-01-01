package com.jk.study.pattern.startegy;

public class FirstStrategy implements StrategyInterface {

	@Override public String methodOne() {
		System.out.println("First Strategy, Method One.");
		return "first/one";
	}

	@Override public String methodTwo() {
		System.out.println("First Strategy, Method Two.");
		return "first/two";
	}

	@Override public String methodThree() {
		System.out.println("First Strategy, Method Three.");
		return "first/three";
	}
}
