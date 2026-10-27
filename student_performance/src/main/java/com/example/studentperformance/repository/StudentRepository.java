package com.example.studentperformance.repository;

import com.example.studentperformance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByRegNoAndPassword(String regNo, String password);
}

