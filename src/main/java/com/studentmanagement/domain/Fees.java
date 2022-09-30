package com.studentmanagement.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long reference_no;
    private String bank_name;
    private String branch;
    private Date date;
    private double ammount;
    private String type;
}
