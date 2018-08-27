package pl.java.learning.todolist.domain.task;

import java.util.List;
import java.util.stream.Collectors;
import pl.java.learning.todolist.infrastructure.persistence.AbstractInMemoryRepository;

/**
 * Simple TaskRepository implementation for unit testing
 *
 */
public class InMemoryTaskRepository extends AbstractInMemoryRepository<Task> implements TaskRepository {

  @Override
  public List<Task> findByFinished(Boolean status) {
    return findAll().stream().filter(task -> task.getFinished() == status).collect(Collectors.toList());
  }
}
