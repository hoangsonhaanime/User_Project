package com.example.practise_crud.service;

import com.example.practise_crud.model.User;
import com.example.practise_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUser(Integer id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }

        throw new NotFoundException("Cannot found this User with id : " + id);
    }
    public void delete(Integer id) {
        boolean exist = userRepository.existsById(id);
        if(exist) {
            userRepository.deleteById(id);
        }
    }
}
