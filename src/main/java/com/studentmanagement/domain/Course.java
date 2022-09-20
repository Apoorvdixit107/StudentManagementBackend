package com.studentmanagement.domain;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.studentmanagement.dto.Request.CourseRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(unique = true)
    @NotNull
    private String courseId;
    private Double credits;
    private String courseType;
    private String name;

    public Course getCourse(CourseRequestDto request){
        Course course = new Course();
        course.courseId = request.getCourseId();
        course.courseType = request.getCourseType();
        course.credits = request.getCredits();
        course.name = request.getName();
        return course;
    }
}
