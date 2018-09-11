package pl.java.learning.todolist.infrastructure.category;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.java.learning.todolist.domain.category.Category;
import pl.java.learning.todolist.domain.category.CategoryRepository;
import pl.java.learning.todolist.domain.category.CategoryService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceUnitTests {

  @InjectMocks
  private CategoryService categoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @Test
  public void shouldReturnItemsFromServiceMethodFindAll() {

    //given
    Category home = new Category("Home", null, null);
    Category work = new Category("Work", null, null);
    given(categoryRepository.findAll()).willReturn(Arrays.asList(home, work));

    //when
    assertThat(categoryService.findAll())
        //then
        .containsOnly(home, work);
  }

}
