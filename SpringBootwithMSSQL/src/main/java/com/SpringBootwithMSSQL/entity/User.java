package com.SpringBootwithMSSQL.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "User")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class User {

	@Id
	private Long id;
    private String fn;
    private String ln;

    public User(){}

	public User(Long id, 
        String fn, 
        String ln
    ){
    this.id = id;
	this.fn = fn;
	this.ln = ln;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public void setFn(String fn){
        this.fn = fn;
    }

    public String getFn(){
        return this.fn;
    }
    public void setLn(String ln){
        this.ln = ln;
    }

    public String getLn(){
        return this.ln;
    }

    public String toString(){
        return "[id = " + this.id +
                "fn = " + this.fn +
                "ln = " + this.ln +
            "]";
    }

}
