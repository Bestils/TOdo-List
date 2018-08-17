package pl.java.learning.todolist.domain.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TaskConfiguration {

  @Bean
  public TaskService taskService(TaskRepository taskRepository) {
    return new TaskService(taskRepository);
  }

  public TaskService taskService() {
    return taskService(new InMemoryTaskRepository());
  }
}
