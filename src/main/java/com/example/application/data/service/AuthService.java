package com.example.application.data.service;

import com.example.application.data.entity.Role;
import com.example.application.data.repository.UserRepository;
import com.example.application.views.DashboardView;
import com.example.application.views.MainLayout;
import com.example.application.views.list.ListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }
    public class AuthException extends Exception {
    }
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(String username, String password) throws AuthException {
        com.example.application.data.entity.User user = userRepository.getByUsername(username);
        if(user != null && user.checkPassword(password)) {
            createRoutes(user.getRole());
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream().
                forEach(route ->
                        RouteConfiguration.forSessionScope().setRoute(route.route, route.view, MainLayout.class));
    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        ArrayList<AuthorizedRoute> routes = new ArrayList<>();

        if(role.equals(Role.USER)) {
            routes.add(new AuthorizedRoute("", "Самореклама!", DashboardView.class));
        } else if (role.equals(Role.ADMIN)) {
            routes.add(new AuthorizedRoute("", "Самореклама!", DashboardView.class));
            routes.add(new AuthorizedRoute("students", "Студенты!", ListView.class));
        }

        return routes;
    }

    private InMemoryUserDetailsManager userDetailsManager;

    public UserDetailsService register(String firstName, String lastName, String email, String password) {
        UserDetails newUser = User.builder()
                .username(email)
                .password(password)
                .roles("USER")
                .build();
        userDetailsManager.createUser(newUser);
        return new InMemoryUserDetailsManager(newUser);
    }

}
