package com.studentmanagement.dto.Request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class CourseRequestDto {

    @NotNull
    private String courseId;
	@NotNull
    private Double credits;
	@NotNull
    private String courseType;
	@NotNull
    private String name;
}