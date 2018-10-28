package pl.java.learning.todolist.infrastructure.security;

import java.util.Arrays;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import pl.java.learning.todolist.domain.role.Role;
import pl.java.learning.todolist.domain.role.RoleRepository;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.domain.user.UserRepository;

@RequiredArgsConstructor
public class SecurityImplicitConnectionSignUp implements ConnectionSignUp {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public String execute(Connection<?> connection) {
    UserProfile profile = connection.fetchUserProfile();
    User newUser = new User(
        profile.getName().replaceAll("\\s+",""),
        passwordEncoder.encode("A" +  RandomStringUtils.randomAlphabetic(10) + "!"),
        profile.getEmail(),
        true,
        null,
        null,
        Collections.singleton(roleRepository.getById(2L))
    );
    userRepository.save(newUser);
    return newUser.getId().toString();
  }
}