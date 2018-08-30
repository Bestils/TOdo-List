package pl.java.learning.todolist.domain.task;

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
  private String name;
  private String description;
  private Integer priority;
  private Boolean finished = false;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name ="user_id")
  private User user;
}
