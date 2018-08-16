package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstname;
    private String lastname;
    private String email;
    private String address;

    @ManyToMany
    @JoinTable(name="stubent_course",
            joinColumns = @JoinColumn(name = "STUDENT_ID",referencedColumnName = "ID"),inverseJoinColumns = @JoinColumn(name = "COURSE_ID",referencedColumnName = "ID"))
    private Set<Course> cast;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Course> getCast() {
        return cast;
    }

    public void setCast(Set<Course> cast) {
        this.cast = cast;
    }
}
