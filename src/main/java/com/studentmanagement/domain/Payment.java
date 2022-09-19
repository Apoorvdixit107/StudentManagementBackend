package com.studentmanagement.domain;

import java.util.Collection;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;
import org.springframework.web.servlet.FlashMap;

import com.studentmanagement.helper.RoleUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Payment")
@Entity
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(unique = true,nullable = false)
    long pmtid;
    String status;
    @Column(nullable = false,unique = true)
    long sid;
    long amt_paid;
}
