package pl.java.learning.todolist.domain.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
