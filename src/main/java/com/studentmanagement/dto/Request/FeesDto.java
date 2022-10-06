package com.studentmanagement.dto.Request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeesDto {
    private String reference_no;
    private String bank_name;
    private String branch;
    private Date date;
    private int ammount;
    private String type;

}
