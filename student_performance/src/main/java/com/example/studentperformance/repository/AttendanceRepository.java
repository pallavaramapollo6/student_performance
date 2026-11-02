package com.example.studentperformance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentperformance.model.Attendance;

@Repository
public interface AttendanceRepository
        extends JpaRepository<Attendance, Integer> {

    // Find attendance using student ID
    Attendance findByStudentId(Integer studentId);
}