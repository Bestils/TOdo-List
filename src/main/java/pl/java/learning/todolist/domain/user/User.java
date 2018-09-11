package pl.java.learning.todolist.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.java.learning.todolist.domain.category.Category;
import pl.java.learning.todolist.domain.role.Role;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.infrastructure.persistence.BaseEntity;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"task", "category", "roles"})
public class User extends BaseEntity {
  private String login;
  private String password;
  private String email;
  private Boolean enabled;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Task> task;

  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Category> category;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "Users_Roles",
      joinColumns = { @JoinColumn(name = "user_id") },
      inverseJoinColumns = { @JoinColumn(name = "role_id") }
  )
  private Set<Role> roles;

  @NotNull(message = "Login  is required")
  @NotBlank(message = "Login is required")
  @Pattern(regexp = "[A-Za-z\\d]{4,255}$", message = "Login has invalid characters")
  public String getLogin() {
    return login;
  }

  @NotNull(message = "Password is required")
  @NotBlank(message = "Password is required")
  @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{6,}$" , message = "Password  has invalid characters")
  public String getPassword() {
    return password;
  }

  @NotNull(message="Email Address is required")
  @NotBlank(message="Email Address is required")
  @Email(message = "Email address has invalid format")
  public String getEmail() {
    return email;
  }

  public void setLogin(String login) {
    this.login = login;
  }
}
