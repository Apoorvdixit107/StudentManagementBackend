package com.medigram.medigrambackend.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medigram.medigrambackend.dto.request.UserDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "userdata")
public class UserData implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(unique=true)
    private String username = null;
    @JsonIgnore
    private String password = null;
    @JsonIgnore
    private boolean accountNonExpired = true;
    @JsonIgnore
    private boolean accountNonLocked = true;
    @JsonIgnore
    private boolean credentialsNonExpired = true;
    @JsonIgnore
    private boolean enabled = true;

    
    @NotBlank
    private String name;
    
    @Column(unique = true)
    @Email
    private String emailId;

    private String role;

    public UserData(){
        
    }
    
    public UserData(UserDto userDto){
        this.username = userDto.getEmailId();
        this.name = userDto.getUserName();
        this.emailId = userDto.getEmailId();
        this.password = userDto.getPassword();
        this.role = userDto.getRole();
    }

   
    @JsonIgnore
    public boolean isNew() {
        return id==null;
    }
    
    // public void setMobile(String mobile) {
    //     this.mobile = mobile;
    //     this.username = mobile;
    // }
    public void setEmailId(String emailId){
        this.emailId = emailId;
        this.username = emailId;
    }

   

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role));
        return list;
        
    }


    
    
    
}
