package pl.java.learning.todolist.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TaskController {

    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskAboutId(@PathVariable long id) {
        Optional<Task> task = taskRepository.findById(id);

        return task.orElseThrow(() -> new TaskNotFoundException("Not found task about given ID"));
    }

    @PostMapping("/tasks/add")
    Task addTask(@RequestBody Task task) {
        taskRepository.save(task);
        return task;
    }
}
