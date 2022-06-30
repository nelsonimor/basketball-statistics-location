package fr.basketball.statistics.location.domain.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EntityNotFoundExceptionTest {

	private static final String FUNC_ERR_01 = "func.err.01";

	@Test
	void testDefault() {
		EntityNotFoundException exception = new EntityNotFoundException();
		assertThat(exception).hasFieldOrPropertyWithValue("code", FUNC_ERR_01).hasMessage(null).hasNoCause();
	}

	@Test
	void testMessage() {
		String message = "exceptionMessage";
		EntityNotFoundException exception = new EntityNotFoundException(message);
		assertThat(exception).hasFieldOrPropertyWithValue("code", FUNC_ERR_01).hasMessage(message).hasNoCause();
	}
}
