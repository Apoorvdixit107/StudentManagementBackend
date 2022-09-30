package com.studentmanagement.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ProfessorCourseCLass {
    @EmbeddedId
    private ProfessorCourseClassId profCourseClassId;
    private int totalLectures;
}
