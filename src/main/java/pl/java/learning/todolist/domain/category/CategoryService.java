package pl.java.learning.todolist.domain.category;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findById(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
  }

  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  public void edit(Category category) {
    Category existCategory = categoryRepository.findById(category.getId())
        .orElseThrow(() -> new CategoryNotFoundException(category.getId()));

    Optional.ofNullable(category.getName()).ifPresent(existCategory::setName);

    save(category);
  }

  public void deleteById(Long id) {
    categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

    //It's not possible delete category about id equal 1 - Uncategorized
    categoryRepository.findById(id).
          filter(category -> id != 1).
          orElseThrow(() -> new CategoryNotFoundException(id)).
          getTasks().
          forEach(task -> task.setCategory(categoryRepository.findById(new Long(1)).get()));

    categoryRepository.deleteById(id);
  }

}