package pl.java.learning.todolist.domain.task;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends pl.java.learning.todolist.infrastructure.persistence.Repository<Task> {
    List<Task> findByFinished(Boolean status);
}
