package fr.basketball.statistics.location.domain.common.exception;

/**
 * Super class of all Business exceptions.
 */
public abstract class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected BusinessException() {
		super();
	}

	protected BusinessException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	protected BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	protected BusinessException(final String message) {
		super(message);
	}

	protected BusinessException(final Throwable cause) {
		super(cause);
	}

	public abstract String getCode();

}
