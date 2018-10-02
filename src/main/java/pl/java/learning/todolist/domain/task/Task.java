package pl.java.learning.todolist.domain.task;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pl.java.learning.todolist.domain.category.Category;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.infrastructure.persistence.BaseEntity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"category", "user"})
public class Task extends BaseEntity {

  @NotNull
  @Size(min = 3)
  private String name;

  @NotNull
  @Size(min = 5)
  private String description;

  private Integer priority;

  private Boolean finished = false;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name ="user_id")
  private User user;

  public void updateCategory(Category category, int position) {
    if(getCategory() != null)
      this.category.getTasks().remove(this);
    this.category = category;
    this.category.getTasks().add(position, this);
  }

  public void updateUser(User user) {
    if(getUser() != null)
      this.user.getTask().remove(this);
    this.user = user;
    this.user.addTask(this);
  }
}
