package pl.java.learning.todolist.infrastructure.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import pl.java.learning.todolist.domain.TestDbVO.Existing;
import pl.java.learning.todolist.domain.TestDbVO.NonExisting;
import pl.java.learning.todolist.infrastructure.AbstractIntegrationTest;

public class CategoryRestControllerE2ETest extends AbstractIntegrationTest {

  private static final String PATH_CATEGORY = "/api/categories";
  private static final String PATH_CATEGORY_DETAILS = "/api/categories/%s";

  @Test
  public void shouldRepondOnProperUrl() throws Exception {
    mockMvc.perform(get(PATH_CATEGORY)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  public void shouldReturnExistingCategory() throws Exception {
    mockMvc.perform(get(url(PATH_CATEGORY_DETAILS, Existing.CATEGORY_ID)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.name").value(Existing.CATEGORY_NAME));
  }

  @Test
  public void shouldReturnNotFoundNonExistingCategory() throws Exception {
    mockMvc.perform(get(url(PATH_CATEGORY_DETAILS, NonExisting.CATEGORY_ID)))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  }

  @Test
  public void shouldReturnNotFoundOnDeletedCategory() throws Exception {
    mockMvc.perform(delete(url(PATH_CATEGORY_DETAILS, Existing.CATEGORY_ID)))
        .andDo(print())
        .andExpect(status().isOk());

    mockMvc.perform(get(url(PATH_CATEGORY_DETAILS, Existing.CATEGORY_ID)))
        .andDo(print())
        .andExpect(status().isNotFound());
  }
}