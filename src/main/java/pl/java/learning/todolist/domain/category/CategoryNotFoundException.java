package pl.java.learning.todolist.domain.category;

import pl.java.learning.todolist.infrastructure.error.BusinessErrorCode;
import pl.java.learning.todolist.infrastructure.error.CommonBusinessException;

public class CategoryNotFoundException extends CommonBusinessException {
  private static final String MESSAGE = "Category with id: %s not found";

  public CategoryNotFoundException(Long id) {
    super(BusinessErrorCode.ENTITY_NOT_FOUND, String.format(MESSAGE, id));
  }
}
