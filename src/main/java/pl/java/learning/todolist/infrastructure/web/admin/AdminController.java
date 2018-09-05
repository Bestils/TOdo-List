package pl.java.learning.todolist.infrastructure.web.admin;

import static pl.java.learning.todolist.infrastructure.web.admin.AdminViews.ADMIN_PANEL;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.java.learning.todolist.domain.user.UserService;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {

  private final UserService userService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public String panelAdmin(Model model) {
    model.addAttribute("users", userService.findAll());
    return ADMIN_PANEL;
  }

}

