package com.studentmanagement.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;


@Embeddable
@Data
public class ProfessorCourseClassId implements Serializable{
private String employeeId;
private String courseId;
private String classId;

}
