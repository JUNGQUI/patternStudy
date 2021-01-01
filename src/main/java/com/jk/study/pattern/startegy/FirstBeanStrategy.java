package com.jk.study.pattern.startegy;

import org.springframework.stereotype.Component;

@Component
public class FirstBeanStrategy implements BeanStrategyInterface {

	@Override public String beanName() {
		return "First Bean";
	}
}
