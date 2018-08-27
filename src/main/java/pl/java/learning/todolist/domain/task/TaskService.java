package pl.java.learning.todolist.domain.task;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

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

    save(that);
  }
}



