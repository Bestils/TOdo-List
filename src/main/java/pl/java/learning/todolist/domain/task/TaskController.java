package pl.java.learning.todolist.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskAboutId(@PathVariable long id) {
        Optional<Task> task = taskRepository.findById(id);

        return task.orElseThrow(() -> new TaskNotFoundException("Not found task about given ID"));
    }

    @PostMapping("/tasks/add")
    public Task addTask(@RequestBody Task task) {
        taskRepository.save(task);
        return task;
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable long id) {
        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent())
            taskRepository.deleteById(id);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> editTask(@RequestBody Task task, @PathVariable long id) {
        Optional<Task> taskInRepository = taskRepository.findById(id);

        if(!taskInRepository.isPresent())
            return ResponseEntity.notFound().build();

        task.setId(id);
        taskRepository.save(task);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
}
