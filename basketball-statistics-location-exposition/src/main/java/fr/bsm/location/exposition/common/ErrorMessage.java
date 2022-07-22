package fr.bsm.location.exposition.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

  BAD_REQUEST("Bad Request"),
  CONSTRAINT_VIOLATION("Constraint violation"),
  MALFORMED_JSON_REQUEST("Malformed JSON request"),
  METHOD_ARGUMENT_TYPE_MISMATCH("Method argument type mismatch"),
  UNKNOWN_REQUEST_PARAMETER("Unknown request parameter"),
  MISSING_CHANNEL_TOKEN("Missing channel token"),
  ENTITY_WAS_NOT_FOUND("Entity was not found"),
  AUTHENTICATION_HAS_FAILED("Authentication has failed"),
  INTERNAL_ERROR_OCCURRED("Internal error occurred");

  private final String message;
}