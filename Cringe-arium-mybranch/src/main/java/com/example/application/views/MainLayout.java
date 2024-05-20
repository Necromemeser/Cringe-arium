package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class MainLayout extends AppLayout {
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        RouterLink logoLink = new RouterLink("", DashboardView.class);
        H1 logo = new H1("Cringe-arium");
        logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);
        logoLink.add(logo);
        logoLink.setTabIndex(-1); // Если вы хотите, чтобы logo не фокусировался при навигации с клавиатуры
        logoLink.getStyle().set("text-decoration", "none");


        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Выйти " + u, e -> securityService.logout());

        var header = new HorizontalLayout(new DrawerToggle(), logoLink, logout); // Используйте logoLink здесь

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logoLink); // Расширяйте logoLink, а не logo
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
//                new RouterLink("Студенты", ListView.class),
                new RouterLink("Главная", DashboardView.class),
                new RouterLink("Профиль", ProfileView.class)
        ));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            addToDrawer(new VerticalLayout(
                    new RouterLink("Студенты", ListView.class)
            ));
        }
    }
}