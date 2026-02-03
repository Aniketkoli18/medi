package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;

@Service
public class PatientServiceImplArraylist implements PatientService {
    
    private static List<Patient> patientList = new ArrayList<>();
@Override
    public List<Patient> getAllPatients() {
        return patientList;
    }
@Override
    public Integer addPatient(Patient patient) {
        patientList.add(patient);
        return 1;
    }
@Override
    public Patient getPatientById(int patientId) {
        return patientList.stream()
                .filter(p -> p.getPatientId() == patientId)
                .findFirst()
                .orElse(null);
    }
@Override
    public void updatePatient(Patient patient) {
        deletePatient(patient.getPatientId());
        patientList.add(patient);
    }
@Override
    public void deletePatient(int patientId) {
        patientList.removeIf(p -> p.getPatientId() == patientId);
    }
@Override
    public List<Patient> getAllPatientSortedByName() {
        List<Patient> sortedList = new ArrayList<>(patientList);
        Collections.sort(sortedList);
        return sortedList;
    }
@Override   
    public void emptyArrayList() {
        patientList.clear();
    }
}
