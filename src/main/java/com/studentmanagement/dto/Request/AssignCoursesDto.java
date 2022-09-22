package com.studentmanagement.dto.Request;

import java.util.List;

import lombok.Data;

@Data
public class AssignCoursesDto {
    private List<String> courses;
    private String empId;
    private String section;
    private String semester;
    private String branch;
}
