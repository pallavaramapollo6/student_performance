package com.example.studentperformance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private Integer regNo;

    private String name;
    private String department;
    private String password;

    public Integer getRegNo() {
        return regNo;
    }

    public void setRegNo(Integer regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public int getPythonMarks() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPythonMarks1(Integer pythonMarks) {
		// TODO Auto-generated method stub
		
	}

	public void setAttendance(Double attendance) {
		// TODO Auto-generated method stub
		
	}

	public void setPythonMarks11(Integer sqlMarks) {
		// TODO Auto-generated method stub
		
	}

	public void setPythonMarks(Integer sqlMarks) {
		// TODO Auto-generated method stub
		
	}
}