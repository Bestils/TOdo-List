package pl.java.learning.todolist.infrastructure.web.users;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.domain.user.UserService;

@RequiredArgsConstructor
@Slf4j
@Controller
public class UsersController {

  final String PATH_USERS_REGISTER = "/users/register";

  private final UserService userService;


  @GetMapping(PATH_USERS_REGISTER)
  public String userForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping(PATH_USERS_REGISTER)
  public String userSubmit(@ModelAttribute User user) {
    userService.saveOrUpdateUser(user);
    return "register";
  }

  @ResponseBody
  @GetMapping(path = "/users")
  public List<User> getUsers() {
    return userService.findAll();
  }
}



