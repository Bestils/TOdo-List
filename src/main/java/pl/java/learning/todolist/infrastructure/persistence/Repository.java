package pl.java.learning.todolist.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Repository<E extends BaseEntity> extends org.springframework.data.repository.Repository<E,Long> {
  List<E> findAll();

  Page<E> findAll(Pageable pageable);

  Optional<E> findById(Long id);

  E getById(Long id);

  E save(E entity);

  void deleteById(Long id);

  boolean existsById(Long id);

  Long count();
}
