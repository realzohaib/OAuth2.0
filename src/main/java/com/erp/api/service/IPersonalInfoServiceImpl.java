package com.erp.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.erp.api.dao.IPersonalInfodao;
import com.erp.api.entity.Education;
import com.erp.api.entity.PersonalInfo;
import com.erp.api.entity.PreviousEmployee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IPersonalInfoServiceImpl implements IPersonalInfoService {
	@Autowired
	private IPersonalInfodao repo;

	@Override
	public List<PersonalInfo> getAll() {
		List<PersonalInfo> list = new ArrayList<PersonalInfo>();
		 List<PersonalInfo> findAll = repo.findAll();
		 for(PersonalInfo personal : findAll) {
			 if(personal.isDeleted()== false) {
				 list.add(personal);
			 }
		 }
		 return list;
	}

	@Override
	public PersonalInfo create(String personalinfo, MultipartFile file) throws IOException {
		// converting string to json and multipart is for photo
		ObjectMapper objectMapper = new ObjectMapper();
		PersonalInfo value = objectMapper.readValue(personalinfo, PersonalInfo.class);

		value.setPhoto(file.getBytes());
		List<Education> educations = value.getEducations();

		for (Education edc : educations) {
			edc.setPersonalinfo(value);
		}

		List<PreviousEmployee> oldEmployee = value.getOldEmployee();
		for (PreviousEmployee prev : oldEmployee) {
			prev.setPersonalinfo(value);
		}
		return repo.save(value);
	}

	@Override
	public PersonalInfo getById(long empId) {
		 PersonalInfo user = repo.findByempId(empId);
		 if(user.isDeleted() == false) {
			 return user;
		 }
		 return null;
		 
	}

	@Override
	public void deleteUser(Long empId) {
		PersonalInfo user = repo.findByempId(empId);
		user.setDeleted(true);
		repo.save(user);

	}

	@Override
	public List<PersonalInfo> getAllDeletedData() {
		 List <PersonalInfo> deletelist = new ArrayList <PersonalInfo>();
		 List<PersonalInfo> deleteFind= repo.findAll();
		 for (PersonalInfo per : deleteFind) {
			if(per.isDeleted()==true) {
				deletelist.add(per);
			}
		}
		 return deletelist;
		 
	}

	@Override
	public PersonalInfo updateData(String persnalinfo, MultipartFile file)
			throws JsonMappingException, JsonProcessingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		PersonalInfo value = objectMapper.readValue(persnalinfo, PersonalInfo.class);
		
		if(file != null || !file.isEmpty()) {
			value.setPhoto(file.getBytes());
		}
		
		List<Education> educations = value.getEducations();

		for (Education edc : educations) {
			edc.setPersonalinfo(value);
		}

		List<PreviousEmployee> oldEmployee = value.getOldEmployee();
		for (PreviousEmployee prev : oldEmployee) {
			prev.setPersonalinfo(value);
		}
		return repo.save(value);

	}

}
