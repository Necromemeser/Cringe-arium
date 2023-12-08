//package com.example.application.controller;
//
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("user")
//public class SimpleSecurityController {
//
//    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
//
//    @Autowired
//    public SimpleSecurityController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
//        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
//    }
//
//    @RequestMapping("exists/{username}")
//    public boolean userExists(@PathVariable("username") String username ) {
//        return inMemoryUserDetailsManager.userExists(username);
//    }
//
//    @RequestMapping("add/{username}/{password}")
//    public String add(@PathVariable("username") String username, @PathVariable("password") String password) {
//        inMemoryUserDetailsManager.createUser(new User(username, password, new ArrayList<GrantedAuthority>()));
//        return "added";
//    }
//}
