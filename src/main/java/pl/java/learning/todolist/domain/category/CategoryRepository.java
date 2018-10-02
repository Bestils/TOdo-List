package pl.java.learning.todolist.domain.category;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends pl.java.learning.todolist.infrastructure.persistence.Repository<Category> {
  List<Category> findCategoriesByUserId(Long userId);
}