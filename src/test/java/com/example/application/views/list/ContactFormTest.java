package com.example.application.views.list;

import com.example.application.data.entity.Group;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactFormTest {
    private List<Group> groups;
    private List<Status> statuses;
    private Contact marcUsher;
    private Group group1;
    private Group group2;
    private Status status1;
    private Status status2;

    @BeforeEach  
    public void setupData() {
        groups = new ArrayList<>();
        group1 = new Group();
        group1.setName("Эльфийский");
        group2 = new Group();
        group2.setName("Язык фактов");
        groups.add(group1);
        groups.add(group2);

        statuses = new ArrayList<>();
        status1 = new Status();
        status1.setName("Status 1");
        status2 = new Status();
        status2.setName("Status 2");
        statuses.add(status1);
        statuses.add(status2);

        marcUsher = new Contact();
        marcUsher.setFirstName("Тайлер");
        marcUsher.setLastName("Дерден");
        marcUsher.setEmail("fight@club.com");
        marcUsher.setStatus(status1);
        marcUsher.setGroup(group2);
    }

    @Test
    public void formFieldsPopulated() {
        ContactForm form = new ContactForm(groups, statuses);
        form.setContact(marcUsher);
        assertEquals("Тайлер", form.firstName.getValue());
        assertEquals("Дерден", form.lastName.getValue());
        assertEquals("fight@club.com", form.email.getValue());
        assertEquals(group2, form.group.getValue());
        assertEquals(status1, form.status.getValue());
    }
    @Test
    public void saveEventHasCorrectValues() {
        ContactForm form = new ContactForm(groups, statuses);
        Contact contact = new Contact();
        form.setContact(contact);
        form.firstName.setValue("John");
        form.lastName.setValue("Doe");
        form.group.setValue(group1);
        form.email.setValue("john@doe.com");
        form.status.setValue(status2);
        AtomicReference<Contact> savedContactRef = new AtomicReference<>(null);
        form.addSaveListener(e -> {
            savedContactRef.set(e.getContact());
        });
        form.save.click();
        Contact savedContact = savedContactRef.get();
        assertEquals("John", savedContact.getFirstName());
        assertEquals("Doe", savedContact.getLastName());
        assertEquals("john@doe.com", savedContact.getEmail());
        assertEquals(group1, savedContact.getGroup());
        assertEquals(status2, savedContact.getStatus());
    }
}