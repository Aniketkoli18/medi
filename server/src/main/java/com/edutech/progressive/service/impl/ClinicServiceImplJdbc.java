package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.dao.ClinicDAOImpl;
import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.service.ClinicService;

public class ClinicServiceImplJdbc implements ClinicService {
    public ClinicDAOImpl clinicDAOImpl;

    

    public ClinicServiceImplJdbc(ClinicDAOImpl clinicDAOImpl) {
        this.clinicDAOImpl = clinicDAOImpl;
    }

    public List<Clinic>getAllClinics(){
       try{
        return clinicDAOImpl.getAllClinics();
       }catch(SQLException e){
        System.out.println(e);
       }
       return null;
    }

    public Clinic getClinicById(int clinicId){
        try{
            return clinicDAOImpl.getClinicById(clinicId);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Integer addClinic(Clinic clinic){
        try{
            return clinicDAOImpl.addClinic(clinic);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public void updateClinic(Clinic clinic){
        try{
            clinicDAOImpl.updateClinic(clinic);
        }catch(Exception e){
            System.out.println(e);
        }

    }
    public void deleteClinic(int clinicId){
        try{
            clinicDAOImpl.deleteClinic(clinicId);
        }catch(Exception e){
            System.out.println(e);
        }
    }

}