package com.jk.study.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class JKObserver {

	public List<Integer> getValueByObservable () {
		List<Integer> integers = new ArrayList<>();

		Observer jkObserver = (o, arg) -> integers.add((Integer) arg);

		JKObservableWithRunnable observable = new JKObservableWithRunnable();
		observable.addObserver(jkObserver);
		observable.run();

		return integers;
	}
}
