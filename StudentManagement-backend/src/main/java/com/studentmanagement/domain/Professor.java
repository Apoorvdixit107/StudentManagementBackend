package com.studentmanagement.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Professor implements UserDetails {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;
    
    private String employeeId;

    
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private long mobileNo;
	private String firstName;
    private String lastName;

	@Column(unique=true)
    private String username = null;
    @JsonIgnore
    @Column(length = 60)
    private String password = null;
    @JsonIgnore
    private boolean accountNonExpired = true;
    @JsonIgnore
    private boolean accountNonLocked = true;
    @JsonIgnore
    private boolean credentialsNonExpired = true;
    @JsonIgnore
    private boolean enabled = true;
    

	public Professor(){
		super();
	}

	public void setEmail(String email) {
        this.email = email;
        this.username = email;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
    public boolean isNew() {
        return pid == null;
    }
	
	
    
}
