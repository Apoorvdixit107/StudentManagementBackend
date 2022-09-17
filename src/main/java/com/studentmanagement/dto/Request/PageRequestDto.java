package com.studentmanagement.dto.Request;



import lombok.Data;

@Data
public class PageRequestDto {
    private int pageNo;
    private int pageSize;
}
