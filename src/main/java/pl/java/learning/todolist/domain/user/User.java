package pl.java.learning.todolist.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.learning.todolist.domain.category.Category;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.infrastructure.persistence.BaseEntity;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"task", "category"})
public class User extends BaseEntity {
  private String login;
  private String password;
  private String email;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Task> task;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Category> category;
}
