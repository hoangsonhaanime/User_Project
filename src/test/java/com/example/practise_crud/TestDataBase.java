package com.example.practise_crud;

import com.example.practise_crud.model.User;
import com.example.practise_crud.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestDataBase {
    @Autowired private UserRepository userRepository;
    @Test
    public void testAddNewUser() {
        User user = new User();
        user.setEmail("Nguyenkimngan@gmail.com");
        user.setPassword("12345678910");
        user.setFirstName("Nguyen");
        user.setLastName("Ngan");
        User newUser = userRepository.save(user);

        Assertions.assertThat(user).isNotNull();
    }
    @Test
    public void testGetUser() {
        Integer id = 1;
        Optional<User> user = userRepository.findById(id);

        Assertions.assertThat(user).isPresent();

        System.out.println(user.get());
    }
    @Test
    public void testGetAllUser() {
        List<User> userList = (List<User>) userRepository.findAll();

        Assertions.assertThat(userList).hasSizeGreaterThan(0);

        for(User user : userList) {
            System.out.println(user.toString());
        }
    }
    @Test
    public void testUpdateUser() {
        Integer id = 1;

        User user = userRepository.findById(id).get();

        user.setPassword("21022002");

        User updateUser = userRepository.save(user);

        Assertions.assertThat(updateUser.getPassword()).isEqualTo("21022002");
    }
    @Test
    public void testDeleteUser() {
        Integer id = 12;

        userRepository.deleteById(id);

        Optional<User> deleteUser = userRepository.findById(id);

        Assertions.assertThat(deleteUser).isNotPresent();

    }
}
