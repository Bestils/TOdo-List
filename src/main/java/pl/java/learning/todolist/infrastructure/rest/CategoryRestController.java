package pl.java.learning.todolist.infrastructure.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.java.learning.todolist.domain.category.Category;
import pl.java.learning.todolist.domain.category.CategoryService;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryRestController {

  private final CategoryService categoryService;

  @GetMapping
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Category getCategoryById(@PathVariable Long id) {
    return categoryService.findById(id);
  }

  @PostMapping
  public Category addCategory(@RequestBody Category category) {
    return categoryService.save(category);
  }

  @PatchMapping
  public void updateCategory(@RequestBody Category category) {
    categoryService.updateCategory(category);
  }

  @DeleteMapping("{id}")
  public void deleteCategory(@PathVariable Long id) {
      categoryService.deleteById(id);
  }
}
