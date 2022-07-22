package fr.bsm.location.application.continent.context;

import lombok.Getter;

@Getter
public class ContinentContextHolder<P, T> {

	private P param;

	private T result;

	public void addResult(T result) {
		this.result = result;
	}

	public void addParam(P param) {
		this.param = param;
	}
}
