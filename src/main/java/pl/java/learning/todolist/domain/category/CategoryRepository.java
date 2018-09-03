package pl.java.learning.todolist.domain.category;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends pl.java.learning.todolist.infrastructure.persistence.Repository<Category> {
  public List<Category> findCategoriesByUserId(Long id);
}