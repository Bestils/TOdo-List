package pl.java.learning.todolist.domain.task;

import pl.java.learning.todolist.infrastructure.persistence.AbstractInMemoryRepository;

/**
 * Simple TaskRepository implementation for unit testing
 *
 */
public class InMemoryTaskRepository extends AbstractInMemoryRepository<Task> implements TaskRepository {
}
