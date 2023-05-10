package com.example.application.views;

import com.example.application.data.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;
import java.util.Scanner;

@Route("login")
@PageTitle("Вход")
@AnonymousAllowed
public class LoginView extends VerticalLayout { // implements BeforeEnterObserver {

	InMemoryUserDetailsManager userDetailsManager;
	private final LoginForm login = new LoginForm();

	public LoginView(){

//		userDetailsManager = new InMemoryUserDetailsManager();
//		UserDetails user = userDetailsManager.loadUserByUsername("admin");
//		System.out.println(user);
//
//		UserDetails zh = userDetailsManager.loadUserByUsername("zhivai@cirilla.gif");
//		System.out.println(zh);

		addClassName("login-view");
		setSizeFull();
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		login.setAction("login");

		LoginI18n i18n = LoginI18n.createDefault();
		i18n.getForm().setTitle("Логинься");
		i18n.getForm().setUsername("Логин");
		i18n.getForm().setPassword("Пароль");
		i18n.getForm().setSubmit("Жмяк");
		i18n.getForm().setForgotPassword("Забыл пароль? Лол");

		login.setI18n(i18n);

		add(new H1("Вход"), login, new RouterLink("Нет акка? Ну так зарегайся", RegisterView.class));

	}

}
