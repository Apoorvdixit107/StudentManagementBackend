package com.studentmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

    private String rollNo;
    private String firstName;
    private String lastName;
    private double attendance;
    private String emailId;
    private String mobile;
    private String password;

    public StudentDto(){

    }

    public StudentDto(String rollNo, String firstName, String lastName, double attendance, String emailId,
            String mobile,String password) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.attendance = attendance;
        this.emailId = emailId;
        this.mobile = mobile;
        this.password = password;
    }
}
