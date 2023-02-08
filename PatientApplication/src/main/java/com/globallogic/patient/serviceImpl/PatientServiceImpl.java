package com.globallogic.patient.serviceImpl;
import com.globallogic.patient.VO.MedicalHistory;
import com.globallogic.patient.VO.ResponseTemplateVO;
import com.globallogic.patient.entity.Patient;

import com.globallogic.patient.exception.ResourceNotFoundException;
import com.globallogic.patient.repository.PatientRepo;
import com.globallogic.patient.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public  class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepo patientRepo;
	@Autowired 
	RestTemplate restTemplate;

	@Override
	public List<Patient> showPatient() {
		return patientRepo.findAll();
	}

	
	public Optional<Patient> showPatientById(int patientId) {

		return patientRepo.findById(patientId);
		
	}
     
	@Override
	public Patient addPatient(Patient patient) {

        Optional<Patient > savedPatient = patientRepo.findById(patient.getPatientId());
        if(savedPatient.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with givenid:" + patient.getPatientId());
        }
        return patientRepo.save(patient);
    }
	
	@Override
	public Patient updatePatient(Patient patient,int patientId) {
		Patient existingPatient = this.patientRepo.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id :" + patientId));
		existingPatient.setFirstName(patient.getFirstName());
		existingPatient.setLastName(patient.getLastName());
		existingPatient.setCity(patient.getCity());
		existingPatient.setPhoneNo(patient.getPhoneNo());
//		existingPatient.setDateOfBirth(patient.getDateOfBirth());

		return  this.patientRepo.save(existingPatient) ;
	}

	@Override
	public void deletePatient(int patientId) {
		Patient patient=this.patientRepo.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient not found with id :" + patientId));
		this.patientRepo.delete(patient);
		
	}


	public ResponseTemplateVO getPatientWithMedicalRecord(int patientId) {
		ResponseTemplateVO vo=new ResponseTemplateVO();
		Patient patient=patientRepo.findByPatientId(patientId);

        MedicalHistory medicalHistory =
        		restTemplate.getForObject("http://localhost:9001/medicalHistory/"+ patient.getPatientId(),MedicalHistory.class );


        vo.setPatient(patient);
        vo.setMedicalHistory(medicalHistory);

        return  vo;
	}

	
	

	
	
	
	

}