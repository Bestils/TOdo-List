package pl.java.learning.todolist.domain.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.java.learning.todolist.domain.category.CategoryService;
import pl.java.learning.todolist.domain.user.UserService;


@Configuration
public class TaskConfiguration {

  @Bean
  public TaskService taskService(TaskRepository taskRepository, UserService userService, CategoryService categoryService) {
    return new TaskService(taskRepository, userService, categoryService);
  }

  public TaskService taskService(UserService userService, CategoryService categoryService) {
    return taskService(new InMemoryTaskRepository(), userService, categoryService);
  }
}
