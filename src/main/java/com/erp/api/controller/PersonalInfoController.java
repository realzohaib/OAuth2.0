package com.erp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erp.api.dao.IPersonalInfodao;
import com.erp.api.entity.PersonalInfo;
import com.erp.api.service.IPersonalInfoServiceImpl;

@RestController
@RequestMapping("api/v1")
public class PersonalInfoController {

	@Autowired
	private IPersonalInfoServiceImpl service;

	@Autowired
	private IPersonalInfodao repo;

	@PostMapping("/personalinfo")
	public ResponseEntity<?> create(@RequestParam("PersonalInfo") String personalinfo,
			@RequestParam("photo") MultipartFile file) {
		try {
			PersonalInfo personalInfo2 = service.create(personalinfo, file);
			return ResponseEntity.ok("Data Saved");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/personalinfo")
	public ResponseEntity<?> getAll() {
		try {

			List<PersonalInfo> list = service.getAll();
			if (list.isEmpty()) {
				return ResponseEntity.ok("No Records.");
			}
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/personalinfo/{empId}")
	public ResponseEntity<?> getByEmpId(@PathVariable long empId) {
		try {

			if (!repo.existsByEmpId(empId)) {
				return ResponseEntity.ok("Employee Not Found With Employee Id " + empId);
			}
			PersonalInfo info = service.getById(empId);

			if (info == null) {
				return ResponseEntity.ok("No Records found,Either User has been deleted or does not exist");
			}
			return ResponseEntity.ok(info);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/personalinfo/{empId}")
	public ResponseEntity<?> deleteById(@PathVariable long empId) {
		try {
			if (!repo.existsByEmpId(empId)) {
				return ResponseEntity.ok("Employee Not Found With Employee Id " + empId);
			}
			service.deleteUser(empId);
			return ResponseEntity.ok("User Deleted");

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/personalinfo/deletedData")
	public ResponseEntity<?> findAllDeletedData(){
		try {

			List <PersonalInfo> deleteList = service.getAllDeletedData();

			if (deleteList.isEmpty()) {
				return ResponseEntity.ok("No Records found,Either User has been deleted or does not exist");
			}
			return ResponseEntity.ok(deleteList);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@PutMapping("/personalinfo-update")
	public ResponseEntity<?> updatePersonalInfo(@RequestParam("PersonalInfo") String personalinfo,
			@RequestParam("photo") MultipartFile file) {
		try {
			PersonalInfo personalInfo2 = service.updateData(personalinfo, file);
			return ResponseEntity.ok("Data update");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
