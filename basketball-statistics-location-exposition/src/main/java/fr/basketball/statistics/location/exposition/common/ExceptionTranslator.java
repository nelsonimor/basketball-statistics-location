package fr.basketball.statistics.location.exposition.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {



  @ExceptionHandler(Exception.class)
  public ResponseEntity<Problem> handleOthers(Exception e) {
    return buildResponse(ErrorMessage.INTERNAL_ERROR_OCCURRED, e, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<Problem> buildResponse(ErrorMessage title, Exception exception,HttpStatus httpStatus) {
    return buildResponse(title, exception.getMessage(), exception, httpStatus);
  }

  private ResponseEntity<Problem> buildResponse(ErrorMessage title, String detail,Exception exception, HttpStatus httpStatus) {

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
