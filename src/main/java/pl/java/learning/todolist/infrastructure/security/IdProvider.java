package pl.java.learning.todolist.infrastructure.security;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class IdProvider {

  public Long getCurrentUserId() {
    MyUserPrincipal principal = (MyUserPrincipal) Optional
        .ofNullable(SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal())
        .orElseThrow(UserNotAuthenticatedException::new);

    return principal.getUserId();
  }
}
