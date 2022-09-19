package com.studentmanagement.service;

import com.studentmanagement.domain.Student;
import com.studentmanagement.dto.Request.PaymentDto;

public interface PaymentService {
    String getStatus(long sid);
    Student postPayment(PaymentDto dto);

}
