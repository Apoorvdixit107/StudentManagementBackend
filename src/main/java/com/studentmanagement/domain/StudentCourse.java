package com.studentmanagement.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.data.annotation.Reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {
    @EmbeddedId
    protected StudentId id;
    private String status;
    
}

@Embeddable
@Data
class StudentId implements Serializable{
    private String rollNo;
    private String courseId;
}
