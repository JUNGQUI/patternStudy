package com.jk.study.pattern.startegy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StrategyTest {

	@Autowired @Qualifier("firstBeanStrategy")
	BeanStrategyInterface firstBeanStrategyInterface;

	@Autowired @Qualifier("secondBeanStrategy")
	BeanStrategyInterface secondBeanStrategyInterface;

	@Test
	void StrategyClassTest() {
		StrategyObject first = new StrategyObject(new FirstStrategy());
		StrategyObject second = new StrategyObject(new SecondStrategy());

		Assertions.assertEquals(first.getStrategyInterface().methodOne(), "first/one");
		Assertions.assertEquals(first.getStrategyInterface().methodTwo(), "first/two");
		Assertions.assertEquals(first.getStrategyInterface().methodThree(), "first/three");

		Assertions.assertEquals(second.getStrategyInterface().methodOne(), "second/one");
		Assertions.assertEquals(second.getStrategyInterface().methodTwo(), "second/two");
		Assertions.assertEquals(second.getStrategyInterface().methodThree(), "second/three");
	}

	@Test
	void StrategyBeanTest() {
		Assertions.assertEquals(firstBeanStrategyInterface.beanName(), "First Bean");
		Assertions.assertEquals(secondBeanStrategyInterface.beanName(), "Second Bean");
	}
}