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
@Table(name = "Address")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Address {

	@Id
	private Long id;
    private String city;
    private String pin;

    public Address(){}

	public Address(Long id, 
        String city, 
        String pin
    ){
    this.id = id;
	this.city = city;
	this.pin = pin;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return this.city;
    }
    public void setPin(String pin){
        this.pin = pin;
    }

    public String getPin(){
        return this.pin;
    }

    public String toString(){
        return "[id = " + this.id +
                "city = " + this.city +
                "pin = " + this.pin +
            "]";
    }

}
