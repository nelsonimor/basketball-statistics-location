package fr.basketball.statistics.location.exposition.common;

import static fr.basketball.statistics.location.exposition.common.ErrorMessage.ENTITY_WAS_NOT_FOUND;
import static fr.basketball.statistics.location.exposition.common.ErrorMessage.INTERNAL_ERROR_OCCURRED;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import fr.basketball.statistics.location.domain.common.exception.EntityNotFoundException;

class ExceptionTranslatorTest {

	private ExceptionTranslator translator;

	@BeforeEach
	void setUp() {
		translator = new ExceptionTranslator();
	}



	@Test
	void testHandleOthers() {
		String exceptionMessage = "This is en exception";
		Exception exception = new Exception(exceptionMessage);
		ResponseEntity<Problem> response = translator.handleOthers(exception);
		assertResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_OCCURRED,
				exceptionMessage);
	}

	@Test
	void testHandleNotFoundException() {
		String exceptionMessage = "The given identifier is unknown by the system.";
		EntityNotFoundException exception = new EntityNotFoundException(exceptionMessage);
		ResponseEntity<Problem> response = translator.handleEntityNotFoundException(exception);
		assertResponse(response, HttpStatus.NOT_FOUND, ENTITY_WAS_NOT_FOUND, exceptionMessage);
	}

	@Test
	void testHandleMethodArgumentTypeMismatch() {
		MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException(null, null, null, null,
				null);
		ResponseEntity<Problem> response = translator.handleMethodArgumentTypeMismatch(exception);
		assertResponse(response, HttpStatus.BAD_REQUEST, "Bad Request", Status.BAD_REQUEST,
				"Parameters are not in the correct format");
	}


	private void assertResponse(ResponseEntity<Problem> response, HttpStatus status,
			ErrorMessage title,
			String detail) {
		assertThat(response).isNotNull()//
		.hasFieldOrPropertyWithValue("status", status)//
		.extracting("body").extracting("title", "detail")//
		.containsExactly(title.getMessage(), detail);
	}

	private void assertResponse(ResponseEntity<Problem> response, HttpStatus status, String code, Status message,
			String description) {
		assertThat(response).isNotNull()//
		.hasFieldOrPropertyWithValue("status", status)//
		.extracting("body").extracting("title", "status", "detail")//
		.containsExactly(code, message, description);
	}


}
