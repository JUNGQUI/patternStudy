package com.jk.study.pattern.observer;

import java.util.Observable;

public class JKObservableWithRunnable extends Observable implements Runnable {

	@Override public void run() {
		for (int i = 0; i < 10; i++) {
			setChanged();
			notifyObservers(i);
		}
	}
}
