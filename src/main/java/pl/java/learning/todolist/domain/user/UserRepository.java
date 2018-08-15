package pl.java.learning.todolist.domain.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Gladus on 26.06.2018.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();
}
