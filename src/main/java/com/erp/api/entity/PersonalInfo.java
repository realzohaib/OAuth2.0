package com.erp.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@Setter
//@Getter
@Entity
public class PersonalInfo {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;

	@Column(name = "name_prefix")
	private String namePrefix;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "father_first_name")
	private String fathersFirstName;

	@Column(name = "father_middle_name")
	private String fathersMiddleName;

	@Column(name = "father_last_name")
	private String fathersLastName;

	@Column(name = "dob")
	private String dateOfBirth;

	private Integer age;

	@Column(name = "marital_status ")
	private String maritalStatus;

	@Column(name = "country_Code")
	private String phoneCode;

	@Column(name = "personal_contact_no")
	private String personalContactNo;

	@Column(name = "email_id")
	private String email;
	//lob is for loading photo 
	@Lob
	@Column(length = 1048576)
	private byte[] photo;
	
	private boolean isDeleted;

	@OneToMany(mappedBy = "personalinfo")
	@Cascade(CascadeType.ALL)
	@JsonManagedReference
	private List<Education> educations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "personalinfo")
	@Cascade(CascadeType.ALL)
	private List<PreviousEmployee> oldEmployee;

}
