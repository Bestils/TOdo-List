package pl.java.learning.todolist.domain.user;

import lombok.extern.slf4j.Slf4j;
import pl.java.learning.todolist.infrastructure.error.BusinessErrorCode;
import pl.java.learning.todolist.infrastructure.error.CommonBusinessException;


@Slf4j
public class UserNotFoundException extends CommonBusinessException {

  private static final String MESSAGE = "User with id: %s not found";

  public UserNotFoundException(Long id) {
    super(BusinessErrorCode.ENTITY_NOT_FOUND, String.format(MESSAGE, id));
  }
}
