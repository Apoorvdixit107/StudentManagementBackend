package com.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.Course;
import com.studentmanagement.domain.ProfessorCourse;
import com.studentmanagement.domain.ProfessorCourseId;
import com.studentmanagement.domain.StudentCourse;
import com.studentmanagement.domain.StudentCourseId;
import com.studentmanagement.dto.Response.AttendanceResponse;
import com.studentmanagement.dto.Response.AttendanceResponse.SubjectsAttendance;
import com.studentmanagement.repository.ProfCourseRepository;
import com.studentmanagement.repository.StudentCourseRepository;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ProfCourseRepository profCourseRepository;

    public AttendanceResponse getAttendance(String rollNo){
        AttendanceResponse response = new AttendanceResponse();
        List<StudentCourse> studentCourses = studentCourseRepository.findByRollNo(rollNo);
        
        studentCourses.forEach(course ->{
            SubjectsAttendance attendance = new SubjectsAttendance();
            attendance.setAttendedLectures(course.getAttendedLectures());
            response.setAttendedLectures(response.getAttendedLectures() + course.getAttendedLectures());
            attendance.setTotalLectures(course.getTotalLectures());
            response.setTotalLectures(response.getTotalLectures() + course.getTotalLectures());
            attendance.setAttendance(course.getAttendedLectures()/course.getTotalLectures()*100);
            attendance.setSubject(course.getId().getCourseId());
            response.getData().add(attendance);
        });
   
        response.setTotalAttendance((double)response.getAttendedLectures()/response.getTotalLectures()*100);
        return response;
    }

    public void markAttendance(List<String> request,String courseId,String empId) throws Exception{
        ProfessorCourseId Id = new ProfessorCourseId();
        Id.setCourseId(courseId);
        Id.setEmployeeId(empId);
        Optional<ProfessorCourse> findById = profCourseRepository.findById(Id);
        if(!findById.isPresent()){throw new Exception("Only professor of this course can mark attendance");}
        Course course = courseService.get(courseId);
        if(course == null){
            throw new Exception("Course doent Exist");
        }
        List<String> notEnrolled = new ArrayList<>();
        request.forEach(rollNo ->{
            StudentCourseId id = new StudentCourseId();
            id.setCourseId(courseId);
            id.setRollNo(rollNo);
            Optional<StudentCourse> student = studentCourseRepository.findById(id);
            if(student.isPresent()){
                StudentCourse studentCourse = student.get();
                int attendedLectures = studentCourse.getAttendedLectures();
                int totalAttended = studentCourse.getTotalLectures();
                studentCourse.setAttendedLectures(attendedLectures+1);
                studentCourse.setTotalLectures(totalAttended+1);
                studentCourseRepository.save(studentCourse);
            }else{
                notEnrolled.add(rollNo);
            }
        });
        
        if(findById.isPresent()){
            findById.get().setTotalLectures(findById.get().getTotalLectures() +1);
            profCourseRepository.save(findById.get());
        }
        
        if(notEnrolled.size() != 0){
            throw new Exception("Addtendence marked and these students "+ notEnrolled +" are not enrolled in the given course");
        }
    }
}
