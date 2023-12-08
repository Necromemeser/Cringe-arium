package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.cookieconsent.CookieConsent;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@PermitAll
@Route(value = "profile", layout = MainLayout.class) // <1>
@PageTitle("Профиль")
public class ProfileView extends VerticalLayout {

    private final CrmService service;
    private final String firstName = "Золтан";
    private final String lastName = "Хивай";

    private final InMemoryUserDetailsManager userDetailsManager;
    private final String name = "Золтан Хивай";
    private final String email = "zhivai@cirilla.gif";
    private final String bio = "Я думал меня ждут горячий окорок, холодное пиво. А тут - учеба.";

    @Autowired
    public ProfileView(CrmService service, InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.service = service;
        this.userDetailsManager = inMemoryUserDetailsManager;


        // Create a header label
        Label header = new Label("Профиль");
        header.getStyle().set("font-size", "24px");
        header.getStyle().set("font-weight", "bold");
        add(header);

        // Create a horizontal layout for the profile picture and details
        HorizontalLayout detailsLayout = new HorizontalLayout();
        detailsLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        add(detailsLayout);

        // Create a profile picture
        Image profilePicture = new Image("images/Zoltan.png", "Profile Picture");
        profilePicture.setWidth("200px");
        profilePicture.setHeight("200px");
        detailsLayout.add(profilePicture);

        // Create a vertical layout for the profile details
        VerticalLayout profileDetails = new VerticalLayout();
        profileDetails.setDefaultHorizontalComponentAlignment(Alignment.START);
        detailsLayout.add(profileDetails);

        // Create a name label
        Label nameLabel = new Label(name);
        nameLabel.getStyle().set("font-size", "20px");
        nameLabel.getStyle().set("font-weight", "bold");
        profileDetails.add(nameLabel);

        // Create an email label
        Label emailLabel = new Label(email);
        emailLabel.getStyle().set("font-size", "16px");
        emailLabel.getStyle().set("font-weight", "bold");
        profileDetails.add(emailLabel);

        // Create a bio label
        Label bioLabel = new Label(bio);
        bioLabel.getStyle().set("font-size", "16px");
        profileDetails.add(bioLabel);

        Label course = new Label("Курс: Эльфийский");
        course.getStyle().set("font-size", "18px");
        profileDetails.add(course);
    }

}
