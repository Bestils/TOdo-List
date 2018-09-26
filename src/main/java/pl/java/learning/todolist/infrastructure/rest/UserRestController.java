package pl.java.learning.todolist.infrastructure.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.domain.user.UserService;
import pl.java.learning.todolist.infrastructure.security.IdProvider;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;
  private final IdProvider idProvider;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<User> findAll() {
    return userService.findAll();
  }

  @PostMapping
  public User addUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PatchMapping
  public void updateUser(@RequestBody User user) {
    if(idProvider.getCurrentUserId() == user.getId()) {
      userService.updateUser(user);
    }
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public User getUserById(@PathVariable Long id) {
    return userService.getById(id);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void deleteUserById(@PathVariable Long id) {
    userService.deleteById(id);
  }
}