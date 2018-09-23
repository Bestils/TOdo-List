package pl.java.learning.todolist.domain.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.java.learning.todolist.domain.user.UserService;


@Configuration
public class TaskConfiguration {

  @Bean
  public TaskService taskService(TaskRepository taskRepository, UserService userService) {
    return new TaskService(taskRepository, userService);
  }

  public TaskService taskService(UserService userService) {
    return taskService(new InMemoryTaskRepository(), userService);
  }
}
