package com.globallogic.patient.VO;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public LocalDate getLastAppointmentDate() {
		return lastAppointmentDate;
	}
	public void setLastAppointmentDate(LocalDate lastAppointmentDate) {
		this.lastAppointmentDate = lastAppointmentDate;
	}
	public String getDiagnosedBy() {
		return diagnosedBy;
	}
	public void setDiagnosedBy(String diagnosedBy) {
		this.diagnosedBy = diagnosedBy;
	}
	public String getLastReceivedMedicine() {
		return lastReceivedMedicine;
	}
	public void setLastReceivedMedicine(String lastReceivedMedicine) {
		this.lastReceivedMedicine = lastReceivedMedicine;
	}
	int recordId;
	LocalDate lastAppointmentDate;
	String diagnosedBy;
	String lastReceivedMedicine;
}
