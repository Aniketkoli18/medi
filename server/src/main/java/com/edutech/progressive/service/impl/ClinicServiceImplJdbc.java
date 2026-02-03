package com.edutech.progressive.service.impl;


import java.util.List;

import com.edutech.progressive.dao.ClinicDAO;
import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.service.ClinicService;

public class ClinicServiceImplJdbc implements ClinicService {


    private ClinicDAO clinicDAO;

    public ClinicServiceImplJdbc(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }

    @Override
    public List<Clinic> getAllClinics() throws Exception {
       List<Clinic> clinics=null;
        try {
            clinics= clinicDAO.getAllClinics();
        } catch (Exception e) {
           throw e;
        }
     
        return clinics;
    }

    @Override
    public Clinic getClinicById(int clinicId)throws Exception  {
       try {
             return  clinicDAO.getClinicById(clinicId);
        } catch (Exception e) {
           throw e;
        }
      
    }

    @Override
    public Integer addClinic(Clinic clinic)throws Exception  {
       try {
             return clinicDAO.addClinic(clinic);
        } catch (Exception e) {
          throw e;
        }
      
    }

    @Override
    public void updateClinic(Clinic clinic) throws Exception  {
       try {
            clinicDAO.updateClinic(clinic);
        } catch (Exception e) {
           throw e;
        }
       
    }

    @Override
    public void deleteClinic(int clinicId)throws Exception  {
         try {
            clinicDAO.deleteClinic(clinicId);
        } catch (Exception e) {
          throw e;
        }
    }

}
