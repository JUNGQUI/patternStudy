package com.jk.study.pattern.startegy;

import lombok.Data;

@Data
public class StrategyObject {
	private StrategyInterface strategyInterface;

	public StrategyObject(StrategyInterface strategyInterface) {
		this.strategyInterface = strategyInterface;
	}
}
