package com.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.Course;
import com.studentmanagement.dto.Request.CourseRequestDto;
import com.studentmanagement.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    public Course save(CourseRequestDto requestDto){
        Course entity = new Course().getCourse(requestDto);
        courseRepository.save(entity);
        return entity;
    }
    public Course get(String courseId){
        
        return courseRepository.findByCourseId(courseId);
        
    }
}
