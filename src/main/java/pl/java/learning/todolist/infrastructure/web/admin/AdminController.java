package pl.java.learning.todolist.infrastructure.web.admin;

import static pl.java.learning.todolist.infrastructure.web.WebUtils.redirectTo;
import static pl.java.learning.todolist.infrastructure.web.admin.AdminViews.ADMIN;
import static pl.java.learning.todolist.infrastructure.web.admin.AdminViews.ADMIN_PANEL;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.domain.user.UserService;

@RequiredArgsConstructor
@Slf4j
@RequestMapping(ADMIN)
@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

  private final UserService userService;

  @GetMapping
  public String panelAdmin(Model model) {
    model.addAttribute("users", userService.findAll());
    return ADMIN_PANEL;
  }

  @PostMapping(value = "/users", params = "action=unlock")
  public String unlockUser(@RequestParam Long id) {
    userService.updateStatus(id, true);
    return redirectTo(ADMIN);
  }

  @PostMapping(value = "/users", params = "action=lock")
  public String lockUser(@RequestParam Long id) {
    userService.updateStatus(id, false);
    return redirectTo(ADMIN);
  }
}

