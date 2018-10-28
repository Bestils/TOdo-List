package pl.java.learning.todolist.infrastructure.security;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class SimpleSocialUserDetailsService implements SocialUserDetailsService {

  private final UserRepository userRepository;

  @Override
  public MySocialUserDetails loadUserByUserId(String userId)
      throws UsernameNotFoundException, DataAccessException {
    User user = userRepository.findById(Long.valueOf(userId)).get();
    return new MySocialUserDetails(user);
  }
}
