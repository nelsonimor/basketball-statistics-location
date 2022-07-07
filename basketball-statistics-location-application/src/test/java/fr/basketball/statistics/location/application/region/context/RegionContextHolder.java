package fr.basketball.statistics.location.application.region.context;

import lombok.Getter;

@Getter
public class RegionContextHolder<P, T> {

	private P param;

	private T result;

	public void addResult(T result) {
		this.result = result;
	}

	public void addParam(P param) {
		this.param = param;
	}
}
