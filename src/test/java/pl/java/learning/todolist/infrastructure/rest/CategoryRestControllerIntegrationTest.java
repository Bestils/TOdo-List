package pl.java.learning.todolist.infrastructure.rest;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.java.learning.todolist.domain.category.CategoryService;
import pl.java.learning.todolist.infrastructure.AbstractIntegrationTest;

public class CategoryRestControllerIntegrationTest extends AbstractIntegrationTest {

  private static final String PATH_CATEGORY = "/api/categories";
  private static final String PATH_CATEGORY_DETAILS = "/api/categories/%s";

  @MockBean
  CategoryService categoryServiceMock;

  @Test
  public void shouldInvokeSerivceFindAllOnRequestToCategoryRestController() throws Exception {
    mockMvc.perform(get(PATH_CATEGORY))
        .andDo(print())
        .andExpect(status().isOk());

    verify(categoryServiceMock).findAll();
  }
}
