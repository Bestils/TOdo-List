package pl.java.learning.todolist.domain.category;

import java.util.List;
import pl.java.learning.todolist.infrastructure.persistence.AbstractInMemoryRepository;

public class InMemoryCategoryRepository
    extends AbstractInMemoryRepository<Category>
    implements CategoryRepository {

  @Override
  public List<Category> findCategoriesByUserId(Long id) {
    return null;
  }
}
