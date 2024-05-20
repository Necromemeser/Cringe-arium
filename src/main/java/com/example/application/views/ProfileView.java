package com.example.application.views;

import com.example.application.data.entity.User;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.example.application.security.SecurityService;

@PermitAll
@Route(value = "profile", layout = MainLayout.class) // <1>
@PageTitle("Профиль")
public class ProfileView extends VerticalLayout {

    private final CrmService service;
    private final SecurityService securityService;

    @Autowired
    public ProfileView(CrmService service, SecurityService securityService) {
        this.service = service;
        this.securityService = securityService;

        UserDetails currentUser = securityService.getAuthenticatedUser();
        if (currentUser != null) {
            addProfileDetails(currentUser);
        } else {
            add(new H3("Пользователь не найден"));
        }
    }

    private void addProfileDetails(UserDetails user) {
        H3 header = new H3("Профиль");
        header.getStyle().set("font-size", "24px");
        header.getStyle().set("font-weight", "bold");
        add(header);

        HorizontalLayout detailsLayout = new HorizontalLayout();
        detailsLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        add(detailsLayout);

        Image profilePicture = new Image("images/Zoltan.png", "Profile Picture");
        profilePicture.setWidth("200px");
        profilePicture.setHeight("200px");
        detailsLayout.add(profilePicture);

        VerticalLayout profileDetails = new VerticalLayout();
        profileDetails.setDefaultHorizontalComponentAlignment(Alignment.START);
        detailsLayout.add(profileDetails);

        Label nameLabel = new Label(user.getUsername());
        nameLabel.getStyle().set("font-size", "20px");
        nameLabel.getStyle().set("font-weight", "bold");
        profileDetails.add(nameLabel);

        /*Label emailLabel = new Label(user.getEmail());
        emailLabel.getStyle().set("font-size", "16px");
        emailLabel.getStyle().set("font-weight", "bold");
        profileDetails.add(emailLabel);


        // Create a bio label
        Label bioLabel = new Label(bio);
        bioLabel.getStyle().set("font-size", "16px");
        profileDetails.add(bioLabel);*/

        Label course = new Label("Записи: нет");
        course.getStyle().set("font-size", "18px");
        profileDetails.add(course);

        // Create a separator
        Hr separator = new Hr();
        add(separator);

        // Create a section for appointment booking
        VerticalLayout appointmentLayout = new VerticalLayout();
        appointmentLayout.setDefaultHorizontalComponentAlignment(Alignment.START);
        add(appointmentLayout);

        // Create a title for appointment booking
        H3 appointmentTitle = new H3("Записаться на курс");
        appointmentLayout.add(appointmentTitle);

        // Create radio button group
        RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>();
        radioButtonGroup.setItems("Эльфийский", "Язык фактов");
        radioButtonGroup.setLabel("Выберите язык:");
        appointmentLayout.add(radioButtonGroup);

        // Create a submit button
        Button submitButton = new Button("Отправить");
        submitButton.addClickListener(event -> {
            Notification.show("Заявка отправлена. Скоро с вами свяжутся \uD83D\uDC80");
        });
        appointmentLayout.add(submitButton);
    }
}