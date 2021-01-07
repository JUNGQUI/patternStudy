package com.jk.study.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import lombok.Getter;

@Getter
@SuppressWarnings("deprecation")
public class JKObservable extends Observable {
	private List<Integer> integers;

	public JKObservable () {
		this.integers = new ArrayList<>();
	}

	public void setIntegers(int i) {
		for (int a = 0; a < i; a++) {
			this.integers.add(a);
		}

		setChanged();
		notifyObservers(this.integers);
	}
}
