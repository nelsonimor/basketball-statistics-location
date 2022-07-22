package fr.bsm.location.exposition.common;

import static fr.bsm.location.exposition.common.ErrorMessage.ENTITY_WAS_NOT_FOUND;
import static fr.bsm.location.exposition.common.ErrorMessage.INTERNAL_ERROR_OCCURRED;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import fr.bsm.location.domain.common.exception.EntityNotFoundException;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {



	@ExceptionHandler(Exception.class)
	public ResponseEntity<Problem> handleOthers(Exception e) {
		return buildResponse(INTERNAL_ERROR_OCCURRED, e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Problem> handleEntityNotFoundException(EntityNotFoundException e) {
		return buildResponse(ENTITY_WAS_NOT_FOUND, e, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Problem> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) { //
		return new ResponseEntity<>(
				Problem.builder().withTitle(Status.BAD_REQUEST.getReasonPhrase())
				.withDetail("Parameters are not in the correct format").withStatus(Status.BAD_REQUEST).build(),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Problem> buildResponse(ErrorMessage title, Exception exception,HttpStatus httpStatus) {
		return buildResponse(title, exception.getMessage(), httpStatus);
	}

	private ResponseEntity<Problem> buildResponse(ErrorMessage title, String detail,HttpStatus httpStatus) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
		return new ResponseEntity<>(Problem.builder()
				.withTitle(title.getMessage())
				.withDetail(detail)
				.withStatus(Status.valueOf(httpStatus.value()))
				.build(),
				httpHeaders,
				httpStatus);
	}
}
