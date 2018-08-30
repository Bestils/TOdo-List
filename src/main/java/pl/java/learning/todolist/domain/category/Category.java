package pl.java.learning.todolist.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.java.learning.todolist.domain.task.Task;
import pl.java.learning.todolist.domain.user.User;
import pl.java.learning.todolist.infrastructure.persistence.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"tasks", "user"})
public class Category extends BaseEntity {
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
}