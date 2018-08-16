package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface StudentRepository  extends  CrudRepository <Student,Long>{
    ArrayList<Student> findByfirstname(String firstname);
    ArrayList<Student> findBylastname(String lastname);
    ArrayList<Student> findByemail(String email);
}
