package pl.java.learning.todolist.infrastructure.error;

import lombok.Getter;

@Getter
public class CommonTechnicalException extends RuntimeException {


  private final TechnicalErrorCode technicalErrorCode;

  public CommonTechnicalException(TechnicalErrorCode errorCode) {
    super(errorCode.getMessage());
    this.technicalErrorCode = errorCode;
  }
}
