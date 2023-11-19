package com.example.ToDoApp;

import com.example.ToDoApp.Repository.UserRepo;
import com.example.ToDoApp.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.OptionalAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        UserRepo user = new UserRepo();
        user.setEmail("vivektest@gmail.com");
        user.setPassword("1233");
        user.setFirstname("vivek2");
        user.setLastname("test2");

        UserRepo savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<UserRepo> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(UserRepo user : users){
            System.out.println(users);
        }
    }
    @Test
    public void testupdate(){
        Integer userId = 1;
        Optional<UserRepo> optionalUserRepo = repo.findById(userId);
        UserRepo userRepo = optionalUserRepo.get();
        userRepo.setPassword("1110");
        repo.save(userRepo);

        UserRepo updateUser = repo.findById(userId).get();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("1110");
    }

    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<UserRepo> optionalUserRepo = repo.findById(userId);
        Assertions.assertThat(optionalUserRepo).isPresent();
        System.out.println(optionalUserRepo.get());
    }

    @Test
    public void TestDelete(){
        Integer userID = 2;
        repo.deleteById(userID);

        Optional<UserRepo> optionalUserRepo = repo.findById(userID);
        Assertions.assertThat(optionalUserRepo).isNotPresent();
    }
}
