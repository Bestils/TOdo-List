package pl.java.learning.todolist.infrastructure.web.tasks;

import static pl.java.learning.todolist.infrastructure.web.WebUtils.redirectTo;
import static pl.java.learning.todolist.infrastructure.web.tasks.TaskViews.TASKS_ALL;
import static pl.java.learning.todolist.infrastructure.web.tasks.TaskViews.TASK_DETAILS;
import static pl.java.learning.todolist.infrastructure.web.tasks.TaskViews.TASK_EDIT_FORM;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.java.learning.todolist.domain.category.CategoryService;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.domain.task.TaskService;


@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;
  private final CategoryService categoryService;

  @GetMapping
  public String findAll(Model model) {
    model.addAttribute("categories", categoryService.findAll());
    model.addAttribute("task", new Task());
    return TASKS_ALL;
  }

  @GetMapping("{id}")
  public String getTaskDetails(@PathVariable Long id, Model model) {
    model.addAttribute("task", taskService.findById(id));
    return TASK_DETAILS;
  }

  @GetMapping("edit")
  public String addEditForm(Model model) {
    model.addAttribute("task", new Task());
    return TASK_EDIT_FORM;
  }

  @PostMapping
  public String processCreateForm(@ModelAttribute @Valid Task task, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return TASK_EDIT_FORM;
    }
    taskService.save(task);
    return redirectTo("/tasks");
  }

  @PostMapping("update")
  public String processUpdateForm(@ModelAttribute @Valid Task task, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return TASK_EDIT_FORM;
    }

    taskService.updateTask(task);

    return redirectTo(TASKS_ALL);
  }

  @PostMapping("delete")
  public String deleteTask(@RequestBody Long id) {
    taskService.deleteById(id);
    return redirectTo(TASKS_ALL);
  }
}
