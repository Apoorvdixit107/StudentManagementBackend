package com.studentmanagement.service;

import com.studentmanagement.domain.Student;
import com.studentmanagement.dto.Response.AuthToken;

public interface StudentAuthenticationService {

    AuthToken login(String username, String password);

//    Optional<User> findByToken(String token);

    void logout(Student student);

    Boolean changePassword(String password, String userNme);
}
