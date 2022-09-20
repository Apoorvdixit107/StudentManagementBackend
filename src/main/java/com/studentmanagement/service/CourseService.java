package com.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
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
}