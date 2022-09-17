package com.studentmanagement.dto.Request;

import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "[0-9]{10}",message = "Invalid Mobile")
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
