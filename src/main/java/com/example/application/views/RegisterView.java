package com.example.application.views;

import com.example.application.data.service.AuthService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

@AnonymousAllowed
@Route("register")
public class RegisterView extends VerticalLayout {

    private final AuthService authService;

    public RegisterView(AuthService authService) {
        this.authService = authService;

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        TextField firstName = new TextField("Имя");
        TextField lastName = new TextField("Фамилия");
        TextField email = new TextField("Электронка");
        PasswordField password1 = new PasswordField("Пароль");
        PasswordField password2 = new PasswordField("Подтвердите пароль");
        add(
                new H2("Register"),
                firstName,
                lastName,
                email,
                password1,
                password2,
                new Button("Send", event -> register(
                        firstName.getValue(),
                        lastName.getValue(),
                        email.getValue(),
                        password1.getValue(),
                        password2.getValue()
                ))
        );
    }

//
//    @Override
//    protected Component initContent() {
////        TextField username = new TextField("Username");
//        setAlignItems(Alignment.CENTER);
//        setJustifyContentMode(JustifyContentMode.CENTER);
//
//        TextField firstName = new TextField("Имя");
//        TextField lastName = new TextField("Фамилия");
//        TextField email = new TextField("Электронка");
//        PasswordField password1 = new PasswordField("Пароль");
//        PasswordField password2 = new PasswordField("Подтвердите пароль");
//        return new VerticalLayout(
//                new H2("Register"),
//                firstName,
//                lastName,
//                email,
//                password1,
//                password2,
//                new Button("Send", event -> register(
//                        firstName.getValue(),
//                        lastName.getValue(),
//                        email.getValue(),
//                        password1.getValue(),
//                        password2.getValue()
//                ))
//        );
//    }

    private void register(String firstName, String lastName, String email, String password1, String password2) {
//        if (username.trim().isEmpty()) {
//            Notification.show("Enter a username");
        if (firstName.trim().isEmpty()) {
            Notification.show("Введите имя");
        } else if (lastName.trim().isEmpty()) {
            Notification.show("Введите фамилию");
        } else if (email.trim().isEmpty()) {
            Notification.show("Введите электронку");
        }
        else if (password1.isEmpty()) {
            Notification.show("Введите пароль");
        } else if (!password1.equals(password2)) {
            Notification.show("Пароли не совпадают(");
        } else {
            authService.register(firstName, lastName, email, password1);//, group="Эльфийский", status="Учится");
//            Notification.show("Check your email.");
        }
    }
}
