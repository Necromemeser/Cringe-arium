package com.example.application.data.service;

import com.example.application.data.entity.Role;
//import com.example.application.data.entity.User;
import com.example.application.data.entity.Contact;

import com.example.application.data.repository.ContactRepository;
//import com.example.application.views.admin.AdminView;
//import com.example.application.views.home.HomeView;
//import com.example.application.views.logout.LogoutView;
//import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public class AuthException extends Exception {

    }

    private final ContactRepository contactRepository;

    public AuthService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

//    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    //@PostMapping("/register")
    public UserDetailsService register(String firstName, String lastName, String email, String password) {
        //Contact contact = contactRepository.save(new Contact(firstName, lastName, email, password, Role.USER));
        UserDetails newUser = User.builder()
                .username(email)
                .password(password)
                .roles("USER")
                .build();
        userDetailsManager.createUser(newUser);
        return new InMemoryUserDetailsManager(newUser);
    }

}
