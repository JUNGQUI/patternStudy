package com.jk.study.pattern.startegy;

import org.springframework.stereotype.Component;

@Component
public class SecondBeanStrategy implements BeanStrategyInterface {

	@Override public String beanName() {
		return "Second Bean";
	}
}
