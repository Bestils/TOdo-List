package pl.java.learning.todolist.domain.task;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.text.html.Option;
import pl.java.learning.todolist.infrastructure.persistence.AbstractInMemoryRepository;

/**
 * Simple TaskRepository implementation for unit testing
 */
public class InMemoryTaskRepository
    extends AbstractInMemoryRepository<Task>
    implements TaskRepository {

  @Override
  public Optional<Task> findByIdAndUserId(Long taskId, Long userId) {
    return findById(taskId)
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId));
  }

  @Override
  public List<Task> findTasksByUserId(Long userId) {
    return findAll().stream()
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId))
        .collect(Collectors.toList());
  }

  @Override
  public List<Task> findTasksByFinishedAndUserId(Boolean status, Long userId) {
    return findAll().stream()
        .filter(task -> task.getUser() != null)
        .filter(task -> task.getUser().getId().equals(userId))
        .filter(task -> task.getFinished().equals(status))
        .collect(Collectors.toList());
  }
}
