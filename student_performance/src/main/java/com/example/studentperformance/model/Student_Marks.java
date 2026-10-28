package com.example.studentperformance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_marks")
public class Student_Marks {

    @Id
    private String studentId;

    private Integer pythonMarks;
    private Integer sqlMarks;
    private Integer javaMarks;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getPythonMarks() {
        return pythonMarks;
    }

    public void setPythonMarks(Integer pythonMarks) {
        this.pythonMarks = pythonMarks;
    }

    public Integer getSqlMarks() {
        return sqlMarks;
    }

    public void setSqlMarks(Integer sqlMarks) {
        this.sqlMarks = sqlMarks;
    }

    public Integer getJavaMarks() {
        return javaMarks;
    }

    public void setJavaMarks(Integer javaMarks) {
        this.javaMarks = javaMarks;
    }
}