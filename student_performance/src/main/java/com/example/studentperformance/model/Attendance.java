package com.example.studentperformance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    private Integer studentId;

    private Double attendance;
    private Integer daysPresent;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    public Integer getDaysPresent() {
        return daysPresent;
    }

    public void setDaysPresent(Integer daysPresent) {
        this.daysPresent = daysPresent;
    }
}