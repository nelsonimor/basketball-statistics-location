package fr.basketball.statistics.location.exposition.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.zalando.problem.Problem;

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
		assertResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_ERROR_OCCURRED,
				exceptionMessage);
	}
	
	  @Test
	  void testHandleNotFoundException() {
	    String exceptionMessage = "The given identifier is unknown by the system.";
	    EntityNotFoundException exception = new EntityNotFoundException(exceptionMessage);
	    ResponseEntity<Problem> response = translator.handleEntityNotFoundException(exception);
	    assertResponse(response, HttpStatus.NOT_FOUND, ErrorMessage.ENTITY_WAS_NOT_FOUND, exceptionMessage);
	  }

	private void assertResponse(ResponseEntity<Problem> response, HttpStatus status,
			ErrorMessage title,
			String detail) {
		assertThat(response).isNotNull()//
		.hasFieldOrPropertyWithValue("status", status)//
		.extracting("body").extracting("title", "detail")//
		.containsExactly(title.getMessage(), detail);
	}


}
