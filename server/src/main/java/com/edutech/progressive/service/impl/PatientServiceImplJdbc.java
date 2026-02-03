package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.edutech.progressive.dao.PatientDAOImpl;
import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;

public class PatientServiceImplJdbc implements PatientService {

    PatientDAOImpl patientDAOImpl;

    
    public PatientServiceImplJdbc(PatientDAOImpl patientDAOImpl) {
        this.patientDAOImpl = patientDAOImpl;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient>a= new ArrayList<>();
        try{
       a=patientDAOImpl.getAllPatients();
        }catch(SQLException e){
            e.printStackTrace();
        }
       return a;
    }

    @Override
    public Integer addPatient(Patient patient) {
        Integer a=null;
        try{
        a= patientDAOImpl.addPatient(patient);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public List<Patient> getAllPatientSortedByName() {
       List<Patient>l=new ArrayList<>();
       try{
        l=patientDAOImpl.getAllPatients();
       }catch(SQLException e){
        e.printStackTrace();
       }
      Collections.sort(l,Comparator.comparing(Patient::getFullName));
      return l;
    }

    public void updatePatient(Patient patient){
        try{
        patientDAOImpl.updatePatient(patient);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void deletePatient(int patientId){

        try {
            patientDAOImpl.deletePatient(patientId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public Patient getPatientById(int patientId){
        try {
            return patientDAOImpl.getPatientById(patientId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}