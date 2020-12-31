package com.projet.Model;


import javax.persistence.*;

@Entity
public class Student {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;
    private String name;
    private String lastname;
    @ManyToOne
    private Section section;

    public Student(int id, String name, String lastname, Section section) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.section = section;
    }

    public Student() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
