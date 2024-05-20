package com.example.application.data.service;

import com.example.application.data.entity.Role;
import com.example.application.data.repository.UserRepository;
import com.example.application.views.DashboardView;
import com.example.application.views.MainLayout;
import com.example.application.views.ProfileView;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {
    }

    public static class AuthException extends Exception {
    }

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(String email, String password) throws AuthException {
        com.example.application.data.entity.User user = userRepository.findByEmail(email);
        if (user != null && user.checkPassword(password)) {
            createRoutes(user.getRole());
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).forEach(route ->
                RouteConfiguration.forSessionScope().setRoute(route.route, route.view, MainLayout.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        ArrayList<AuthorizedRoute> routes = new ArrayList<>();

        if (role == Role.USER) {
            routes.add(new AuthorizedRoute("dashboard", "Самореклама!", DashboardView.class));
            routes.add(new AuthorizedRoute("profile", "Профиль", ProfileView.class));
        }
        if (role == Role.ADMIN) {
            routes.add(new AuthorizedRoute("dashboard", "Самореклама!", DashboardView.class));
            routes.add(new AuthorizedRoute("students", "Студенты!", ListView.class));
            routes.add(new AuthorizedRoute("profile", "Профиль", ProfileView.class));
        }

        return routes;
    }
}