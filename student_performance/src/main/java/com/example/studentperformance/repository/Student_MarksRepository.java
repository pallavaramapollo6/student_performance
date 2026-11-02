package com.example.studentperformance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentperformance.model.Student_Marks;

@Repository
public interface Student_MarksRepository
        extends JpaRepository<Student_Marks, Integer> {

    // Find marks using student ID
    Student_Marks findByStudentId(Integer studentId);
}