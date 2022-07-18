package com.studentmanagement.service;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.dto.AuthToken;

public interface ProffessorAuthenticationService {

	
    AuthToken login(String username, String password);
    void logout(Professor student);
    Boolean changePassword(String password, String userNme);

}
