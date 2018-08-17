package pl.java.learning.todolist.domain.task;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends pl.java.learning.todolist.infrastructure.persistence.Repository<Task> {
}
