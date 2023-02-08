package com.globallogic.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.patient.VO.ResponseTemplateVO;
import com.globallogic.patient.entity.Patient;
import com.globallogic.patient.service.PatientService;

//@RestController
//@RequestMapping("/patient")
@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	@GetMapping
	public ResponseEntity<Object> getAllPatients() {
		List<Patient> patientList = patientService.showPatient();
		return new ResponseEntity<>(patientList, HttpStatus.OK);
	}

	@GetMapping("/{patientId}")
	public ResponseEntity<Object> showPatientById(@PathVariable("patientId") int patientId) {

		Optional<Patient> patient = patientService.showPatientById(patientId);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> addPatient(@RequestBody Patient patient) {
		patient = patientService.addPatient(patient);
		return new ResponseEntity<>("Patient is created successfully with Id = " + patient.getPatientId(), HttpStatus.CREATED);
	}
//	@PostMapping
//	public Patient addPatient(@RequestBody Patient patient) {
//		patient = patientService.addPatient(patient);
//	}

	@PutMapping("/")
	public ResponseEntity<Object> updatePatient(@RequestBody Patient patient,@PathVariable ("patientId") int patientId ) {
		patientService.updatePatient(patient,patientId);
		return new ResponseEntity<>("Patient is updated successsfully", HttpStatus.OK);
	}
	
	

	@DeleteMapping("/{patientId}")
	public ResponseEntity<Object> deletePatient(@PathVariable("patientId") int patientId) {

		patientService.deletePatient(patientId);
		return new ResponseEntity<>("Patient is deleted successsfully", HttpStatus.OK);
	}
//	@GetMapping("/{patientId}")
//	public ResponseTemplateVO getPatientWithMedicalRecord(@PathVariable("patientId") int patientId) {
//
//		return patientService.getPatientWithMedicalRecord(patientId);
//	}
//	
	
}