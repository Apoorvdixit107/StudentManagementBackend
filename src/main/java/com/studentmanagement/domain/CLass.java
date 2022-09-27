package com.studentmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CLass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    private String branch;
    private String section;
    private String semester;
    private long totalStudents;
    private String classId;


}
