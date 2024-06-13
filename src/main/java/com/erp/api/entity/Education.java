package com.erp.api.entity;

import javax.persistence.Column;
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
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ed_id")
	private Long id;

	@Column(name = "Issuing_authority")
	private String issuingAuthority;

	@Column(name = "marks_percentage_grade")
	private String marksOrGrade;

	@Column(name = "year")
	private String year;

	@ManyToOne
	@JsonBackReference
	private PersonalInfo personalinfo;

}
