package com.studentmanagement.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class StudentCourseId implements Serializable{
    private String rollNo;
    private String courseId;
}
