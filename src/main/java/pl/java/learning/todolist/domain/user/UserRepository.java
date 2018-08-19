package pl.java.learning.todolist.domain.user;

import org.springframework.stereotype.Repository;

/**
 * Created by Gladus on 26.06.2018.
 */
@Repository
public interface UserRepository extends
    pl.java.learning.todolist.infrastructure.persistence.Repository<User> {

}