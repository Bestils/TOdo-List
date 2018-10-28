package pl.java.learning.todolist.domain.task;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import pl.java.learning.todolist.domain.task.InMemoryTaskRepository;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.domain.task.TaskRepository;
import pl.java.learning.todolist.domain.user.User;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryTaskRepositoryUnitTests {

  private TaskRepository taskRepository;

  @Before
  public void setUp() {
    taskRepository = new InMemoryTaskRepository();
  }

  @Test
  public void shouldReturnAllTasksBelongToUser() {

    //given
    User admin = new User("admin", "password", "admin@admin.com", true, null, null, null);
    User user = new User("user", "password", "user@admin.com", true, null, null, null);
    admin.setId(new Long(1));
    user.setId(new Long(2));

    Task firstTask = new Task("taskOne", "some description", 5, false, null, admin, 0);
    Task secondTask = new Task("taskTwo", "some description 2", 8, false, null, admin, 0);
    Task thirdTask = new Task("taskThird", "some description 3", 4, false, null, user, 0);

    taskRepository.save(firstTask);
    taskRepository.save(secondTask);
    taskRepository.save(thirdTask);

    //when
    List<Task> tasksBelongToUser = taskRepository.findTasksByUserId(1L);

    //then
    assertThat(tasksBelongToUser).containsOnly(firstTask, secondTask);
  }

  @Test
  public void shouldReturnSpecificTaskBelongToUser() {

    //given
    User userInTest = new User("admin", "password", "admin@admin.com", true, null, null, null);
    Task taskForUser = new Task("task", "some description", 5, false, null, userInTest, 0);
    userInTest.setId(new Long(1));
    taskRepository.save(taskForUser);

    //when
    Optional<Task> taskReturned = taskRepository.findByIdAndUserId(taskForUser.getId(), userInTest.getId());

    //then
    assertThat(taskReturned.get()).isEqualTo(taskForUser);
  }

  @Test
  public void shouldReturnTasksAboutSpecificStatusBelongToUser() {
    //given
    User user = new User("user", "password", "user@admin.com", true, null, null, null);
    User admin = new User("admin", "password", "admin@admin.com", true, null, null, null);
    user.setId(new Long(1));
    admin.setId(new Long(2));

    Task firstTask = new Task("taskOne", "some description", 5, true, null, admin, 0);
    Task secondTask = new Task("taskTwo", "some description 2", 8, true, null, user, 0);
    Task thirdTask = new Task("taskThird", "some description 3", 4, true, null, user, 0);

    taskRepository.save(firstTask);
    taskRepository.save(secondTask);
    taskRepository.save(thirdTask);

    //when
    List<Task> tasksAboutStatusTrue = taskRepository.findTasksByFinishedAndUserId(true, (long)1);

    //then
    assertThat(tasksAboutStatusTrue).containsOnly(secondTask, thirdTask);
  }

}
