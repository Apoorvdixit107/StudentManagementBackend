package com.studentmanagement.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProfessorCourseId implements Serializable{
private String EmployeeId;
private String CourseId;
}