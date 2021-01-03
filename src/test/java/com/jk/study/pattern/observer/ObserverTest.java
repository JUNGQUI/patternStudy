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
	@SuppressWarnings({"deprecation", "unchecked"})
	void observerTest() {
		List<Integer> observerIntegers1 = new ArrayList<>();
		List<Integer> observerIntegers2 = new ArrayList<>();

		JKObservable jkObservable = new JKObservable();

		// 로직 정의
		Observer integerObserver = (o, arg) -> {
			// Observable Object 내에 integerCollection 존재하기 때문에 Observable 에서 가져오거나
			observerIntegers1.addAll(
					((JKObservable) o).getIntegers()
			);

			// notify 에 collection 등록했기에 arg 를 통해 직접 가져올 수 있다.
			observerIntegers2.addAll((Collection<? extends Integer>) arg);
		};


		// event 등록
		jkObservable.addObserver(integerObserver);
		// 실행, setIntegers 안에 setChanged(), notifyObservers() 정의되어 있음
		jkObservable.setIntegers(10);

		for (int i = 0; i < 10; i++) {
			Assertions.assertEquals(observerIntegers1.get(i), integers.get(i));
			Assertions.assertEquals(observerIntegers2.get(i), integers.get(i));
		}
	}
}
