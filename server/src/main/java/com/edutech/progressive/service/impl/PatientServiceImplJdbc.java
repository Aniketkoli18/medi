package com.edutech.progressive.service.impl;


import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.PatientDAO;
import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;

public class PatientServiceImplJdbc implements PatientService {

    private PatientDAO patientDAO;

    public PatientServiceImplJdbc(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public List<Patient> getAllPatients() throws Exception {
        List<Patient> patients = null;
        try {
            patients = patientDAO.getAllPatients();
        } catch (Exception e) {
            throw e;
        }

        return patients;
    }

    @Override
    public Integer addPatient(Patient patient) throws Exception {
        try {
            return patientDAO.addPatient(patient);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Patient> getAllPatientSortedByName() throws Exception {
        List<Patient> patients = null;
        try {
            patients = patientDAO.getAllPatients();
            Collections.sort(patients);
        } catch (Exception e) {
            throw e;
        }
        return patients;
    }

    public void updatePatient(Patient patient) throws Exception {
        try {
            patientDAO.updatePatient(patient);
        } catch (Exception e) {
            throw e;
        }
    }

    public void deletePatient(int patientId) throws Exception {
        try {
            patientDAO.deletePatient(patientId);
        } catch (Exception e) {
            throw e;
        }
    }

    public Patient getPatientById(int patientId) throws Exception{
          try {
          return patientDAO.getPatientById(patientId);
        } catch (Exception e) {
        throw e;
        }
    }

}
