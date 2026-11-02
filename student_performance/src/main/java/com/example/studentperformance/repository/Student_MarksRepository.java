package com.example.studentperformance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentperformance.model.Student_Marks;

public interface Student_MarksRepository
        extends JpaRepository<Student_Marks, Integer> {

}