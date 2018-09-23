package pl.java.learning.todolist.infrastructure.web.tasks;

import static pl.java.learning.todolist.infrastructure.web.WebUtils.redirectTo;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.java.learning.todolist.domain.category.Category;
import pl.java.learning.todolist.domain.category.CategoryService;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.domain.task.TaskService;
import pl.java.learning.todolist.domain.user.UserService;
import pl.java.learning.todolist.infrastructure.security.IdProvider;

@Slf4j
@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

  private final IdProvider idProvider;
  private final CategoryService categoryService;

  @PostMapping
  public String processCreateForm(@ModelAttribute @Valid Category category, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return redirectTo("/tasks");
    }
    categoryService.createForUser(category, idProvider.getCurrentUserId());
    return redirectTo("/tasks");
  }

}
