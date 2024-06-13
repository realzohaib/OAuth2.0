package com.erp.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class PreviousEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long previousId;
	private String companyName;
	private String companyAddress;
	private String designation;
	private String description;
	private String dateFrom;
	private String dateTo;
	private String previousManagerName;
	private String previousManagerPhoneCode;
	private String previousManagerContact;
	private String previousHRName;
	private String previousHRPhoneCode;
	private String previousHRContact;
	private double lastWithdrawnSalary;
	
	@ManyToOne
	@JsonBackReference
	private PersonalInfo personalinfo;
}
