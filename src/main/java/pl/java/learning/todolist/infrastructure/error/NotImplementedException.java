package pl.java.learning.todolist.infrastructure.error;

public class NotImplementedException extends CommonTechnicalException {

  public NotImplementedException() {
    super(TechnicalErrorCode.NOT_IMPLEMENTED);
  }
}
