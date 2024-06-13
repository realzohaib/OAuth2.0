package com.erp.api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.erp.api.entity.PersonalInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IPersonalInfoService {
	public PersonalInfo create(String personalinfo ,MultipartFile file) throws JsonMappingException, JsonProcessingException, IOException;
	public List<PersonalInfo> getAll();
	public PersonalInfo getById(long empId);
	public void deleteUser(Long empId);
	public List<PersonalInfo> getAllDeletedData();
	public PersonalInfo updateData(String persnalinfo, MultipartFile file) throws JsonMappingException, JsonProcessingException, IOException;
	
}
