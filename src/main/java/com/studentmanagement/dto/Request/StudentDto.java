package com.studentmanagement.dto.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

    @NotNull
    private String rollNo;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private double attendance;
    @Email
    private String emailId;
    @Pattern(regexp = "[0-9]{10}",message = "Invalid Mobile")
    private String mobile;
    @NotNull
    private String password;
    @NotNull
    private String branch;
    @NotNull
    private String section;
    @NotNull
    private String semester;
    

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
