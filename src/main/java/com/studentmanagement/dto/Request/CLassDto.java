package com.studentmanagement.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CLassDto {

    
    private String branch;
    private String section;
    private String semester;
    private long totalStudents;
}
