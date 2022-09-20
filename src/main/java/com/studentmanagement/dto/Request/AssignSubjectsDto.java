package com.studentmanagement.dto.Request;

import java.util.List;

import lombok.Data;

@Data
public class AssignSubjectsDto {
    private List<String> subjects;
    private String rollNo;
}
