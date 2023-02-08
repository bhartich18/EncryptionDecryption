package com.globallogic.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.patient.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
	 Patient findByPatientId(int patientId);

}
