package com.globallogic.patient.service;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.globallogic.patient.VO.ResponseTemplateVO;
import com.globallogic.patient.entity.Patient;

@Repository
public interface PatientService {

	// GET -> SHOW ALL PATIENTS
	public List<Patient> showPatient();



	// POST -> ADD PATIENT
	public Patient addPatient(Patient patient);

	// PUT ->UPDATE PATIENT
	public  Patient updatePatient(Patient patient,int patientId);

	// DELETE ->DELETE PATIENT
	public void deletePatient(int patientId);

	public ResponseTemplateVO getPatientWithMedicalRecord(int patientId);

	public Optional<Patient> showPatientById(int patientId);
	
	
	 
	

}