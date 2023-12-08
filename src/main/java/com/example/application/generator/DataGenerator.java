package com.example.application.generator;

import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.data.repository.UserRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringComponent
public class DataGenerator {

//    @Bean
//    public CommandLineRunner loadData(UserRepository userRepository) {
////        return args -> {
////            userRepository.save(new User("user1", "u", Role.USER));
////        };
//    }
}
