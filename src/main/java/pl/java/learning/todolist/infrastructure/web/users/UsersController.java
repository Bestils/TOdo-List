package pl.java.learning.todolist.infrastructure.web.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.domain.user.UserService;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class UsersController {

  final String PATH_USERS_REGISTER_AND_LOGIN = "/login";
  final String PATH_USERS_SUCCESS = "/success";
  final String PATH_USERS_LOGOUT = "/logout-success";

  private final UserService userService;

@Autowired
 private final PasswordEncoder passwordEncoder;


  @GetMapping(PATH_USERS_REGISTER_AND_LOGIN)
  public String userForm(Model model) {
    model.addAttribute("user", new User());
    return "login";
  }


  @GetMapping(PATH_USERS_SUCCESS)
  public String userLoginPage() {
    return "success";
  }

  @PostMapping(PATH_USERS_SUCCESS)
  public String successSubmit(@ModelAttribute User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveOrUpdateUser(user);
    return "success";
  }

  @GetMapping(PATH_USERS_LOGOUT)
  public String userLoggedOut() {
    return "logout-success";
  }

  @ResponseBody
  @GetMapping(path = "/users")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<User> getUsers() {
    return userService.findAll();
  }
}



