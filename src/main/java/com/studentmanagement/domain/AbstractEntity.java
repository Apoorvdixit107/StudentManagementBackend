package com.studentmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;



import javax.persistence.Version;
import java.util.Date;

@Getter
@Setter
public class AbstractEntity {

    @CreatedDate
    @JsonIgnore
    protected Date createdDate;
    @CreatedBy
    @JsonIgnore
    protected String createdBy;
    @LastModifiedDate
    @JsonIgnore
    protected Date updatedDate;
    @LastModifiedBy
    @JsonIgnore
    protected String updatedBy;
    @Version
    @JsonIgnore
    protected int version;
}
