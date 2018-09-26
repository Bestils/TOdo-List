package pl.java.learning.todolist.domain.task;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends pl.java.learning.todolist.infrastructure.persistence.Repository<Task> {
    Optional<Task> findByIdAndUserId(Long taskId, Long userId);
    List<Task> findTasksByUserId(Long userId);
    List<Task> findTasksByFinishedAndUserId(Boolean status, Long userId);
}
