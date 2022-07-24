package fr.bsm.application.util;

import lombok.Getter;

@Getter
public class ContextHolder<P, T> {

	private P param;

	private T result;

	public void addResult(T result) {
		this.result = result;
	}

	public void addParam(P param) {
		this.param = param;
	}
}
