package com.erp.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.api.entity.PersonalInfo;

@Repository
public interface IPersonalInfodao extends JpaRepository<PersonalInfo, Long> {
	
	public PersonalInfo findByempId(Long empId);
	
	 boolean existsByEmpId(Long empId);
		
	

}
