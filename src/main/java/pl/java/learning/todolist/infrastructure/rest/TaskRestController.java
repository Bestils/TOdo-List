package pl.java.learning.todolist.infrastructure.rest;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.domain.task.TaskService;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskRestController {

  private final TaskService taskService;

  @GetMapping
  public List<Task> findAll(@RequestParam(value = "finished", required = false) Boolean status) {
    return Optional.ofNullable(status)
        .map(taskService::findByStatus)
        .orElse(taskService.findAll());
  }

  @GetMapping("{id}")
  public Task getTaskAboutId(@PathVariable Long id) {
    return taskService.findById(id);
  }

  @GetMapping("/status")
  public List<Task> getFinishedTasks(@RequestParam(value = "finished", defaultValue = "true") Boolean status) {
    return taskService.findByStatus(status);
  }

  @PostMapping
  public Task addTask(@RequestBody Task task) {
    return taskService.save(task);
  }

  @PatchMapping
  public void updateTask(@RequestBody Task task) {
    taskService.updateTask(task);
  }

  @DeleteMapping("{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteById(id);
  }
}
