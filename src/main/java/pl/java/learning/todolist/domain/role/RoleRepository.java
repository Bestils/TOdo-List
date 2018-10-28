package pl.java.learning.todolist.domain.role;

import org.springframework.stereotype.Repository;
import pl.java.learning.todolist.domain.user.User;

@Repository
public interface RoleRepository extends
    pl.java.learning.todolist.infrastructure.persistence.Repository<Role>{
}
