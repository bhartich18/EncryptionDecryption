package com.globallogic.patient;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.globallogic.patient.controller.PatientController;
import com.globallogic.patient.entity.Patient;
import com.globallogic.patient.repository.PatientRepo;
import com.globallogic.patient.service.PatientService;
import com.globallogic.patient.serviceImpl.PatientServiceImpl;

import lombok.RequiredArgsConstructor;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PatientController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PatientTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;
	@MockBean
	PatientController patientController;

	@MockBean
	PatientService patientService;

	@MockBean
	PatientRepo patientRepo;

	Patient RECORD_1 = new Patient(1, "Avni","cha", "Meerut", 983747553, 1);

	Patient RECORD_2 = new Patient(2, "Aksh","chahal", "Hapur", 925387868, 2);

	@SuppressWarnings("deprecation")
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
	}

	@Test
	@Order(1)

	public void getAllPatientsS() throws Exception {

		List<Patient> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2));

		Mockito.when(patientService.showPatient()).thenReturn(records);

		mockMvc.perform(MockMvcRequestBuilders.get("/patient/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		System.out.println("############# TEST 1 ##################");

		System.out.println("All Patient's records fetched successfully");

	}

	@Test
	@Order(2)

	public void getPatientById() throws Exception {
		Mockito.when(patientService.showPatientById(RECORD_1.getPatientId()))
				.thenReturn(java.util.Optional.of(RECORD_1));

		mockMvc.perform(MockMvcRequestBuilders.get("/patient/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		System.out.println("############# TEST 2 ##################");

		System.out.println("Patient with Id " + RECORD_1.getPatientId() + " fetched successfully.");

	}

	@Test
	@Order(3)
	public void addPatient() throws Exception {
		Patient record = Patient.builder().patientId(3).firstName("Bharti").lastName("Chahal").city("Meerut").phoneNo(748399456).recordId(3)

				.build();

		Mockito.when(patientRepo.save(record)).thenReturn(record);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(record));
		mockMvc.perform(mockRequest).andExpect(status().isOk());

		System.out.println("############# TEST 3 ##################");

		System.out.println("Patient with id " + record.getPatientId() + " created successfully");

	}

	@Test
	@Order(4)
	public void updatePatient() throws Exception {
		Patient record = Patient.builder().patientId(3).lastName("Chahal").city("Meerut").phoneNo(748489939).recordId(3)

				.build();

		Mockito.when(patientService.showPatientById(RECORD_1.getPatientId())).thenReturn(Optional.of(RECORD_1));
		Mockito.when(patientService.updatePatient(record, RECORD_1.getPatientId())).thenReturn(record);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(record));

		mockMvc.perform(mockRequest).andExpect(status().isOk());

		System.out.println("############# TEST 4 ##################");

		System.out.println("Patient with Id " + RECORD_1.getPatientId() + " updated successfully");

	}

	@Test
	@Order(5)

	public void deletePatientById() throws Exception {
		Mockito.when(patientService.showPatientById(RECORD_2.getPatientId())).thenReturn(Optional.of(RECORD_2));
		mockMvc.perform(MockMvcRequestBuilders.delete("/patient/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		System.out.println("############# TEST 5 ##################");

		System.out.println("Patient with Id " + RECORD_2.getPatientId() + " deleted successfully");

	}

}
