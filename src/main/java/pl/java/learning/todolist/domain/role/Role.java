package pl.java.learning.todolist.domain.role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.infrastructure.persistence.BaseEntity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "users")
public class Role extends BaseEntity implements GrantedAuthority {

  @NotNull
  private String name;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;

  @Override
  public String getAuthority() {
    return name;
  }
}
