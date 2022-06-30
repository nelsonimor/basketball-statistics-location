package fr.basketball.statistics.location.domain.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BusinessExceptionTest {

	private static final String FUNC_ERR_00 = "func.err.00";

	@Test
	void testDefault() {
		SimpleBusinessException exception = new SimpleBusinessException();
		assertThat(exception).hasFieldOrPropertyWithValue("code", FUNC_ERR_00).hasMessage(null).hasNoCause();
	}

	@Test
	void testMessage() {
		String message = "exceptionMessage";
		SimpleBusinessException exception = new SimpleBusinessException(message);
		assertThat(exception).hasFieldOrPropertyWithValue("code", FUNC_ERR_00).hasMessage(message).hasNoCause();
	}

	@Test
	void testCause() {
		Exception cause = new IllegalArgumentException();
		SimpleBusinessException exception = new SimpleBusinessException(cause);
		assertThat(exception).hasFieldOrPropertyWithValue("code", FUNC_ERR_00).hasCause(cause)
				.hasMessage(IllegalArgumentException.class.getName());
	}

	@Test
	void testMessageAndCause() {
		String message = "exceptionMessage";
		Exception cause = new IllegalArgumentException();
		SimpleBusinessException exception = new SimpleBusinessException(message, cause);
		assertThat(exception).hasFieldOrPropertyWithValue("code", FUNC_ERR_00).hasMessage(message).hasCause(cause);
	}

	@Test
	void testStackTrace() {
		String message = "exceptionMessage";
		Exception cause = new IllegalArgumentException();
		SimpleBusinessException exception = new SimpleBusinessException(message, cause, false, false);
		assertThat(exception).hasFieldOrPropertyWithValue("code", FUNC_ERR_00).hasMessage(message).hasCause(cause)
				.hasFieldOrPropertyWithValue("stackTrace", new StackTraceElement[0])
				.hasFieldOrPropertyWithValue("suppressedExceptions", null);
	}

	private static final class SimpleBusinessException extends BusinessException {
		private static final long serialVersionUID = 1L;

		public SimpleBusinessException() {
			super();
		}

		public SimpleBusinessException(final String message, final Throwable cause, final boolean enableSuppression,
				final boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public SimpleBusinessException(final String message, final Throwable cause) {
			super(message, cause);
		}

		public SimpleBusinessException(final String message) {
			super(message);
		}

		public SimpleBusinessException(final Throwable cause) {
			super(cause);
		}

		@Override
		public String getCode() {
			return FUNC_ERR_00;
		}

	}
}
