package pl.java.learning.todolist.domain.user;

import static pl.java.learning.todolist.infrastructure.web.WebUtils.redirectTo;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.java.learning.todolist.domain.task.Task;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User getById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  public void saveOrUpdateUser(User User) {
    userRepository.save(User);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }



  public void updateStatus(Long userId, boolean status) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException(userId));
    user.setEnabled(status);
    userRepository.save(user);
  }

}
