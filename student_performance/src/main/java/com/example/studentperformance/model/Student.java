
package com.example.studentperformance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private String regNo;

    private String name;
    private String department;
    private String password;

    private Integer pythonMarks;
    private Integer sqlMarks;
    private Integer javaMarks;

    private Double attendance;

    private String studentId;
    private int daysPresent;

    // Registration number
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    // Student name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Department
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Python marks
    public Integer getPythonMarks() {
        return pythonMarks;
    }

    public void setPythonMarks(Integer pythonMarks) {
        this.pythonMarks = pythonMarks;
    }

    // SQL marks
    public Integer getSqlMarks() {
        return sqlMarks;
    }

    public void setSqlMarks(Integer sqlMarks) {
        this.sqlMarks = sqlMarks;
    }

    // Java marks
    public Integer getJavaMarks() {
        return javaMarks;
    }

    public void setJavaMarks(Integer javaMarks) {
        this.javaMarks = javaMarks;
    }

    // Attendance percentage
    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    // Student ID
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // Days present
    public int getDaysPresent() {
        return daysPresent;
    }

    public void setDaysPresent(int daysPresent) {
        this.daysPresent = daysPresent;
    }
}
