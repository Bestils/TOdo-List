package pl.java.learning.todolist.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.domain.user.UserRepository;
import pl.java.learning.todolist.infrastructure.security.MyUserPrincipal;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(username);
    if(user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new MyUserPrincipal(user);
  }
}
