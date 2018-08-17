package pl.java.learning.todolist.domain.task;

import pl.java.learning.todolist.infrastructure.error.BusinessErrorCode;
import pl.java.learning.todolist.infrastructure.error.CommonBusinessException;

public class TaskNotFoundException extends CommonBusinessException {

  private static final String MESSAGE = "Task with id: %s not found";

  public TaskNotFoundException(Long id) {
    super(BusinessErrorCode.ENTITY_NOT_FOUND, String.format(MESSAGE, id));
  }
}
