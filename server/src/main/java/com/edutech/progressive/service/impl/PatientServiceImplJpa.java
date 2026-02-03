package com.edutech.progressive.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.service.PatientService;


@Service
public class PatientServiceImplJpa implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImplJpa(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Integer addPatient(Patient patient) {
        Patient saved = patientRepository.save(patient);
        return saved.getPatientId(); // Return generated ID for test cases
    }

    @Override
    public void updatePatient(Patient patient) {
        if (patientRepository.existsById(patient.getPatientId())) {
            patientRepository.save(patient);
        }
    }

    @Override
public void deletePatient(int patientId) throws Exception {  // Add throws Exception
    if (patientRepository.existsById(patientId)) {
        patientRepository.deleteById(patientId);
    } else {
        throw new Exception("Patient not found");  // Throw exception if not found
    }
}

@Override
public Patient getPatientById(int patientId) throws Exception {
    return patientRepository.findById(patientId)
            .orElseThrow(() -> new Exception("Patient not found"));
}

    @Override
    public List<Patient> getAllPatientSortedByName() {
        return patientRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Patient::getFullName))
                .collect(Collectors.toList());
    }
}
