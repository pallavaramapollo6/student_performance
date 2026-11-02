package com.example.studentperformance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentperformance.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Find student by register number
    Student findByRegNo(Integer regNo);

    // Find students by department
    java.util.List<Student> findByDepartment(String department);

	Student findByRegNoAndPassword(String regNo, String password);
}