package com.exemplo.autenticacaoapispringsecurity;

import com.exemplo.autenticacaoapispringsecurity.entities.User;
import com.exemplo.autenticacaoapispringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername("pedro");

        if(user==null){
            user = new User();
            user.setName("Pedro");
            user.setUsername("pedro");
            user.setPassword("123456");
            user.getRoles().add("ADMIN");
            repository.save(user);
        }

        user = repository.findByUsername("ana");
        if(user==null){
            user = new User();
            user.setName("Ana");
            user.setUsername("ana");
            user.setPassword("123456");
            user.getRoles().add("USERS");
            repository.save(user);
        }
    }
}
