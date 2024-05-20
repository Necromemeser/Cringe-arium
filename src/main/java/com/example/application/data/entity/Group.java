package com.example.application.data.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Formula;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "groups_table")
public class Group extends AbstractEntity {
    @NotBlank
    @Column(name = "group_name")
    private String name;

    @OneToMany(mappedBy = "group")
    @Nullable
    private List<Contact> students = new LinkedList<>();


    @Formula("(select count(c.id) from contacts_table c where c.group_id = id)")
    private int studentsCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getStudents() {
        return students;
    }

    public void setStudents(List<Contact> employees) {
        this.students = employees;
    }

    public int getStudentsCount(){
        return studentsCount;
    }
}
