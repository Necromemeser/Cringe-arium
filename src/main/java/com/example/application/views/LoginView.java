package com.example.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login") 
@PageTitle("Вход")
@AnonymousAllowed
public class LoginView extends VerticalLayout { // implements BeforeEnterObserver {

	private final LoginForm login = new LoginForm(); 

	public LoginView(){
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

		add(new H1("Вход"), login, new RouterLink("Register", RegisterView.class));

	}

//	@Override
//	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//		// inform the user about an authentication error
//		if(beforeEnterEvent.getLocation()
//        .getQueryParameters()
//        .getParameters()
//        .containsKey("error")) {
//            login.setError(true);
//        }
//	}
}