package pl.java.learning.todolist.infrastructure.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;
import pl.java.learning.todolist.domain.TestDbVO.Existing;
import pl.java.learning.todolist.domain.TestDbVO.NonExisting;
import pl.java.learning.todolist.infrastructure.AbstractIntegrationTest;


@Slf4j
public class TaskRestControllerTests extends AbstractIntegrationTest {

  private static final String PATH_TASKS = "/api/tasks";
  private static final String PATH_TASK_DETAILS = "/api/tasks/%s";

  @Test
  public void shouldRepondOnProperUrl() throws Exception {
    mockMvc.perform(get(PATH_TASKS))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  public void shouldReturnTaskDetails() throws Exception {
    mockMvc.perform(get(url(PATH_TASK_DETAILS, Existing.TASK_ID)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  public void shouldReturnNotFoundWhenTaskNotExist() throws Exception {
    mockMvc.perform(get(url(PATH_TASK_DETAILS, NonExisting.TASK_ID)))
        .andExpect(status().is4xxClientError());
  }
}
