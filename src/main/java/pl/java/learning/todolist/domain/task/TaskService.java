package pl.java.learning.todolist.domain.task;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pl.java.learning.todolist.domain.user.UserService;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final UserService userService;

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  public Task findById(Long id) {
    return taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException(id));
  }

  public List<Task> findByStatus(Boolean status) {
    return taskRepository.findByFinished(status);
  }

  public Task save(Task task) {
    return taskRepository.save(task);
  }

  public void deleteById(Long id) {
    taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException(id));

    taskRepository.deleteById(id);
  }

  public void updateTask(Task task) {
    Task that = taskRepository.findById(task.getId())
        .orElseThrow(() -> new TaskNotFoundException(task.getId()));

    Optional.ofNullable(task.getName())
        .ifPresent(that::setName);

    Optional.ofNullable(task.getDescription())
        .ifPresent(that::setDescription);

    Optional.ofNullable(task.getPriority())
        .ifPresent(that::setPriority);

    Optional.ofNullable(task.getFinished())
        .ifPresent(that::setFinished);

    save(that);
  }

  public Task createForUser(@Valid Task task, Long currentUserId) {
    task.setUser(userService.getById(currentUserId));
    return taskRepository.save(task);
  }
}



