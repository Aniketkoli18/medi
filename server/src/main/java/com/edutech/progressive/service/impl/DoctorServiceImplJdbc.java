package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.edutech.progressive.dao.DoctorDAOImpl;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

public class DoctorServiceImplJdbc implements DoctorService  {
  DoctorDAOImpl doctorDAOImpl;
  
  

    public DoctorServiceImplJdbc(DoctorDAOImpl doctorDAOImpl) {
    this.doctorDAOImpl = doctorDAOImpl;
  }

    @Override
    public List<Doctor> getAllDoctors() {
      List<Doctor>a= new ArrayList<>();
      try{
        a=doctorDAOImpl.getAllDoctors();

      }catch(SQLException e){
        e.printStackTrace();
      }
      return a;
    }

    public Doctor getDoctorById(int doctorId){
        try{
          return doctorDAOImpl.getDoctorById(doctorId);
        }catch(SQLException e){
          e.printStackTrace();
        }
        return null;
    }
    @Override
    public Integer addDoctor(Doctor doctor) {
      Integer a=null;
      try{
        a=doctorDAOImpl.addDoctor(doctor);

      }catch(SQLException e){
        e.printStackTrace();
      }
      return a;
    }


    @Override
    public List<Doctor> getDoctorSortedByExperience() {
       List<Doctor>a= new ArrayList<>();
       try{
        a=doctorDAOImpl.getAllDoctors();
       }catch(SQLException e){
        e.printStackTrace();
       }
       Collections.sort(a,Comparator.comparing(Doctor::getYearsOfExperience));
       return a;
    }

    public void updateDoctor(Doctor doctor){
      try{
        doctorDAOImpl.updateDoctor(doctor);
      }catch(SQLException e){
        e.printStackTrace();
      }

    }
    public void deleteDoctor(int doctorId){
        try{
          doctorDAOImpl.deleteDoctor(doctorId);
        }catch(SQLException e){
          e.printStackTrace();
        }
    }

}