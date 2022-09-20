package com.studentmanagement.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class StudentProfessor {
    @EmbeddedId
    protected StudentProfessorId id;
    private String courseId;
}

@Data
@Embeddable
class StudentProfessorId implements Serializable{
    private String studentId;
    private String employeeId;
}