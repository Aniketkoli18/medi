package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.DoctorDAO;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

public class DoctorServiceImplJdbc  implements DoctorService{

      private DoctorDAO doctorDAO;

    

    public DoctorServiceImplJdbc(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    public List<Doctor> getAllDoctors() throws Exception  {
       List<Doctor> doctors=null;
        try {
            doctors= doctorDAO.getAllDoctors();
        } catch (Exception e) {
           throw e;
        }
     
        return doctors;
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws Exception  {
         try {

            Integer id=doctorDAO.addDoctor(doctor);
            if(id!=null && id>0){
                doctor.setDoctorId(id);
            }
             return id;
        } catch (Exception e) {
          throw e;
        }
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience()throws Exception  {
        List<Doctor> doctors=null;
        try {
            doctors= doctorDAO.getAllDoctors();
             Collections.sort(doctors);
            
        } catch (Exception e) {
           throw e;
        }
     
        return doctors;
        
    }

     public void updateDoctor(Doctor doctor) throws Exception {
        try {
            doctorDAO.updateDoctor(doctor);;
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteDoctor(int doctorId) throws Exception {
        try {
            doctorDAO.deleteDoctor(doctorId);;
        } catch (Exception e) {
            throw e;
        }
    }

    public Doctor getDoctorById(int doctorId) throws Exception{
          try {
          return doctorDAO.getDoctorById(doctorId);
        } catch (Exception e) {
        throw e;
        }
    }


}
