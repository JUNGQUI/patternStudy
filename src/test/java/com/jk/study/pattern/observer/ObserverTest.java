package com.jk.study.pattern.observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObserverTest {

	List<Integer> integers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

	@Test
	void observerRunnableTest() {
		JKObserver jkObserver = new JKObserver();

		for (int i = 0; i < 10; i++) {
			Assertions.assertEquals(jkObserver.getValueByObservable().get(i), integers.get(i));
		}
	}

	@Test
	void observerTest() {
		List<Integer> observerIntegers = new ArrayList<>();
		JKObservable jkObservable = new JKObservable();

		// 로직 정의
		Observer integerObserver = (o, arg) ->
				observerIntegers.addAll(
						((JKObservable) o).getIntegers()
				);

		// event 등록
		jkObservable.addObserver(integerObserver);
		// 실행, setIntegers 안에 setChanged(), notifyObservers() 정의되어 있음
		jkObservable.setIntegers(10);

		for (int i = 0; i < 10; i++) {
			Assertions.assertEquals(observerIntegers.get(i), integers.get(i));
		}
	}
}
