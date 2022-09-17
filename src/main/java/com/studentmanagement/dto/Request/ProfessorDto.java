package com.studentmanagement.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessorDto {
     	private Long pid;
	    private String email;
	    private String employeeId;
	    private long mobileNo;
	    private String password;
	    private String firstName;
	    private String lastName;

}
