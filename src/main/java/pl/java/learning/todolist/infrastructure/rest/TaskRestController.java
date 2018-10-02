package pl.java.learning.todolist.infrastructure.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
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
import pl.java.learning.todolist.infrastructure.security.IdProvider;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskRestController {

  private final TaskService taskService;
  private final IdProvider idProvider;

  @GetMapping
  public List<Task> findTasksByUserId() {
    return taskService.findTasksBelongToUser(idProvider.getCurrentUserId());
  }

  @GetMapping("{id}")
  public Task getTaskAboutId(@PathVariable Long id) {
    return taskService.findById(id);
  }

  @GetMapping("/status")
  public List<Task> getFinishedTasks(@RequestParam(value = "finished", defaultValue = "true") Boolean status) {
    return taskService.findByStatus(status, idProvider.getCurrentUserId());
  }

  @PostMapping
  public Task addTask(@RequestBody Task task) {
    return taskService.save(task, idProvider.getCurrentUserId());
  }

  @PatchMapping
  public void updateTask(@RequestBody Task task) {
    taskService.updateTask(task, idProvider.getCurrentUserId());
  }

  @DeleteMapping("{id}")
  public void deleteTask(@PathVariable Long id) {
      taskService.deleteById(id, idProvider.getCurrentUserId());
  }

  @PostMapping("/change")
  public void changePositionInLists(@RequestParam("taskId") Long taskId,
      @RequestParam("newPositionInList") Long newPositionInList,
      @RequestParam("newCategoryId") Long newCategoryId) {
    taskService.changeCategory(taskId, newCategoryId, newPositionInList);
  }
}