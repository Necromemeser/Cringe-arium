package com.example.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import com.example.application.data.entity.Role;

@Entity
@Table(name = "users_table")
public class User extends AbstractEntity {

    @NotEmpty
    @Column(name = "first_name")
    private String firstName = "";

    @NotEmpty
    @Column(name = "last_name")
    private String lastName = "";

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email = "";

    @NotEmpty
    @Column(name = "password")
    private String password = "";

    @NotEmpty
    @Column(name= "role")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return password.equals(getPassword());
    }
}
