package ru.mashka.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "specialists")
public class Specialist {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String patronymic;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    public Specialist(String firstName, String lastName, String patronymic, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.id = id;
    }

    public Specialist() {

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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
