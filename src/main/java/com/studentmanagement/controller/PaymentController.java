package com.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.dto.Request.PaymentDto;
import com.studentmanagement.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/status/{sid}")
    public ResponseEntity<?> getStatus(@PathVariable("sid") long sid) {
        return ResponseEntity.ok(this.paymentService.getStatus(sid));

    }

    @PostMapping("/putpayment")
    public ResponseEntity<?> postPayment(@RequestBody PaymentDto dto) {
        return ResponseEntity.ok(this.paymentService.postPayment(dto));
    }
}
