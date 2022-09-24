package com.studentmanagement.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProfessorCourseClassId implements Serializable{
private String employeeId;
private String courseId;
private long classId;

}
