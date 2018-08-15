package pl.java.learning.todolist.domain.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    public User getById(Long id) {
        return userRepository.findById(id).get();

    }


    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }


    public void saveOrUpdateUser(User User) {
        userRepository.save(User);

    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
