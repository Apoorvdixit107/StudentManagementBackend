package com.studentmanagement.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;


@Data
@Entity
public class ProfessorCourse {

    
    @EmbeddedId
    private ProfessorCourseId profCourseId;

}

