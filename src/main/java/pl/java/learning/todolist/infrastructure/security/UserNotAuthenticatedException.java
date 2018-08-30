package pl.java.learning.todolist.infrastructure.security;

import lombok.extern.slf4j.Slf4j;
import pl.java.learning.todolist.infrastructure.error.CommonTechnicalException;
import pl.java.learning.todolist.infrastructure.error.TechnicalErrorCode;


@Slf4j
public class UserNotAuthenticatedException extends CommonTechnicalException {

  UserNotAuthenticatedException() {
    super(TechnicalErrorCode.NOT_AUTHENTICATED);
  }
}
