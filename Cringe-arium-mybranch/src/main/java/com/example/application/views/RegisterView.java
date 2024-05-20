package com.example.application.views;

import com.example.application.data.service.AuthService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@AnonymousAllowed
@PageTitle("Регистрация")
@Route("register")
public class RegisterView extends VerticalLayout {

    private final InMemoryUserDetailsManager userDetailsManager;
    private final AuthService authService;

    @Autowired
    public RegisterView(AuthService authService, InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.authService = authService;
        this.userDetailsManager = inMemoryUserDetailsManager;

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        TextField firstName = new TextField("Имя");
        TextField lastName = new TextField("Фамилия");
        TextField email = new TextField("Электронка");
        PasswordField password1 = new PasswordField("Пароль");
        PasswordField password2 = new PasswordField("Подтвердите пароль");
        add(
                new H2("Регистрация"),
                firstName,
                lastName,
                email,
                password1,
                password2,
                new Button("Зарегистрироваться", event -> {register(
                        firstName.getValue(),
                        lastName.getValue(),
                        email.getValue(),
                        password1.getValue(),
                        password2.getValue()
                );

                }
                ));
    }

    private void register(String firstName, String lastName, String email, String password1, String password2) {
        if (firstName.trim().isEmpty()) {
            Notification.show("Введите имя");
        } else if (lastName.trim().isEmpty()) {
            Notification.show("Введите фамилию");
        } else if (email.trim().isEmpty()) {
            Notification.show("Введите электронную почту");
        }
        else if (password1.isEmpty()) {
            Notification.show("Введите пароль");
        } else if (!password1.equals(password2)) {
            Notification.show("Пароли не совпадают(");
        } else {
            UserDetails user = createUser(email, password1, "USER");
            userDetailsManager.createUser(user); // add the new user to the InMemoryUserDetailsManager
            System.out.println(userDetailsManager.userExists("admin"));
            System.out.println(userDetailsManager.userExists("zhivai@cirilla.gif"));
            Notification.show("Регистрация прошла успешно");
            UI.getCurrent().navigate("");
        }
    }

    private UserDetails createUser(String username, String password, String... roles) {
        return User.builder()
                .username(username)
                .password("{noop}" + password)
                .roles(roles)
                .build();
    }
}
