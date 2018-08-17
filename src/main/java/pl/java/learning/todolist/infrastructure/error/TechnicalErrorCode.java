package pl.java.learning.todolist.infrastructure.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TechnicalErrorCode {

  GENERAL_ERROR("General system error",HttpStatus.INTERNAL_SERVER_ERROR),
  NOT_IMPLEMENTED("This feature is not implemented", HttpStatus.METHOD_NOT_ALLOWED),
  ENTITY_NOT_FOUND("Entity with given id not found",HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus httpStatus;
}
