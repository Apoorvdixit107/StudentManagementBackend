package com.studentmanagement.domain;

import java.util.Collection;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studentmanagement.dto.Request.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString

public class Student implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(unique = true)
    private String rollNo;
    @Column(unique = true)
    private String emailId;
    @Column(unique = true)
    private String mobile;
    
    private String firstName;
    private String lastName;
    private double attendance;
    


    @Column(unique=true)
    private String username = null;
    @JsonIgnore
    @Column(length = 60)
    private String password = null;
    @JsonIgnore
    private boolean accountNonExpired = true;
    @JsonIgnore
    private boolean accountNonLocked = true;
    @JsonIgnore
    private boolean credentialsNonExpired = true;
    @JsonIgnore
    private boolean enabled = true;

    public Student() {
        super();
    }
    public Student(StudentDto studentDto){
        this.mobile = studentDto.getMobile();
        this.username = studentDto.getMobile();
        this.password = studentDto.getPassword();
        this.firstName = studentDto.getFirstName();
        this.lastName = studentDto.getLastName();
        this.emailId = studentDto.getEmailId();
        this.attendance = studentDto.getAttendance();
        this.rollNo = studentDto.getRollNo();
    }
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        this.username = mobile;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return null;
    }
   
  
}
