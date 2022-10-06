package com.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.dto.Request.FeesDto;
import com.studentmanagement.repository.FeesRepository;

@Service
public class FeesServiceImpl implements FeesService {
    
    @Autowired
    private FeesRepository feesRepository;

    @Override
    public void putFees(FeesDto dto) {
       
        
    }
}
