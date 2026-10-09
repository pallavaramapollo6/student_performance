package com.example.studentperformance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentperformance.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
