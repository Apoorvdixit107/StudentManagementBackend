package com.studentmanagement.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.Payment;
import com.studentmanagement.domain.Student;
import com.studentmanagement.dto.Request.PaymentDto;
import com.studentmanagement.dto.Request.StudentDto;
import com.studentmanagement.helper.RoleUtil;
import com.studentmanagement.repository.PaymentRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
    
private long pay=130100L;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PaymentRepository paymentRepository;
    
    @Autowired
    StudentRepository studentRepository;



    Payment dtoToPayment(PaymentDto dto){
        return this.modelMapper.map(dto,Payment.class);
    }

    PaymentDto paymentToDto(Payment payment){
        return this.modelMapper.map(payment,PaymentDto.class);
    }

    @Override
    public String getStatus(long sid) {
       return this.paymentRepository.findBySid(sid).get().getStatus();
        
    }

    @Override
    public Student postPayment(PaymentDto dto) {
       Payment payment= this.paymentRepository.save(this.dtoToPayment(dto));
        if(dto.getAmt_paid()==pay){
          payment.setStatus(RoleUtil.FULL_STATUS);
        }
        else if(dto.getAmt_paid()<pay&&dto.getAmt_paid()>0){
            payment.setStatus(RoleUtil.PARTIAL_STATUS);
        }
        else{
            payment.setStatus(RoleUtil.NO_STATUS);
        }

        return this.studentRepository.findByRollNo(payment.getSid()).get();
    }
}
