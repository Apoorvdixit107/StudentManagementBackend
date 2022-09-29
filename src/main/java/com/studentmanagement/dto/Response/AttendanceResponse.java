package com.studentmanagement.dto.Response;

import java.util.List;

import lombok.Data;

@Data
public class AttendanceResponse {
    List<SubjectsAttendance> data;
    private Double totalAttendance;
    private Integer totalLectures;
    private Integer attendedLectures;
    @Data
    public static class SubjectsAttendance{
        private String subject;
        private int totalLectures;
        private int attendedLectures;
        private double attendance;
    }
}
