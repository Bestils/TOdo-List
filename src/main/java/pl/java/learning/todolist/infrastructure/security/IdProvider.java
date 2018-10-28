package pl.java.learning.todolist.infrastructure.security;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;
import pl.java.learning.todolist.domain.user.UserRepository;


@Slf4j
@Service
public class IdProvider {

  public Long getCurrentUserId() {

    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();

    if (userDetails instanceof MySocialUserDetails) {
      MySocialUserDetails principal = (MySocialUserDetails) Optional
          .ofNullable(userDetails)
          .orElseThrow(UserNotAuthenticatedException::new);
      return principal.getSocialUserId();
    }
    else {
      MyUserPrincipal principal = (MyUserPrincipal) Optional
          .ofNullable(userDetails)
          .orElseThrow(UserNotAuthenticatedException::new);
      return principal.getUserId();
    }
  }
}
