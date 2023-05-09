package com.example.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import static com.example.application.data.entity.Role.USER;

@Entity
@Table(name = "contacts_table")
public class Contact extends AbstractEntity {

    @NotEmpty
    @Column(name = "first_name")
    private String firstName = "";

    @NotEmpty
    @Column(name = "last_name")
    private String lastName = "";

    @NotEmpty
    @Column(name = "password")
    private String password = "";

    @ManyToOne
    @JoinColumn(name = "group_id")
//    @NotNull
    @JsonIgnoreProperties({"students"})
    private Group group;

//    @NotNull
    @ManyToOne
    private Status status;

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email = "";

//    public Contact(String firstName, String lastName, String email, String password, Role role){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.group.setId();
////        this.status = new Status("Учится");
////        this.group = new Group("Эльфийский");
//    }

    public Contact(){}

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
