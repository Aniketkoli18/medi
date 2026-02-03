package com.edutech.progressive.service;

import java.util.List;

import com.edutech.progressive.dto.PatientDTO;
import com.edutech.progressive.entity.Patient;

public interface PatientService {

    List<Patient> getAllPatients() throws Exception;

    public Integer addPatient(Patient patient) throws Exception;

    List<Patient> getAllPatientSortedByName() throws Exception;

    default void emptyArrayList() throws Exception{
    }

    //Do not implement these methods in PatientServiceImplArraylist.java class
    default void updatePatient(Patient patient) throws Exception{}

    default void deletePatient(int patientId)throws Exception {}

    default Patient getPatientById(int patientId)throws Exception {
        return null;
    }

    //Do not implement these methods in PatientServiceImplArraylist.java and PatientServiceImplJdbc.java class
    //Do not implement this method until day-13
    default public void modifyPatientDetails(PatientDTO patientDTO) throws Exception{ }
}
