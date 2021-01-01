package com.jk.study.pattern.startegy;

public class SecondStrategy implements StrategyInterface {

	@Override public String methodOne() {
		System.out.println("Second Strategy, Method One.");
		return "second/one";
	}

	@Override public String methodTwo() {
		System.out.println("Second Strategy, Method Two.");
		return "second/two";
	}

	@Override public String methodThree() {
		System.out.println("Second Strategy, Method Three.");
		return "second/three";
	}
}
