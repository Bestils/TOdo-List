package pl.java.learning.todolist.infrastructure.persistence;

import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Data;


@Data
@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue
  private Long id;

  @Version
  private Long version;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o.getClass().isInstance(this.getClass())) {
      BaseEntity entity = (BaseEntity) o;
      return getId() != null && Objects.equals(getId(), entity.getId());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
